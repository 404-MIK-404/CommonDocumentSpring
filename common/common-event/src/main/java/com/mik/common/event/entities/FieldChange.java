package com.mik.common.event.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FieldChange {
    private Object oldValue;
    private Object newValue;

    @Override
    public String toString() {
        return "FieldChange [oldValue=" + oldValue + ", newValue=" + newValue + "]";
    }
}
