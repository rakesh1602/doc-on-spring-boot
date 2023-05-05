package com.spring.docon.entity.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@MappedSuperclass
@Data
public class BaseEntity {

    @CreationTimestamp
    @Column(name = "created_ts", updatable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime createdTs;

    @Digits(integer = 19, fraction = 0)
    @NotNull
    @Column(name = "created_by", nullable = false, precision = 19)
    private Long createdBy;

    @UpdateTimestamp
    @Column(name = "updated_ts", insertable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime updatedTs;

    @Digits(integer = 19, fraction = 0)
    @Column(name = "updated_by", precision = 19)
    @NotNull
    private Long updatedBy;
}
