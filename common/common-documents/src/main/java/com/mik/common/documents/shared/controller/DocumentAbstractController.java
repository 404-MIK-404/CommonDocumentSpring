package com.mik.common.documents.shared.controller;

import com.mik.common.documents.exceptions.exception.DocumentTemplateNotFoundException;
import com.mik.common.documents.shared.model.DocumentConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.io.InputStream;

public abstract class DocumentAbstractController<T extends DocumentConfiguration> {

    /** Экспорт данных **/
    public abstract ResponseEntity<byte[]> exportDocument(DocumentConfiguration configuration);

    /** Импорт данных **/
    public abstract ResponseEntity<byte[]> importDocument();

    /** Выгрузка документа **/
    public abstract ResponseEntity<byte[]> loadDocument(DocumentConfiguration configuration);


    protected InputStream findTemplateDocument(String path) throws IOException {
        Resource resource = new ClassPathResource(path);
        if (!resource.exists()) {
            throw new DocumentTemplateNotFoundException("Файл шаблона не найден: " + path);
        }
        return resource.getInputStream();
    }

}
