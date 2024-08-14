package com.project.authtemplate.global.common.entity

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import lombok.experimental.SuperBuilder
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@SuperBuilder
@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity {

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected var createdDateTime: LocalDateTime? = null

    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected var modifiedDateTime: LocalDateTime? = null

}