package com.mik.common.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PageResponse<T> {
    List<T> content;
    long totalElements;
    int totalPages;
    int currentPage;
}
