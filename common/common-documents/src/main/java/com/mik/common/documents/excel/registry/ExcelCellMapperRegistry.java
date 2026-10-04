package com.mik.common.documents.excel.registry;

import com.mik.common.documents.excel.mapper.ExcelExportCellMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@AllArgsConstructor
public class ExcelCellMapperRegistry {

    private final Map<Class<?>, ExcelExportCellMapper> cellMappers;

    @SuppressWarnings("unchecked")
    public <T> ExcelExportCellMapper<T> get(final Class<?> clazz) {
        return (ExcelExportCellMapper<T>) cellMappers.get(clazz);
    }

}
