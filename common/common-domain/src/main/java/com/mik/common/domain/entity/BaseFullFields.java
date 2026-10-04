package com.mik.common.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.Instant;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseFullFields<ID extends Serializable> {

    public abstract ID getId();
    public abstract void setId(ID id);

    @Column(name = "is_deleted")
    protected boolean deleted;

    @Column(name = "date_create", updatable = false)
    @CreatedDate
    protected Instant dateCreate;

    @Column(name = "user_create", updatable = false)
    @CreatedBy
    protected String userCreate;

    @Column(name = "date_modify")
    @LastModifiedDate
    protected Instant dateModify;

    @Column(name = "user_modify")
    @LastModifiedBy
    protected String userModify;
}
