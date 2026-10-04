package com.mik.common.documents.shared.model;

import lombok.Builder;

@Builder
public record FileExport(
        String name,
        byte[] resource
) {
}
