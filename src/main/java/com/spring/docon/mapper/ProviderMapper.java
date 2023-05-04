package com.spring.docon.mapper;

import com.spring.docon.entity.ProviderEntity;
import com.spring.docon.model.Provider;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
<<<<<<< HEAD
=======

>>>>>>> f5e8be4199568b1c8165cf53086ebe5c43b8cd83
public interface ProviderMapper {

    ProviderEntity modelToEntity(Provider provider);

    List<Provider> entityToModels(List<ProviderEntity> provider);

    Provider entityToModel(ProviderEntity provider);
<<<<<<< HEAD
=======



>>>>>>> f5e8be4199568b1c8165cf53086ebe5c43b8cd83
}
