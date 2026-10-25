package com.mik.common.documents.excel.model.column;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExcelGroupColumnDefinition {


    private String title;

    private List<ExcelColumnDefinition> columns;

}
