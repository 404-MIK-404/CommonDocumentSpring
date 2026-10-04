package com.mik.common.documents.excel.util;

import com.mik.common.documents.excel.annotation.style.ExcelMarkerTableStyleColumn;
import lombok.experimental.UtilityClass;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Objects;

@UtilityClass
public final class ExcelCellStyleUtils {

    public CellStyle createCellStyle(Cell cell) {
        CellStyle cellStyle = cell.getCellStyle();
        if (cellStyle == null) {
            cellStyle = cell.getSheet().getWorkbook().createCellStyle();
        } else {
            cellStyle.cloneStyleFrom(cell.getCellStyle());
        }
        return cellStyle;
    }

    public void applyConfigStyle(Workbook workbook, Cell cell, CellStyle cellStyle, ExcelMarkerTableStyleColumn annotationStyle) throws Exception {
        if (cellStyle == null) return;
        Row row = cell.getRow();
        Sheet sheet = cell.getSheet();
        int indexColumn = cell.getColumnIndex();
        ExcelCellStyleUtils.setHorizontalAlignment(cellStyle,annotationStyle.style().horizontal());
        ExcelCellStyleUtils.setVerticalAlignment(cellStyle,annotationStyle.style().vertical());
        ExcelCellStyleUtils.setWrapText(cellStyle,annotationStyle.style().wrap());
        ExcelCellStyleUtils.setBoldText(workbook,cellStyle,annotationStyle.style().bold());
        ExcelCellStyleUtils.setHeight(row,annotationStyle.style().height());
        ExcelCellStyleUtils.setWidth(sheet,indexColumn,annotationStyle.style().width());
        if (!Objects.isNull(annotationStyle.color())) ExcelCellStyleUtils.setBackgroundColor(workbook, annotationStyle.color().color(), cellStyle);
    }

    public void setDecimalStyle(CellStyle style, Cell cell, boolean isDecimal) {
        DataFormat dataFormat = cell.getSheet()
                                    .getWorkbook()
                                    .createDataFormat();
        String format = isDecimal ? "0.00" : "0";
        short styleFormat = dataFormat.getFormat(format);
        style.setDataFormat(styleFormat);
    }

    public void addCellStyle(Cell cell, CellStyle cellStyle) {
        cell.setCellStyle(cellStyle);
    }

    public void setHorizontalAlignment(CellStyle style, HorizontalAlignment alignment) {
        if (style == null || alignment == null) return;
        style.setAlignment(alignment);
    }

    public void setVerticalAlignment(CellStyle style, VerticalAlignment alignment) {
        if (style == null || alignment == null) return;
        style.setVerticalAlignment(alignment);
    }

    public void setWrapText(CellStyle style, Boolean wrapText) {
        if (style == null || wrapText == null) return;
        style.setWrapText(wrapText);
    }

    public void setBoldText(Workbook workbook, CellStyle style, Boolean bold) {
        Font font = workbook.getFontAt(style.getFontIndex());
        font.setBold(bold);
        style.setFont(font);
    }

    public void setHeight(Row row, float height) {
        if (height <= 0) return;
        row.setHeightInPoints(height);
    }

    public void setWidth(Sheet sheet, int indexColumn ,float width) {
        if (width <= 0) return;
        int width256 = Math.round(width * 256);
        width256 = Math.max(0, Math.min(width256, 255 * 256));
        sheet.setColumnWidth(indexColumn, width256);
    }

    public void setBackgroundColor(Workbook workbook,String hex, CellStyle style) throws DecoderException {
        if (workbook == null || hex == null || hex.isEmpty() || style == null) return;
        byte[] rgb = Hex.decodeHex(hex.replace("#", ""));
        if (workbook instanceof XSSFWorkbook xssfWorkbook) {
            setBackgroundColor(xssfWorkbook,style,rgb);
        } else if (workbook instanceof HSSFWorkbook hssfWorkbook) {
            setBackgroundColor(hssfWorkbook,rgb,style);
        }
    }


    public void setBackgroundColor(XSSFWorkbook workbook, CellStyle style, byte[] rgb) {
        if (workbook == null || style == null || rgb == null) return;
        XSSFColor customColor = new XSSFColor(rgb, workbook.getStylesSource().getIndexedColors());
        style.setFillForegroundColor(customColor);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    }

    public void setBackgroundColor(HSSFWorkbook workbook, byte[] rgb, CellStyle style) {
        if (workbook == null || style == null || rgb == null) return;
        HSSFPalette palette = workbook.getCustomPalette();
        HSSFColor color = palette.findColor(
                rgb[0],
                rgb[1],
                rgb[2]
        );
        if (color == null) return;
        style.setFillForegroundColor(color.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    }





}
