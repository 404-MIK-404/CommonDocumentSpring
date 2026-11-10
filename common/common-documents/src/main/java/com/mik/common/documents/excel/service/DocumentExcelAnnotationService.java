package com.mik.common.documents.excel.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.mik.common.documents.excel.annotation.table.ExcelTable;
import com.mik.common.documents.excel.factory.ExcelAnnotationContextFactory;
import com.mik.common.documents.excel.model.column.ExcelColumnDefinition;
import com.mik.common.documents.excel.model.column.ExcelGroupColumnDefinition;
import com.mik.common.documents.excel.model.context.ColumnContext;
import com.mik.common.documents.excel.model.context.TableContext;
import com.mik.common.documents.excel.model.table.ExcelTableDefinition;
import com.mik.common.documents.excel.processor.column.ExcelAnnotationProcessor;
import com.mik.common.documents.excel.processor.table.ExcelTableAnnotationProcessor;
import com.mik.common.documents.excel.util.DocumentExcelAnnotationUtil;
import com.mik.common.documents.util.DocumentReflectionDataExtractorUtils;
import com.mik.common.documents.util.DocumentReflectionUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

@Service
@AllArgsConstructor
public class DocumentExcelAnnotationService {

    //private final ExcelAnnotationScannerRegistry annotationScannerRegistry;

    private final Map<Class<? extends Annotation>, ExcelAnnotationProcessor> processors;

    private ExcelTableAnnotationProcessor tableAnnotationProcessor;

    private final ExcelAnnotationContextFactory factory;

    //private DocumentExcelContextFactory factory;

    //private Set<Class<?>> visitedClazz;

    public List<ExcelTableDefinition> buildExcelStructureDefinition(List<List<?>> tables) {
        Set<String> visited = new HashSet<>();
        List<ExcelTableDefinition> definitions = new ArrayList<>();
        for (List<?> table : tables) {
            Class<?> clazzTable = DocumentReflectionUtils.getClassFromList(table);
            if (clazzTable == null) continue;
            ExcelTableDefinition tableDefinition = onProcessTableDefinition(visited, clazzTable, table);
            if (tableDefinition == null) continue;
            onProcessColumnDefinitionByClass(clazzTable, tableDefinition);
            onProcessColumnDefinitionByTable(tableDefinition.getColumns(), tableDefinition.getData());
            definitions.add(tableDefinition);
        }
        return definitions;
    }

    /** @TODO Изменить название функций */
    private ExcelTableDefinition onProcessTableDefinition(Set<String> tableIds,Class<?> clazz, List<?> data) {
        List<ExcelTable> annotations = DocumentExcelAnnotationUtil.forEachTableAnnotation(clazz);
        ExcelTableDefinition definition = null;
        for (ExcelTable annotation : annotations) {
            String id = annotation.id();
            if (tableIds.contains(id)) continue;
            TableContext context = factory.onCreateTableContext(annotation,clazz,data);
            definition = tableAnnotationProcessor.process(context);
            definition.setData(DocumentReflectionDataExtractorUtils.convertToListJsonNode(data));
        }
        return definition;
    }

    private void onProcessColumnDefinitionByClass(Class<?> clazz, ExcelTableDefinition table) {
        List<ExcelColumnDefinition> columns = table.getColumns();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            List<Annotation> annotations = DocumentExcelAnnotationUtil.forEachColumnAnnotation(field);
            for (Annotation annotation : annotations) {
                Class<? extends Annotation> type = annotation.annotationType();
                ExcelAnnotationProcessor<ColumnContext> processor = processors.get(type);
                if (processor == null) continue;
                ColumnContext context = factory.onCreateColumnContext(annotation,null, table.getData(), field);
                processor.process(columns,context);
            }
        }
        table.setColumns(columns);
    }

    private void onProcessColumnDefinitionByTable(List<ExcelColumnDefinition> columns, List<JsonNode> data) {
        for (ExcelColumnDefinition column : columns) {
            Class<?> clazz = column.getClazz();
            ExcelGroupColumnDefinition group = column.getGroups();
            if (Objects.isNull(group)) continue;
            NodeExcel node = new NodeExcel(column, clazz, data);
            onProcessNodeColumnGroup(node, data);
        }
    }

    private void onProcessNodeColumnGroup(NodeExcel node, List<JsonNode> data) {
        Set<ExcelColumnDefinition> visitedNodes = Collections.newSetFromMap(new IdentityHashMap<>());
        Deque<ExcelColumnDefinition> nodes = new ArrayDeque<>();
        nodes.add(node.column);
        while(!nodes.isEmpty()) {

            ExcelColumnDefinition column = nodes.pop();
            Class<?> columnType = column.getClazz();
            //String path = column.getPath();
            if (!visitedNodes.add(column) && DocumentReflectionUtils.isSimpleClass(columnType)) continue;
            ExcelGroupColumnDefinition group = column.getGroups();
            List<ExcelColumnDefinition> columns = group.getColumns();

            Field[] fields = column.getClazz().getDeclaredFields();
            for (Field field : fields) {
                List<Annotation> annotations = DocumentExcelAnnotationUtil.forEachColumnAnnotation(field);
                for (Annotation annotation : annotations) {
                    Class<? extends Annotation> type = annotation.annotationType();
                    ExcelAnnotationProcessor<ColumnContext> processor = processors.get(type);
                    if (processor == null) continue;
                    ColumnContext context = factory.onCreateColumnContext(annotation,column, data, field);
                    processor.process(columns,context);
                }
            }
            for (ExcelColumnDefinition columnGroup : columns) {
                Class<?> clazz = columnGroup.getClazz();
                ExcelGroupColumnDefinition groupFind = columnGroup.getGroups();
                if (Objects.isNull(groupFind)) continue;
                //NodeExcel node = new NodeExcel(column, clazz, data);
                nodes.add(column);
            }

        }
    }

    /** @TODO Изменить название функций */
    private void onProcessColumnDefinition(Class<?> clazz, ExcelColumnDefinition definition) {

    }


    /*
    public void processTableAnnotations(ExcelExportContext context) {
        ExcelAnnotationProcessor<ExcelTableDefinition> processor =  annotationScannerRegistry.get(ExcelMarkerTable.class);
        if (Objects.isNull(context.getData())) return;
        context.getData().forEach((clazz, values) -> {
            DocumentExcelAnnotationUtil.forEachTableAnnotation(clazz, annotation -> {
                ExcelAnnotationContext annotationContext = factory.createAnnotationContext(annotation,clazz,values);
                String id = annotation.id();
                ExcelTableDefinition table = processor.process(annotationContext);
                context.getTables().put(id,table);
            });
        });
    }

    public void processColumnAnnotations(ExcelExportContext context) {
        if (Objects.isNull(context.getData()) || Objects.isNull(context.getTables())) return;
        context.getTables().forEach((clazz, tableDefinition) -> {
            visitedClazz = new HashSet<>();
            List<ExcelColumnDefinition> columns = tableDefinition.getColumns();
            List<JsonNode> data = tableDefinition.getData();
            Field[] fields = tableDefinition.getClazz().getDeclaredFields();
            NodeExcelFieldAnnotation node = new NodeExcelFieldAnnotation(fields,tableDefinition.getClazz(),data,columns,"");
            processColumnAnnotationsField(node);
        });
    }

    public void processColumnGroupAnnotations(ExcelExportContext context) {
        context.getTables().forEach((clazz, tableDefinition) -> {
            Set<ExcelColumnDefinition> visitedNodes = Collections.newSetFromMap(new IdentityHashMap<>());
            Deque<ExcelColumnDefinition> nodeToProcess = tableDefinition.getColumns().stream()
                                                                        .filter(this::isGroupColumn)
                                                                        .collect(Collectors.toCollection(ArrayDeque::new));
            while(!nodeToProcess.isEmpty()) {
                ExcelColumnDefinition node = nodeToProcess.pop();
                Class<?> columnType = node.getType();
                String path = node.getPathField();
                if (!visitedNodes.add(node) && DocumentReflectionUtils.isSimpleClass(columnType)) continue;
                processColumnAnnotationsFromClass(columnType,node,nodeToProcess,tableDefinition.getData(),path);
            }
        });
    }

    private void processColumnAnnotationsField(NodeExcelFieldAnnotation node) {
        Class<?> clazz = node.clazz;
        if (!visitedClazz.add(clazz)) return;
        try {
            DocumentExcelAnnotationUtil.forEachColumnAnnotation(node.fields,((field, annotation) -> {
                Class<? extends Annotation> annotationType = annotation.annotationType();
                ExcelAnnotationProcessor<List<ExcelColumnDefinition>> processor = annotationScannerRegistry.get(annotationType);
                ExcelAnnotationContext context = factory.createAnnotationContext(annotation,node.columns, node.path, node.data, field);
                boolean isColumnMapGroup = DocumentExcelAnnotationUtil.isColumnMapGroupAnnotation(annotation);
                if (!isColumnMapGroup) {
                    buildColumns(node.columns, processor, context);
                } else {
                    processColumnMap(annotation,field,node);
                }
            }));
        } finally {
            visitedClazz.remove(clazz);
        }
    }

    private void processColumnMap(Annotation annotation, Field field, NodeExcelFieldAnnotation node) {
        ExcelMarkerTableMapColumnGroup tableMapColumnAnnotation = (ExcelMarkerTableMapColumnGroup) annotation;
        String curPath = node.path;
        List<JsonNode> data = node.data;
        boolean isStringPojoMap = DocumentReflectionUtils.isMapStringPOJO(field);
        if (!isStringPojoMap) return;
        Class<?> clazzMapValue = DocumentReflectionUtils.resolveMapValueType(field);
        String path = (curPath == null || curPath.isEmpty())
                      ? field.getName()
                      : curPath + "." + field.getName();
        Set<String> uniqueKeys = DocumentReflectionDataExtractorUtils.getKeysDataByPath(data,path);
        for (String key : uniqueKeys) {
            String mapPath = path + "." + key;

            ExcelColumnDefinition columnDefinition = new ExcelColumnDefinition();
            columnDefinition.setType(clazzMapValue);
            columnDefinition.setGroupTitle(key);
            columnDefinition.setPathField(mapPath);
            columnDefinition.setStyleColumn(tableMapColumnAnnotation.columnStyle());
            columnDefinition.setStyleData(tableMapColumnAnnotation.dataStyle());
            NodeExcelFieldAnnotation nodeForward = new NodeExcelFieldAnnotation(
                    clazzMapValue.getDeclaredFields(),clazzMapValue,data,columnDefinition.getChildren(),mapPath);
            processColumnAnnotationsField(nodeForward);
            node.columns.add(columnDefinition);
        }
    }


    private void processColumnAnnotationsFromClass(Class<?> clazz, ExcelColumnDefinition column, Deque<ExcelColumnDefinition> nodes, List<JsonNode> data, String path) {
        DocumentExcelAnnotationUtil.forEachColumnAnnotation(clazz.getDeclaredFields(),((field, annotation) -> {
            Class<? extends Annotation> annotationType = annotation.annotationType();
            ExcelAnnotationProcessor<List<ExcelColumnDefinition>> analyzer = processors.get(annotationType);
            ExcelAnnotationContext context = factory.createAnnotationContext(annotation, new ArrayList<>(),path,data,field);
            List<ExcelColumnDefinition> created = analyzer.process(context);
            for (ExcelColumnDefinition child : created) {
                boolean exists = column.getChildren().stream().anyMatch(c -> c.getName() != null && c.getName().equals(child.getName()));
                if (exists) continue;
                column.getChildren().add(child);
                if (child.getGroupTitle() != null && !child.getGroupTitle().isEmpty()) {
                    nodes.add(child);
                }
            }
        }));
    }

     */

    /*
    private boolean containsColumn(List<ExcelColumnDefinition> list,
                                   ExcelColumnDefinition target) {
        return list.stream().anyMatch(existing ->
                                              Objects.equals(existing.getName(), target.getName()) &&
                                              Objects.equals(existing.getPathField(), target.getPathField())
        );

    }

    private boolean isGroupColumn(ExcelColumnDefinition column) {
        return column.getGroupTitle() != null && !column.getGroupTitle().isEmpty();
    }

    private void buildColumns(List<ExcelColumnDefinition> columns, ExcelAnnotationProcessor<List<ExcelColumnDefinition>> processor,ExcelAnnotationContext context) {
        List<ExcelColumnDefinition> resultColumns = processor.process(context);
        for (ExcelColumnDefinition col : resultColumns) {
            if (!containsColumn(columns, col)) {
                columns.add(col);
            }
        }
    }

     */

    private record NodeExcelFieldAnnotation(
            Field[] fields,
            Class<?> clazz,
            List<JsonNode> data,
            List<ExcelColumnDefinition> columns,
            String path
    ) {}


    private record NodeExcel(
            ExcelColumnDefinition column,
            Class<?> clazz,
            List<JsonNode> data
    ){}


}
