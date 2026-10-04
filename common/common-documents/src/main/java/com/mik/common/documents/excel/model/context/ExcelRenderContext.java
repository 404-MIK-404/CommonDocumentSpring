package com.mik.common.documents.excel.model.context;

import com.mik.common.documents.excel.model.table.ExcelTableDefinition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExcelRenderContext {

    /**
     * Оставляем
     */
    private Map<String, Object> properties;

    /*
    * Оставляем
    * */
    private ExcelTableDefinition table;

    /** Ключ для работы с property или таблицы **/
    private String key;

    private String command;

}
