package com.spring.docon.service;

import com.spring.docon.entity.GenderEntity;
import com.spring.docon.repository.GenderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class GenderService {

    private final GenderRepository genderRepository;

    public GenderService(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    public List<GenderEntity> getGender() {

        log.info("Retrieving genders.");

        List<GenderEntity> gender = genderRepository.findAll();

        if (!gender.isEmpty()) {
            return gender;
        } else {
            throw new RuntimeException("Genders not found.");
        }
    }
}