package com.mik.common.event.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mik.common.event.aop.DynamicMessageContext;
import com.mik.common.event.entities.FieldChange;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChangeMessageBuilder {

    private final EntityComparisonService service;
    private final ObjectMapper mapper;

    public <T> String buildChangeMessage(String prefix, final T oldEntity, final T newEntity) {
        Map<String, FieldChange> changes = service.compare(oldEntity, newEntity);

        StringBuilder message = new StringBuilder(prefix);

        if (!changes.isEmpty()) {
            DynamicMessageContext.setModifiedFields(changes);
            changes.forEach((fieldComment, change) -> {
                message.append(". Поле - ")
                       .append(fieldComment)
                       .append(": ")
                       .append(change.getOldValue())
                       .append(" ---> ")
                       .append(change.getNewValue());
            });
        }
        return message.toString();
    }

    public <T> T deepCopy(T entity, Class<T> tClass) {
        try {
            return mapper.readValue(mapper.writeValueAsString(entity), tClass);
        } catch (Exception e) {
            throw new RuntimeException("Failed to deep copy", e);
        }
    }
}
