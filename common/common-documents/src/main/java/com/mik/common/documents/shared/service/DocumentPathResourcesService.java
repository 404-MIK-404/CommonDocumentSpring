package com.mik.common.documents.shared.service;

import com.mik.common.documents.exceptions.exception.DocumentTemplateNotFoundException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class DocumentPathResourcesService {


    public InputStream getTemplate(String path) throws IOException {
        Resource resource = new ClassPathResource(path);
        if (!resource.exists()) {
            throw new DocumentTemplateNotFoundException("Файл шаблона не найден: " + path);
        }
        return resource.getInputStream();
    }




}
