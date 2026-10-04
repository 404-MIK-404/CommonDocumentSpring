package com.mik.common.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FileMessage {
    String name;
    byte[] resource;
}
