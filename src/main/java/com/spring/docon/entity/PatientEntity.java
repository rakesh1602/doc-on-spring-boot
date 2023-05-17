package com.spring.docon.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PatientEntity {

    private Long patientId;

    private String bloodGroup;

    private Integer height;

    private Integer weight;

    private UserRegisterEntity userRegisterEntity;
}




