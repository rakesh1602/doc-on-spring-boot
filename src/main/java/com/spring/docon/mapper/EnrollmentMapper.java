package com.spring.docon.mapper;

import com.spring.docon.entity.EnrollmentEntity;
import com.spring.docon.model.Enrollment;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface EnrollmentMapper {

    EnrollmentEntity modelToEntity(Enrollment enrollment);
}
