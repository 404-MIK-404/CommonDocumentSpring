package com.mik.common.api.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Базовый контроллер для CRUD-операций.
 *
 * @param <T>  тип DTO
 * @param <ID> тип идентификатора (UUID, Long и т.д.)
 */
public abstract class CrudController<T, ID> {

    /**
     * Получение всех записей, включая удалённые. Есть вариант получение записей с фильтрацией по признаку удаления
     * @param isDeleted признак удаления
     * @return список всех записей или список записей, соответствующих указанному признаку удаления
     */
    @GetMapping("/")
    public abstract List<T> findAll(
            @RequestParam(required = false) Boolean isDeleted
    );

    /**
     * Получение записи по идентификатору
     * @param id Идентификатор строки
     * @return запись
     */
    @GetMapping("/{id}")
    public abstract T findById(@PathVariable("id") ID id);

    /**
     * Создание новой записи
     * @param dto DTO для создания записи
     * @return созданная запись
     */
    @PostMapping("/")
    public abstract T create(@RequestBody T dto);

    /**
     * Обновление существующей записи
     * @param dto DTO для обновления записи
     * @return обновлённая запись
     */
    @PatchMapping("/")
    public abstract T update(@RequestBody T dto);

    /**
     * Мягкое удаление записи по идентификатору
     * @param id Идентификатор строки
     * @return ответ без содержимого
     */
    @DeleteMapping("/{id}")
    public abstract ResponseEntity<Void> delete(@PathVariable("id") ID id);

}
