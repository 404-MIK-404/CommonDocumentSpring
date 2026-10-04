package com.mik.common.repository.mappers;

import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * @param <E> Entity
 * @param <M> Model/Domain
 */
public interface EntityMapper<E, M>{

    @Mapping(target = "deleted", source = "isDeleted")
    E toEntity(M m);

    @Mapping(target = "isDeleted", source = "deleted")
    M toDomain(E t);

    @Mapping(target = "deleted", source = "isDeleted")
    @Mapping(target = "dateModify", ignore = true)
    @Mapping(target = "userModify", ignore = true)
    E updateTarget(M source, @MappingTarget E target);


}
