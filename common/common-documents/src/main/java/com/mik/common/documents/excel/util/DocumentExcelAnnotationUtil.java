package com.mik.common.documents.excel.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class DocumentExcelAnnotationUtil {

    /*
    public void forEachTableAnnotation(final Class<?> clazz, Consumer<ExcelMarkerTable> consumer) {
        Arrays.stream(clazz.getAnnotations())
              .filter(DocumentExcelAnnotationUtil::isTableAnnotation)
              .map(ExcelMarkerTable.class::cast)
              .filter(annotation-> !annotation.id().isEmpty())
              .forEach(consumer);
    }

    public void forEachColumnAnnotation(final Field field, Consumer<Annotation> consumer) {
        Arrays.stream(field.getAnnotations())
              .filter(DocumentExcelAnnotationUtil::isColumnAnnotation)
              .forEach(consumer);
    }


    public void forEachColumnAnnotation(final Field[] fields, BiConsumer<Field,Annotation> consumer) {
        for (Field field : fields) {
            Arrays.stream(field.getAnnotations())
                    .filter(DocumentExcelAnnotationUtil::isColumnAnnotation)
                    .forEach(annotation -> consumer.accept(field, annotation));
        }
    }

    public boolean isColumnMapGroupAnnotation(final Annotation annotation) {
        return annotation instanceof ExcelMarkerTableMapColumnGroup;
    }

    private boolean isTableAnnotation(Annotation annotation) {
        return annotation instanceof ExcelMarkerTable;
    }

    private boolean isColumnAnnotation(Annotation annotation) {
        return annotation instanceof ExcelMarkerTableColumn
               ||
               annotation instanceof ExcelMarkerTableColumnGrouping
               ||
               annotation instanceof ExcelMarkerTableMapColumnGroup;
    }

     */


}
