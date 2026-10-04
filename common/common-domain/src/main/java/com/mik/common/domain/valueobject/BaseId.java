package com.mik.common.domain.valueobject;

import lombok.experimental.SuperBuilder;

import java.util.Objects;

@SuperBuilder
public abstract class BaseId<T> {

    private final T value;

    protected BaseId(final T value) {this.value = value;}

    public T getValue() {
        return this.value;
    }


    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }

    @Override
    public boolean equals(final Object o) {
        if(this == o) return true;
        if(o == null || this.getClass() != o.getClass()) return false;
        final BaseId<?> baseId = (BaseId<?>) o;
        return this.value.equals(baseId.value);
    }
}
