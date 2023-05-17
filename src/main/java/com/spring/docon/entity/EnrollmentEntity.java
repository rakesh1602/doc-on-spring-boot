package com.spring.docon.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.Duration;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EnrollmentEntity {

    private UUID enrollmentId;

    private PatientEntity patientEntity;

    private Duration expiry;
}
