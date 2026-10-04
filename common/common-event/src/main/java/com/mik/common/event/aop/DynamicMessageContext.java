package com.mik.common.event.aop;

import com.mik.common.event.entities.FieldChange;

import java.util.Map;

public class DynamicMessageContext {

    private static final ThreadLocal<String> dynamicMessage = new ThreadLocal<>();
    private static final ThreadLocal<Map<String, FieldChange>> modifiedFields = new ThreadLocal<>();

    public static void setDynamicMessage(String message) {
        dynamicMessage.set(message);
    }

    public static String getDynamicMessage() {
        return dynamicMessage.get();
    }

    public static void setModifiedFields(Map<String, FieldChange> fields) {
        modifiedFields.set(fields);
    }

    public static Map<String, FieldChange> getModifiedFields() {
        return modifiedFields.get();
    }

    public static void clear() {
        dynamicMessage.remove();
        modifiedFields.remove();
    }
}
