package com.mik.common.documents.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public final class DocumentReflectionDataExtractorUtils {

    private static final ObjectMapper injector = new ObjectMapper();

    private static final Map<Class<?>, Function<JsonNode, Object>> extractors = new HashMap<>();


    static {
        extractors.put(Number.class, node ->
                                             node != null && !node.isNull()
                                             ? node.decimalValue()
                                             : 0
        );

        extractors.put(Boolean.class, node ->
                                              node != null && !node.isNull()
                                              && node.booleanValue()
        );

        extractors.put(BigDecimal.class, node ->
                                                 node != null && !node.isNull()
                                                 ? node.decimalValue()
                                                 : BigDecimal.ZERO
        );

        extractors.put(String.class, node ->
                                             node != null && !node.isNull()
                                             ? node.textValue()
                                             : ""
        );
    }

    @SuppressWarnings("unchecked")
    public static <T> T invokeData(Object value,Class<?> clazz, String path) {
        JsonNode node = injector.valueToTree(value);
        node = getJsonNodeByPath(node, path);
        Function<JsonNode, Object> extractor = extractors.entrySet().stream()
                                                         .filter(e -> e.getKey().isAssignableFrom(clazz))
                                                         .map(Map.Entry::getValue)
                                                         .findFirst()
                                                         .orElseThrow(() -> new IllegalArgumentException("No extractor for " + clazz));
        return (T) extractor.apply(node);
    }

    public static List<JsonNode> convertToListJsonNode(List<?> data) {
        return data.stream().map(value ->
                                         (JsonNode) injector.valueToTree(value))
                   .toList();
    }

    public static Set<String> getKeysDataByPath(List<JsonNode> data, String path) {
        String[] parts = path.split("\\.");
        Set<String> uniqueKeys = new HashSet<>();
        for (JsonNode root : data) {
            JsonNode current = root;
            for (String part : parts) {
                current = current.path(part);
            }
            if (current.isObject()) {
                current.fieldNames().forEachRemaining(uniqueKeys::add);
            }
        }
        return uniqueKeys;
    }

    public static Map<String, List<JsonNode>> toMapGroupByKeys(List<JsonNode> data, Set<String> keys) {
        return data.stream().collect(Collectors.groupingBy(
                node -> keys.stream()
                            .map(k -> node.path(k).asText())
                            .collect(Collectors.joining(" "))
        ));
    }

    public static Map<String, List<JsonNode>> toMapGroupAndFilterByKeys(List<JsonNode> data,
                                                               Set<String> groupKeys,
                                                               Map<String, String> filters) {
        return data.stream().filter(node -> filters == null || filters.isEmpty() ||
                                            filters.entrySet().stream()
                                                   .allMatch(e -> {
                                                       JsonNode value = node.path(e.getKey());
                                                       return !value.isMissingNode()
                                                              && e.getValue().equals(value.asText());
                                                   })
                   )
                   .collect(Collectors.groupingBy(
                           node -> groupKeys.stream()
                                            .map(k -> node.path(k).asText())
                                            .collect(Collectors.joining(" "))
                   ));
    }

    public static Map<?,?> invokeMap(Object value,String path) {
        JsonNode node = injector.valueToTree(value);
        node = getJsonNodeByPath(node,path);
        if (node == null || node.isNull()) return null;
        return injector.convertValue(node, Map.class);
    }

    private static JsonNode getJsonNodeByPath(JsonNode node,String path) {
        JsonNode current = node;
        for (String part : path.split("\\.")) {
            if (current == null) return null;
            current = current.get(part);
        }
        return current;
    }

}
