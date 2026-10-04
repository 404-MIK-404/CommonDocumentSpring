package com.mik.common.documents.excel.model.column;

import com.mik.common.documents.excel.annotation.ExcelTotalBindingType;
import com.mik.common.documents.excel.annotation.style.ExcelMarkerTableStyleColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ExcelColumnDefinition {

    /**
     * Сами данные о колонке
     */

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

    /**
     * Группировка колонки
     **/
    @Builder.Default
    private String groupTitle = null;

    @Builder.Default
    private List<ExcelColumnDefinition> children = new ArrayList<>();

    /**
     * Через какую формулу формировать итоговое значение, если group заполнен, то работать не будет.
     */
    @Builder.Default
    private ExcelTotalBindingType totalBindingType = ExcelTotalBindingType.NONE;

}
