package com.mik.common.documents.excel.model.total;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ExcelGroupTotalDefinition {

    @Builder.Default
    private String title = "";

    @Builder.Default
    private Set<String> groupTotalKeys = new HashSet<>();

    @Builder.Default
    private List<ExcelRuleTotalDefinition> rules = new ArrayList<>();

    @Builder.Default
    private int totalColspan = 0;

}
