package com.mik.common.domain.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;


@MapperConfig(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.FIELD,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface DomainMapperConfig {}
