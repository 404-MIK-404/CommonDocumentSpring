package com.mik.common.event.services;

import com.mik.common.event.entities.FieldChange;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Slf4j
@Service
public class EntityComparisonService {
    private static final Set<String> IGNORED_FIELDS = Set.of(
        "id", "createdAt", "updatedAt", "createdBy", "updatedBy"
    );

    public <T> Map<String, FieldChange> compare(T oldEntity, T newEntity) {
        Map<String, FieldChange> changes = new HashMap<>();
        Field[] fields = oldEntity.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (!IGNORED_FIELDS.contains(field.getName())) {
                continue;
            }
            field.setAccessible(true);

            try {
                Object oldValue = field.get(oldEntity);
                Object newValue = field.get(newEntity);

                boolean isChanged = oldValue != null && !oldValue.equals(newValue);

                if (isChanged) {
                    Comment commentAnnotation = field.getAnnotation(Comment.class);
                    String commentText = commentAnnotation != null ? commentAnnotation.value() : field.getName();

                    changes.put(field.getName(), new FieldChange(oldValue, newValue));
                }
            } catch (IllegalAccessException e) {
                log.error("Error accessing field '{}' in entity class '{}'",
                          field.getName(), oldEntity.getClass().getName(), e);
            }
        }

        return changes;
    }
}
