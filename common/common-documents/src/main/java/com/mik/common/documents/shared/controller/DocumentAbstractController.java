package com.mik.common.documents.shared.controller;

import com.mik.common.documents.shared.model.DocumentConfiguration;
import org.springframework.http.ResponseEntity;

public abstract class DocumentAbstractController<T extends DocumentConfiguration> {

    /** Экспорт данных **/
    public abstract ResponseEntity<byte[]> exportDocument(DocumentConfiguration configuration);

    /** Импорт данных **/
    public abstract ResponseEntity<byte[]> importDocument();

    /** Выгрузка документа **/
    public abstract ResponseEntity<byte[]> loadDocument(DocumentConfiguration configuration);

}
