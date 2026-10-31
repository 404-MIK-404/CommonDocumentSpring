package com.mik.common.documents.excel.util;

import com.mik.common.documents.excel.annotation.column.ExcelColumn;
import com.mik.common.documents.excel.annotation.column.ExcelGroupColumn;
import com.mik.common.documents.excel.annotation.column.ExcelMapColumn;
import com.mik.common.documents.excel.annotation.column.style.ExcelColumnStyle;
import com.mik.common.documents.excel.annotation.column.style.ExcelDataStyle;
import com.mik.common.documents.excel.annotation.table.ExcelTable;
import lombok.experimental.UtilityClass;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@UtilityClass
public class DocumentExcelAnnotationUtil {

    public void forEachTableAnnotation(final Class<?> clazz, Consumer<ExcelTable> consumer) {
        if (Objects.isNull(clazz)) return;
        Arrays.stream(clazz.getAnnotations())
              .filter(annotation -> annotation instanceof ExcelTable)
              .map(ExcelTable.class::cast)
              .filter(annotation-> !annotation.id().isEmpty())
              .forEach(consumer);
    }

    public List<ExcelTable> forEachTableAnnotation(final Class<?> clazz) {
        List<ExcelTable> annotations = new ArrayList<>();
        for (final Annotation annotation : clazz.getAnnotations()) {
            if (annotation instanceof ExcelTable && !((ExcelTable) annotation).id().isEmpty()) {
                annotations.add((ExcelTable) annotation);
            }
        }
        return annotations;
    }

    public List<Annotation> forEachColumnAnnotation(final Field field) {
        List<Annotation> annotations = new ArrayList<>();
        Annotation[] fieldAnnotations = field.getAnnotations();
        for (final Annotation fieldAnnotation : fieldAnnotations) {
            if (isColumnAnnotation(fieldAnnotation) || isStyleAnnotation(fieldAnnotation)) {
                annotations.add(fieldAnnotation);
            }
        }
        return annotations;
    }

    public void forEachColumnAnnotation(final Field field, final BiConsumer<Field,Annotation> consumer) {
        for (final Annotation fieldAnnotation : field.getAnnotations()) {
            if (isColumnAnnotation(fieldAnnotation) || isStyleAnnotation(fieldAnnotation)) {
                consumer.accept(field,fieldAnnotation);
            }
        }
    }


    /*
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

         */

    private boolean isTableAnnotation(Annotation annotation) {
        return annotation instanceof ExcelTable;
    }

    private boolean isColumnAnnotation(Annotation annotation) {
        return annotation instanceof ExcelColumn
               ||
               annotation instanceof ExcelGroupColumn
               ||
               annotation instanceof ExcelMapColumn;
    }

    private boolean isStyleAnnotation(Annotation annotation) {
        return annotation instanceof ExcelColumnStyle
                ||
                annotation instanceof ExcelDataStyle;
    }



}
