package com.spring.docon.service;


import com.spring.docon.entity.AccountEntity;
import com.spring.docon.entity.ProviderEntity;
import com.spring.docon.entity.UserRegisterEntity;
import com.spring.docon.mapper.ProviderMapper;
import com.spring.docon.model.Provider;
import com.spring.docon.repository.ProviderRepository;
import com.spring.docon.response.ProviderResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Log4j2
@Service
public class ProviderService {

    private final ProviderRepository providerRepository;

    private final ProviderMapper providerMapper;

    private Provider provider;

    private ProviderEntity providerEntity;

    @Autowired
    public ProviderService(ProviderRepository providerRepository, ProviderMapper providerMapper) {

        this.providerRepository = providerRepository;

        this.providerMapper = providerMapper;
    }

    public ProviderResponse addProvider(Provider provider) {

        log.info("Adding provider details");

        ProviderEntity providerEntity = providerMapper.modelToEntity(provider);
        providerRepository.save(providerEntity);
        log.info("Provider details saved successfully.");

        ProviderResponse providerResponse = new ProviderResponse();
        providerResponse.setProviderId(providerEntity.getProviderId());
        log.info("Response id : {}", providerResponse.getProviderId());
        return providerResponse;
    }


    public Provider getById(Long providerId) {

        log.info("Retrieving provider details for provider id {}", providerId);

        Optional<ProviderEntity> providerEntity = Optional.ofNullable(providerRepository.findById(providerId)
                .orElseThrow(() -> new RuntimeException("Provider id not found {}" + providerId)));

        provider = providerMapper.entityToModel(providerEntity.get());
        return provider;

    }

    public List<Provider> getAllprovider() {

        log.info("Fetching all Providers");

        List<ProviderEntity> providerEntity = providerRepository.findAll();

        if (!providerEntity.isEmpty()) {
            return providerMapper.entityToModels(providerEntity);
        } else {
            throw new RuntimeException("Provider not found.");
        }
    }

    public void deleteProviderById(Long providerId) {

        log.info("Retrieving provider details for provider id {}", providerId);

        Optional<ProviderEntity> providerEntity = Optional.ofNullable(providerRepository.findById(providerId)
                .orElseThrow(() -> new RuntimeException("Provider id not found {}" + providerId)));

        providerRepository.deleteById(providerId);
        log.info("deleted successfully");
    }


    public Provider updateProviders(Long providerId, Provider provider) {

        log.info("Retrieving patient details for patient id {} ", providerId);

        Optional<ProviderEntity> oldProviderEntity = Optional.of(providerRepository.findById(providerId)
                .orElseThrow(() -> new RuntimeException("provider id not found {}" + providerId)));

        UserRegisterEntity userRegisterEntity = oldProviderEntity.get().getUser();
        Long userId = userRegisterEntity.getUserId();
        AccountEntity accountEntity = oldProviderEntity.get().getUser().getAccount();
        Long accountId = accountEntity.getAccountId();

        ProviderEntity newProviderEntity = providerMapper.modelToEntity(provider);
        newProviderEntity.setProviderId(providerId);
        newProviderEntity.getUser().setUserId(userId);
        newProviderEntity.getUser().getAccount().setAccountId(accountId);

        providerEntity = providerRepository.save(newProviderEntity);

        return providerMapper.entityToModel(newProviderEntity);
    }
}
