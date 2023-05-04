package com.spring.docon.mapper;

import com.spring.docon.entity.UserRegisterEntity;
import com.spring.docon.model.UserRegister;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
<<<<<<< HEAD
import java.util.Optional;
=======
>>>>>>> f5e8be4199568b1c8165cf53086ebe5c43b8cd83

@Mapper(componentModel = "spring")
public interface UserRegisterMapper {

    UserRegisterEntity modelToEntity(UserRegister userRegister);

    List<UserRegister> entityToModels(List<UserRegisterEntity> userRegister);

<<<<<<< HEAD
    UserRegister entityToModel (Optional<UserRegisterEntity> userRegisterEntity);
=======
    UserRegister entityToModel (UserRegisterEntity userRegister);


>>>>>>> f5e8be4199568b1c8165cf53086ebe5c43b8cd83
}
