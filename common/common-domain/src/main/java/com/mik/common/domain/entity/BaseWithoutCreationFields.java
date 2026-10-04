package com.mik.common.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@MappedSuperclass
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseWithoutCreationFields<ID extends Serializable> {
    public abstract ID getId();
    public abstract void setId(ID id);

    @Column(name = "is_deleted")
    protected boolean deleted;

    @Column(name = "date_modify")
    @LastModifiedDate
    protected Instant dateModify;

    @Column(name = "user_modify")
    @LastModifiedBy
    protected String userModify;
}
