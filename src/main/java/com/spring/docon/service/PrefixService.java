package com.spring.docon.service;

import com.spring.docon.entity.PrefixEntity;
import com.spring.docon.mapper.PrefixMapper;
import com.spring.docon.repository.PrefixRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class PrefixService {

    private final PrefixRepository prefixRepository;
    private final PrefixMapper prefixMapper;

    public PrefixService(PrefixRepository prefixRepository, PrefixMapper prefixMapper) {

        this.prefixRepository = prefixRepository;
        this.prefixMapper = prefixMapper;
    }

    public List<PrefixEntity> getPrefix() {

        log.info("Retrieving prefix.");

        List<PrefixEntity> prefix = prefixRepository.findAll();

        if (!prefix.isEmpty()) {
            return prefix;
        }
        throw new RuntimeException("Prefix data not found");
    }

}
