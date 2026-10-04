package com.mik.common.documents.export.annotation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;

/**
 * Проблема при вставке разных строк в таблицу.
 * Теряется целостность XML верстки, из-за чего не получается скопировать нормально строку,
 * как решение сделал копирование
 * и запоминание строк со стилями
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExcelCellSnapshot {
    private CellStyle style;
    private CellType type;
    private Object value;
    private String formula;
    private short rowHeight;

    // Для поддержки merged regions
    private boolean isMerged;
    private int firstRow;
    private int lastRow;
    private int firstCol;
    private int lastCol;
}
