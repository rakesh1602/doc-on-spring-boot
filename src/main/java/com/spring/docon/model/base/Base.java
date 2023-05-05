package com.spring.docon.model.base;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Base {

    private LocalDateTime createdTs;

    private Long createdBy;

    private LocalDateTime updatedTs;

    private Long updatedBy;
}
