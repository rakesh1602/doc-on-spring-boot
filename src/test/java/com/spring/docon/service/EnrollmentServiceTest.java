package com.spring.docon.service;

import com.spring.docon.entity.EnrollmentEntity;
import com.spring.docon.entity.PatientEntity;
import com.spring.docon.mapper.EnrollmentMapper;
import com.spring.docon.model.Enrollment;
import com.spring.docon.repository.EnrollmentRepository;
import com.spring.docon.repository.PatientRepository;
import com.spring.docon.response.EnrollmentResponse;
import com.spring.docon.utils.MockUtils;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@Log4j2
class EnrollmentServiceTest {

    @InjectMocks
    private EnrollmentService sut;

    @Mock
    private EnrollmentRepository enrollmentRepository;

    @Mock
    private EnrollmentMapper enrollmentMapper;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private KafkaProducer kafkaProducer;

    private EnrollmentEntity enrollmentEntity=new EnrollmentEntity();

    private EnrollmentResponse enrollmentResponse = new EnrollmentResponse();

    private Enrollment enrollment = new Enrollment();

    private UUID enrollmentId;

    @BeforeEach
    void setUp() throws IOException {
        enrollmentMapper = Mappers.getMapper(EnrollmentMapper.class);
        ReflectionTestUtils.setField(sut, "enrollmentMapper", enrollmentMapper);
        enrollmentId = UUID.randomUUID();
    }

    @Test
    void shouldCreateEnrollmentId() {

        enrollmentEntity = MockUtils.enrollmentEntity();
        Long patientId = enrollmentEntity.getPatientEntity().getPatientId();

        Mockito.when(patientRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(enrollmentEntity.getPatientEntity()));

        Mockito.when(enrollmentRepository.save(any(EnrollmentEntity.class))).thenReturn(enrollmentEntity);

        enrollmentResponse = sut.createEnrollment(patientId, enrollment);

        Mockito.verify(enrollmentRepository, Mockito.atLeastOnce()).save(any());

        assertNotNull(enrollmentResponse);
        assertEquals("80a90253-b6bb-419b-bb95-ae7f54851a34", enrollmentEntity.getEnrollmentId().toString());
        assertEquals(3L, enrollmentEntity.getPatientEntity().getPatientId());
        assertEquals("PT360H", enrollmentEntity.getExpiry().toString());
    }

    @Test
    void shouldReturnEnrollmentId() {

        enrollmentEntity.setEnrollmentId(enrollmentId);

        Mockito.when(enrollmentRepository.findByUUID(any(UUID.class))).thenReturn(enrollmentEntity);

        enrollmentResponse=sut.getEnrollment(enrollmentId);

        assertNotNull(enrollmentResponse);

        assertEquals(enrollmentId, enrollmentResponse.getEnrollmentID());
    }
}