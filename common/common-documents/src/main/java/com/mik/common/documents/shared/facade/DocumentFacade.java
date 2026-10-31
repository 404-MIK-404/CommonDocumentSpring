package com.mik.common.documents.shared.facade;


import com.mik.common.documents.exceptions.exception.DocumentException;
import com.mik.common.documents.shared.model.DocumentConfiguration;
import com.mik.common.documents.shared.model.FileExport;

import java.io.InputStream;

public interface DocumentFacade<T extends DocumentConfiguration> {

    /**Выгрузка документов**/
    FileExport loadTo(InputStream file, DocumentConfiguration configuration) throws DocumentException;

    /**Экспорт отчёта**/
    FileExport exportTo(InputStream file, DocumentConfiguration configuration) throws DocumentException;

    //@TODO Переименовать метод
    FileExport toExport(InputStream file, T configuration) throws DocumentException;

}
