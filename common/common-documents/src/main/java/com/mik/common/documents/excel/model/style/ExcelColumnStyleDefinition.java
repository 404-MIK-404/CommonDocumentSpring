package com.mik.common.documents.excel.model.style;

import com.mik.common.documents.excel.annotation.style.ExcelMarkerColorStyleCell;
import com.mik.common.documents.excel.annotation.style.ExcelMarkerStyleCell;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ExcelColumnStyleDefinition {

    private ExcelMarkerStyleCell style;

    private ExcelMarkerColorStyleCell color;

}
