package com.mik.common.documents.pdf.factory;

import com.mik.common.documents.shared.factory.DocumentResponseFactory;
import com.mik.common.documents.shared.mediatype.DocumentMediaTypes;
import com.mik.common.documents.shared.model.FileExport;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
@AllArgsConstructor
public class DocumentPdfResponseFactory implements DocumentResponseFactory {

    @Override
    public ResponseEntity<byte[]> createResponseEntity(FileExport fileExport) {
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                                          "attachment; filename=\"report.docx\"; filename*=UTF-8''" + URLEncoder.encode(fileExport.name(), StandardCharsets.UTF_8))
                             .contentType(DocumentMediaTypes.APPLICATION_PDF)
                             .contentLength(fileExport.resource().length)
                             .body(fileExport.resource());
    }
}
