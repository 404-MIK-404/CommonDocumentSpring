package com.mik.common.documents.excel.model.configuration;

import com.mik.common.documents.shared.model.DocumentConfiguration;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * Расширение DocumentConfiguration для отчётов формата EXCEL
 */
@Getter
@SuperBuilder
public class DocumentExcelConfiguration extends DocumentConfiguration {

    /* Динамические отчёты */
    private List<ExcelDynamicTemplate> dynamicTemplates;

    /* Вывод чисел в тыс. формат */
    @Builder.Default
    private boolean isThousand = false;

}
