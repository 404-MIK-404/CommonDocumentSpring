package com.mik.common.documents.shared.factory;

import com.mik.common.documents.shared.model.FileExport;
import org.springframework.http.ResponseEntity;

public interface DocumentResponseFactory {


    ResponseEntity<byte[]> createResponseEntity(FileExport fileExport);


}
