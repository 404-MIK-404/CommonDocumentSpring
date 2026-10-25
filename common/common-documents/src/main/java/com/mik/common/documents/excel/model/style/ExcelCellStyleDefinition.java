package com.mik.common.documents.excel.model.style;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ExcelCellStyleDefinition {

    private HorizontalAlignment horizontal;

    private VerticalAlignment vertical;

    private boolean bold;

    private boolean wrap;

    private boolean isDecimal;

}
