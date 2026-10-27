package com.mik.common.documents.excel.model.total;

import com.mik.common.documents.excel.model.enums.ExcelTypeTotal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ExcelRuleTotalDefinition {


    @Builder.Default
    private String field = "";

    @Builder.Default
    private ExcelTypeTotal typeTotal = ExcelTypeTotal.NONE;



}
