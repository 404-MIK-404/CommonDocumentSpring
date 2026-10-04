package com.mik.common.domain.ports.repositories;

import java.util.List;

public interface Repository<T, ID> {

    List<T> findAll();

    T findById(ID id);

    List<T> findAllByDeleted(boolean isDeleted);

    T save(T entity);

    void softDeleteById(ID id);

}
