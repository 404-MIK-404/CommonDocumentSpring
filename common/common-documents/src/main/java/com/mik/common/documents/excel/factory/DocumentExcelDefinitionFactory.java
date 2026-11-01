package com.mik.common.documents.excel.factory;


import com.mik.common.documents.excel.annotation.column.ExcelColumn;
import com.mik.common.documents.excel.annotation.column.style.ExcelColumnStyle;
import com.mik.common.documents.excel.annotation.column.style.ExcelDataStyle;
import com.mik.common.documents.excel.annotation.style.ExcelColorCell;
import com.mik.common.documents.excel.annotation.style.ExcelStyleCell;
import com.mik.common.documents.excel.model.column.ExcelColumnDefinition;
import com.mik.common.documents.excel.model.column.style.ExcelColumnStyleDefinition;
import com.mik.common.documents.excel.model.column.style.ExcelDataStyleDefinition;
import com.mik.common.documents.excel.model.style.ExcelCellColorDefinition;
import com.mik.common.documents.excel.model.style.ExcelCellStyleDefinition;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@AllArgsConstructor
public class DocumentExcelDefinitionFactory {


    public ExcelColumnDefinition onCreateColumnDefinition(ExcelColumn annotation,String path, ExcelColumnStyleDefinition columnStyle, ExcelDataStyleDefinition dataStyle) {
        return ExcelColumnDefinition.builder()
                .columnStyle(columnStyle)
                .dataStyle(dataStyle)
                .total(annotation.total())
                .clazz(annotation.cell().type())
                .name(annotation.name())
                .span(annotation.span())
                .path(path)
                .build();
    }

    public ExcelDataStyleDefinition onCreateDataStyle(ExcelDataStyle style) {
        if (Objects.isNull(style)) return null;
        return ExcelDataStyleDefinition.builder()
                .style(onCreateCellStyle(style.style()))
                .color(onCreateColorStyle(style.color()))
                .build();
    }


        public ExcelColumnStyleDefinition onCreateColumnStyle(ExcelColumnStyle style) {
            if (Objects.isNull(style)) return null;
            return ExcelColumnStyleDefinition.builder()
                    .width(style.width())
                    .style(onCreateCellStyle(style.style()))
                    .color(onCreateColorStyle(style.color()))
                    .build();
        }



    private ExcelCellStyleDefinition onCreateCellStyle(ExcelStyleCell style) {
        if (Objects.isNull(style)) return null;
        return ExcelCellStyleDefinition.builder()
                .horizontal(style.horizontal())
                .vertical(style.vertical())
                .bold(style.bold())
                .wrap(style.wrap())
                .isDecimal(style.isDecimal())
                .build();
    }


    private ExcelCellColorDefinition onCreateColorStyle(ExcelColorCell color) {
        if (Objects.isNull(color)) return null;
        return ExcelCellColorDefinition.builder()
                .color(color.color())
                .build();
    }


}
