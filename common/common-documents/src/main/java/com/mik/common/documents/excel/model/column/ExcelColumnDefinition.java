package com.mik.common.documents.excel.model.column;

import com.mik.common.documents.excel.annotation.ExcelTotalBindingType;
import com.mik.common.documents.excel.model.column.style.ExcelColumnStyleDefinition;
import com.mik.common.documents.excel.model.column.style.ExcelDataStyleDefinition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Данные о колонке
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExcelColumnDefinition {

    private String name;

    private Class<?> clazz;

    private int span;

    private ExcelColumnStyleDefinition columnStyle;

    private ExcelDataStyleDefinition dataStyle;

    @Builder.Default
    private String path = "";

    private ExcelGroupColumnDefinition groups;

    private ExcelTotalBindingType total;

    /*

    private String name;

    private Class<?> type;

    private ExcelMarkerTableStyleColumn styleColumn;

    private ExcelMarkerTableStyleColumn styleData;

    @Builder.Default
    private int span = 0;

    @Builder.Default
    private boolean decimal = false;

    @Builder.Default
    private String pathField = "";

    private String defaultValue;

     */

    /**
     * Группировка колонки
     **/
    /*
    @Builder.Default
    private String groupTitle = null;

    @Builder.Default
    private List<ExcelColumnDefinition> children = new ArrayList<>();

     */

    /**
     * Через какую формулу формировать итоговое значение, если group заполнен, то работать не будет.
     */
    /*
    @Builder.Default
    private ExcelTotalBindingType totalBindingType = ExcelTotalBindingType.NONE;

     */

}
