package com.mik.common.documents.excel.factory;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DocumentExcelContextFactory {


    /*
    public ExcelAnnotationContext createAnnotationContext(Annotation annotation, Class<?> clazz , List<?> data) {
        return ExcelAnnotationContext.builder()
                                     .annotation(annotation)
                                     .data(data)
                                     .clazz(clazz)
                                     .build();
    }

    public ExcelAnnotationContext createAnnotationContext(Annotation annotation, List<ExcelColumnDefinition> columns, String path, List<JsonNode> data, Field field) {
        return ExcelAnnotationContext.builder()
                                     .annotation(annotation)
                                     .columns(columns)
                                     .parentPath(path)
                                     .data(data)
                                     .field(field)
                                     .build();
    }



    public ExcelExportContext createExportContext(DocumentConfiguration configuration) {
        return ExcelExportContext.builder()
                                 .properties(configuration.getProperties())
                                 .data(configuration.getData())
                                 .build();
    }

     */


}
