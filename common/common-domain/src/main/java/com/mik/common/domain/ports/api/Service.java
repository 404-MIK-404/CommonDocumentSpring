package com.mik.common.domain.ports.api;

import java.util.List;

@Deprecated
public interface Service<T, ID> {

    List<T> findAll();

    List<T> findAllByDeleted(Boolean isDeleted);

    T findById(ID id);

    T save(T entity);

    T update(T entity);

    void delete(ID id);

}
