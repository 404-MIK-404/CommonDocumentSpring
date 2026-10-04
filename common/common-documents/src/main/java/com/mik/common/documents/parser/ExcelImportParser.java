package com.mik.common.documents.parser;

import com.mik.common.documents.excel.annotation.column.ExcelColumn;
import com.mik.common.documents.excel.annotation.sheet.ExcelSheet;
import com.mik.common.documents.excel.annotation.sheet.ExcelSheetName;
import com.mik.common.documents.exceptions.exception.ExcelParseException;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class ExcelImportParser {

    private static final DataFormatter FORMATTER = new DataFormatter();
    private static final Map<Class<?>, List<Field>> COLUMN_FIELDS = new ConcurrentHashMap<>();
    private static final Map<Class<?>, Optional<Field>> SHEET_NAME_FIELDS = new ConcurrentHashMap<>();

    public <T> List<T> parse(MultipartFile file, Class<T> clazz) {
        ExcelSheet meta = clazz.getAnnotation(ExcelSheet.class);
        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
            List<T> result = new ArrayList<>();
            for (Sheet sheet : resolveSheets(workbook, meta)) {
                result.addAll(parseSheet(sheet, clazz, meta, evaluator));
            }
            return result;
        } catch (Exception e) {
            throw new ExcelParseException("Не удалось открыть файл: " + file.getOriginalFilename(), e);
        }
    }

    public <T> Map<String, List<T>> parseAllSheetsGrouped(MultipartFile file, Class<T> clazz) {
        ExcelSheet meta = clazz.getAnnotation(ExcelSheet.class);
        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
            Map<String, List<T>> result = new LinkedHashMap<>();
            for (Sheet sheet : workbook) {
                result.put(sheet.getSheetName(), parseSheet(sheet, clazz, meta, evaluator));
            }
            return result;
        } catch (Exception e) {
            throw new ExcelParseException("Не удалось открыть файл: " + file.getOriginalFilename(), e);
        }
    }

    private List<Sheet> resolveSheets(Workbook workbook, ExcelSheet meta) {
        if (meta != null && meta.allSheets()) {
            List<Sheet> sheets = new ArrayList<>();
            workbook.forEach(sheets::add);
            return sheets;
        }
        if (meta != null && !meta.name().isBlank()) {
            Sheet sheet = workbook.getSheet(meta.name());
            if (sheet == null) throw new ExcelParseException("Лист не найден: " + meta.name());
            return List.of(sheet);
        }
        int index = meta != null ? meta.index() : 0;
        if (index >= workbook.getNumberOfSheets()) throw new ExcelParseException("Лист с индексом " + index + " отсутствует");
        return List.of(workbook.getSheetAt(index));
    }

    private <T> List<T> parseSheet(Sheet sheet, Class<T> clazz, ExcelSheet meta, FormulaEvaluator evaluator) {
        int startRow = meta != null ? meta.startRow() : 0;
        List<T> result = new ArrayList<>();
        for (int i = startRow; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;
            T obj = mapRow(row, clazz, sheet.getSheetName(), i, evaluator);
            if (obj != null) result.add(obj);
        }
        log.debug("Лист '{}': прочитано {} строк", sheet.getSheetName(), result.size());
        return result;
    }

    private <T> T mapRow(Row row, Class<T> clazz, String sheetName, int rowIndex, FormulaEvaluator evaluator) {
        try {
            T instance = clazz.getDeclaredConstructor().newInstance();
            boolean isEmpty = true;
            for (Field field : columnFields(clazz)) {
                int colIndex = field.getAnnotation(ExcelColumn.class).index();
                Cell cell = row.getCell(colIndex);
                Object value;
                try {
                    value = readCell(cell, field.getType(), evaluator);
                } catch (Exception e) {
                    throw new ExcelParseException("Лист '%s', строка %d, колонка %d, поле '%s': %s"
                                                          .formatted(sheetName, rowIndex + 1, colIndex + 1, field.getName(), e.getMessage()), e);
                }
                if (value != null) isEmpty = false;
                field.set(instance, value);
            }
            sheetNameField(clazz).ifPresent(f -> setSheetName(f, instance, sheetName));
            return isEmpty ? null : instance;
        } catch (ExcelParseException e) {
            throw e;
        } catch (Exception e) {
            throw new ExcelParseException("Не удалось создать объект " + clazz.getSimpleName() + " (строка " + (rowIndex + 1) + ")", e);
        }
    }

    private void setSheetName(Field field, Object instance, String sheetName) {
        try {
            field.set(instance, sheetName);
        } catch (IllegalAccessException e) {
            throw new ExcelParseException("Не удалось установить имя листа в поле " + field.getName(), e);
        }
    }

    private List<Field> columnFields(Class<?> clazz) {
        return COLUMN_FIELDS.computeIfAbsent(clazz, c -> {
            List<Field> fields = new ArrayList<>();
            for (Field f : c.getDeclaredFields()) {
                if (f.isAnnotationPresent(ExcelColumn.class)) {
                    f.setAccessible(true);
                    fields.add(f);
                }
            }
            return fields;
        });
    }

    private Optional<Field> sheetNameField(Class<?> clazz) {
        return SHEET_NAME_FIELDS.computeIfAbsent(clazz, c -> {
            for (Field f : c.getDeclaredFields()) {
                if (f.isAnnotationPresent(ExcelSheetName.class)) {
                    f.setAccessible(true);
                    return Optional.of(f);
                }
            }
            return Optional.empty();
        });
    }

    private Object readCell(Cell cell, Class<?> type, FormulaEvaluator evaluator) {
        if (cell == null || cell.getCellType() == CellType.BLANK) return null;
        if (type == String.class) return readString(cell, evaluator);
        if (type == Boolean.class || type == boolean.class) return readBoolean(cell);
        if (type == LocalDate.class) return readLocalDate(cell);
        if (type == LocalDateTime.class) return readLocalDateTime(cell);
        if (type.isEnum()) return readEnum(cell, type, evaluator);
        BigDecimal decimal = readDecimal(cell, evaluator);
        if (decimal == null) return null;
        if (type == BigDecimal.class) return decimal;
        if (type == Integer.class || type == int.class) return decimal.intValue();
        if (type == Long.class || type == long.class) return decimal.longValue();
        if (type == Double.class || type == double.class) return decimal.doubleValue();
        throw new ExcelParseException("Неподдерживаемый тип поля: " + type.getSimpleName());
    }

    private String readString(Cell cell, FormulaEvaluator evaluator) {
        String val = switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> FORMATTER.formatCellValue(cell);
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case FORMULA -> FORMATTER.formatCellValue(cell, evaluator);
            default -> null;
        };
        if (val == null) return null;
        val = val.trim();
        return val.isEmpty() ? null : val;
    }

    private BigDecimal readDecimal(Cell cell, FormulaEvaluator evaluator) {
        return switch (cell.getCellType()) {
            case NUMERIC -> BigDecimal.valueOf(cell.getNumericCellValue());
            case FORMULA -> {
                CellValue cv = evaluator.evaluate(cell);
                yield cv.getCellType() == CellType.NUMERIC ? BigDecimal.valueOf(cv.getNumberValue()) : null;
            }
            case STRING -> parseDecimalString(cell.getStringCellValue());
            default -> null;
        };
    }

    private BigDecimal parseDecimalString(String raw) {
        if (raw == null) return null;
        String val = raw.trim().replace(",", ".");
        return val.isEmpty() || !val.matches("[-+]?\\d+(\\.\\d+)?") ? null : new BigDecimal(val);
    }

    private Boolean readBoolean(Cell cell) {
        return switch (cell.getCellType()) {
            case BOOLEAN -> cell.getBooleanCellValue();
            case NUMERIC -> cell.getNumericCellValue() != 0;
            case STRING -> switch (cell.getStringCellValue().trim().toLowerCase()) {
                case "true", "1", "да", "yes" -> true;
                case "false", "0", "нет", "no" -> false;
                default -> null;
            };
            default -> null;
        };
    }

    private LocalDate readLocalDate(Cell cell) {
        if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
            return cell.getLocalDateTimeCellValue().toLocalDate();
        }
        if (cell.getCellType() == CellType.STRING) {
            String v = cell.getStringCellValue().trim();
            return v.isEmpty() ? null : LocalDate.parse(v, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }
        return null;
    }

    private LocalDateTime readLocalDateTime(Cell cell) {
        if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
            return cell.getLocalDateTimeCellValue();
        }
        if (cell.getCellType() == CellType.STRING) {
            String v = cell.getStringCellValue().trim();
            return v.isEmpty() ? null : LocalDateTime.parse(v, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
        }
        return null;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private Object readEnum(Cell cell, Class<?> type, FormulaEvaluator evaluator) {
        String v = readString(cell, evaluator);
        return v == null ? null : Enum.valueOf((Class<Enum>) type, v.toUpperCase());
    }
}
