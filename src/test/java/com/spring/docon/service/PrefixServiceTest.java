package com.spring.docon.service;

import com.spring.docon.entity.PrefixEntity;
import com.spring.docon.repository.PrefixRepository;
import com.spring.docon.utils.MockUtils;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@Log4j2
class PrefixServiceTest {

    @InjectMocks
    private PrefixService sut;

    @Mock
    private PrefixRepository prefixRepository;

    private List<PrefixEntity> prefixEntity= Collections.singletonList(new PrefixEntity());

    @Test
    void getPrefix() {

        prefixEntity= MockUtils.prefixEntities();

        Mockito.when(prefixRepository.findAll()).thenReturn(prefixEntity);

        List<PrefixEntity> result=sut.getPrefix();

        Mockito.verify(prefixRepository,Mockito.times(1)).findAll();
        assertEquals(prefixEntity,result);
    }
}