package com.spring.docon.service;

import com.spring.docon.entity.GenderEntity;
import com.spring.docon.model.Gender;
import com.spring.docon.repository.GenderRepository;
import com.spring.docon.utils.MockUtils;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@Log4j2
class GenderServiceTest {

    @InjectMocks
    private GenderService sut;

    @Mock
    private GenderRepository genderRepository;

    private Gender gender=new Gender();

    private List<GenderEntity> genderEntity= Collections.singletonList(new GenderEntity());


    @BeforeEach
    void setUp() throws IOException{
        log.info("Inside before each method");

        gender.setGender("Male");
    }


    @Test
    void getGender() {
        log.info("Inside getGender()");
        genderEntity=MockUtils.genderEntity();

        Mockito.when(genderRepository.findAll()).thenReturn(genderEntity);

        List<GenderEntity> result=sut.getGender();

        Mockito.verify(genderRepository, Mockito.times(1)).findAll();
        assertEquals(genderEntity,result);
        
    }
}