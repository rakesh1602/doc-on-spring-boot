package com.spring.docon.service;

import com.spring.docon.entity.AccountEntity;
import com.spring.docon.entity.ProviderEntity;
import com.spring.docon.entity.UserRegisterEntity;
import com.spring.docon.mapper.ProviderMapper;
import com.spring.docon.model.Provider;
import com.spring.docon.repository.ProviderRepository;
import com.spring.docon.response.ProviderResponse;
import com.spring.docon.utils.MockUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProviderServiceTest {

    @InjectMocks
    private ProviderService providerService;

    @Mock
    private ProviderRepository providerRepository;

    @Mock
    private ProviderMapper providerMapper;

    private Provider provider;

    private ProviderEntity providerEntity=new ProviderEntity();

    @Test
    public void testAddProvider() {

        provider = MockUtils.provider();

        providerEntity = MockUtils.providerEntity();

        when(providerMapper.modelToEntity(provider)).thenReturn(providerEntity);
        when(providerRepository.save(providerEntity)).thenReturn(providerEntity);

        ProviderResponse expectedResponse = new ProviderResponse();
        expectedResponse.setProviderId(providerEntity.getProviderId());

        ProviderResponse actualResponse = providerService.addProvider(provider);

        assertEquals(expectedResponse.getProviderId(), actualResponse.getProviderId());
    }


 /*   @Test
    public void testGetById() {

        Long providerId = 1L;
        providerEntity = MockUtils.providerEntity();
        providerEntity.setUser(new UserRegisterEntity());

        when(providerRepository.findById(providerId)).thenReturn(Optional.of(providerEntity));

        Provider result = providerService.getById(providerEntity.getProviderId());

        //assertEquals(providerId, result);
        assertEquals("12345", result.getMciRegistrationNumber());
        assertEquals("5 years", result.getExperience());
    }*/

    @Test
    public void testGetAllprovider() {

        List<ProviderEntity> providerEntities = new ArrayList<>();
        ProviderEntity providerEntity = new ProviderEntity();
        providerEntities.add(providerEntity);

        List<Provider> providers = new ArrayList<>();
        Provider provider = new Provider();
        providers.add(provider);

        when(providerRepository.findAll()).thenReturn(providerEntities);
        when(providerMapper.entityToModels(providerEntities)).thenReturn(providers);

        List<Provider> result = providerService.getAllprovider();

        assertEquals(providers, result);
    }

    @Test
    public void testDeleteProviderById() {

        Long providerId = 1L;
        when(providerRepository.findById(providerId)).thenReturn(Optional.of(new ProviderEntity(providerId, "MCI123", "10 years experience", new UserRegisterEntity())));

        providerService.deleteProviderById(providerId);

        Mockito.verify(providerRepository, Mockito.times(1)).findById(providerId);
        Mockito.verify(providerRepository, Mockito.times(1)).deleteById(providerId);
    }

    /*@Test
    public void testUpdateProviders() {
        // Arrange
        Long providerId = 1L;
        Provider provider = new Provider();
        provider.setMciRegistrationNumber("ABC123");
        provider.setExperience("5 years");

        ProviderEntity oldProviderEntity = new ProviderEntity();
        oldProviderEntity.setProviderId(providerId);
        UserRegisterEntity userRegisterEntity = new UserRegisterEntity();
        userRegisterEntity.setUserId(1L);
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountId(1L);
        userRegisterEntity.setAccountEntity(accountEntity);
        oldProviderEntity.setUser(userRegisterEntity);

        ProviderEntity newProviderEntity = new ProviderEntity();
        newProviderEntity.setProviderId(providerId);
        UserRegisterEntity newUserRegisterEntity = new UserRegisterEntity();
        newUserRegisterEntity.setUserId(1L);
        AccountEntity newAccountEntity = new AccountEntity();
        newAccountEntity.setAccountId(1L);
        newUserRegisterEntity.setAccountEntity(newAccountEntity);
        newProviderEntity.setUser(newUserRegisterEntity);

        when(providerRepository.findById(providerId)).thenReturn(Optional.of(oldProviderEntity));
        when(providerRepository.save(any(ProviderEntity.class))).thenReturn(newProviderEntity);

        Provider updatedProvider = providerService.updateProviders(providerId, provider);

        assertEquals(provider.getMciRegistrationNumber(), updatedProvider.getMciRegistrationNumber());
        assertEquals(provider.getExperience(), updatedProvider.getExperience());
    }*/


}

