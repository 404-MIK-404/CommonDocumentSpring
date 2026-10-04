package com.mik.common.domain.mappers;

/**
 * @param <M> Model/Domain
 * @param <D> DTO
 */
public interface DomainMapper<M,D> {

    M toDomain(D dto);

    D toDto(M domain);

}
