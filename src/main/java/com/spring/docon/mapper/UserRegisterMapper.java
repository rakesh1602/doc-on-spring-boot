package com.spring.docon.mapper;

import com.spring.docon.entity.UserRegisterEntity;
import com.spring.docon.model.UserRegister;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface UserRegisterMapper {

    UserRegisterEntity modelToEntity(UserRegister userRegister);

    UserRegister entityToModel(Optional<UserRegisterEntity> userRegisterEntity);

    List<UserRegister> entityToModels(List<UserRegisterEntity> userRegisterEntities);
}
