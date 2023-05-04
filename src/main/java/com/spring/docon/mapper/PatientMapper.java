package com.spring.docon.mapper;

import com.spring.docon.entity.PatientEntity;
import com.spring.docon.model.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    @Mapping(source = "person", target = "userRegisterEntity")
    @Mapping(source = "person.account", target = "userRegisterEntity.accountEntity")
    PatientEntity modelToEntity(Patient patient);

    List<Patient> entityToModels(List<PatientEntity> patient);

    Patient entityToModel(Optional<PatientEntity> patient);
}