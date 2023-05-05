package com.spring.docon.mapper;

import com.spring.docon.entity.base.BaseEntity;
import com.spring.docon.model.base.Base;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BaseEntityMapper {

    BaseEntity modelToEntity(Base base);
}