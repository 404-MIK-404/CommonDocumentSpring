package com.mik.common.documents.excel.model.column.style;

import com.mik.common.documents.excel.model.style.ExcelCellColorDefinition;
import com.mik.common.documents.excel.model.style.ExcelCellStyleDefinition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ExcelDataStyleDefinition {

    private ExcelCellStyleDefinition style;

    private ExcelCellColorDefinition color;

}
