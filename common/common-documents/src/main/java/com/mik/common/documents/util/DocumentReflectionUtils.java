package com.mik.common.documents.util;

import lombok.experimental.UtilityClass;
import org.springframework.core.ResolvableType;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@UtilityClass
public final class DocumentReflectionUtils {


    public boolean isMapStringPOJO(Field field) {
        ResolvableType type = ResolvableType.forField(field);

        if (!Map.class.isAssignableFrom(type.resolve())) {
            return false;
        }

        Class<?> keyClass = type.getGeneric(0).resolve();
        Class<?> valueClass = type.getGeneric(1).resolve();

        if (keyClass != String.class || valueClass == null) {
            return false;
        }

        return !valueClass.isPrimitive()
               && !valueClass.isArray()
               && !valueClass.isEnum()
               && !Collection.class.isAssignableFrom(valueClass)
               && !Map.class.isAssignableFrom(valueClass)
               && !DocumentReflectionUtils.isSimpleClass(valueClass);
    }

    public Class<?> resolveMapValueType(Field field) {
        ResolvableType type = ResolvableType.forField(field);
        Class<?> key = type.getGeneric(0).resolve();
        Class<?> value = type.getGeneric(1).resolve();
        if (key != String.class) return null;
        return value;
    }


    public boolean isSimpleClass(Class<?> clazz) {
        if (clazz == null) return false;
        return ClassUtils.isPrimitiveOrWrapper(clazz)
               || BigDecimal.class.isAssignableFrom(clazz)
               || CharSequence.class.isAssignableFrom(clazz)
               || java.util.Date.class.isAssignableFrom(clazz)
               || java.time.temporal.Temporal.class.isAssignableFrom(clazz);
    }

    public boolean isNumberClass(Class<?> clazz) {
        if (clazz == null) return false;
        return Number.class.isAssignableFrom(clazz) ||
               clazz == byte.class ||
               clazz == short.class ||
               clazz == int.class ||
               clazz == long.class ||
               clazz == double.class ||
               clazz == float.class;
    }

    public boolean isIntegralNumberClass(Class<?> clazz) {
        if (clazz == null) return false;
        return clazz == Integer.class
               || clazz == Long.class
               || clazz == Short.class
               || clazz == Byte.class;
    }

    public Class<?> getClassFromList(List<?> value) {
        Class<?> clazz = value.getClass();
        return ResolvableType
                .forClass(clazz)
                .getGeneric(0)
                .resolve();
    }

}



