package com.mik.common.documents.excel.util;

import com.mik.common.documents.excel.model.column.ExcelColumnDefinition;
import com.mik.common.documents.excel.model.table.ExcelTableDefinition;
import lombok.experimental.UtilityClass;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

@UtilityClass
public class ExcelColumnDefinitionUtils {


    public static List<ExcelColumnDefinition> getColumnDefinitions(List<ExcelColumnDefinition> columns) {
        List<ExcelColumnDefinition> result = new ArrayList<>();
        columns.forEach(column -> {
            if (column.getGroupTitle() != null && !column.getGroupTitle().isEmpty()) {
                result.addAll(getColumnDefinitions(column.getChildren()));
            } else {
                result.add(column);
            }
        });
        return result;
    }

    public static int depthColumn(ExcelTableDefinition table) {
        Deque<ExcelColumnDefinition> nodes = new ArrayDeque<>();
        int depth = 0;
        table.getColumns().forEach(column -> {
            if (column.getGroupTitle() != null
                && !column.getGroupTitle().isEmpty()
                && !column.getChildren().isEmpty()) {
                nodes.add(column);
            }
        });
        while (!nodes.isEmpty()) {
            ExcelColumnDefinition node = nodes.pop();
            int resDepth = getDepth(node,0);
            depth = Math.max(resDepth,depth);
        }
        return depth;
    }

    private static int getDepth(ExcelColumnDefinition columnNode, int depth) {
        if (columnNode.getChildren() == null || columnNode.getChildren().isEmpty()) {
            return depth;
        }
        int max = depth;
        for (ExcelColumnDefinition child : columnNode.getChildren()) {
            int childDepth = getDepth(child, depth + 1);
            max = Math.max(childDepth, max);
        }
        return max;
    }

}
