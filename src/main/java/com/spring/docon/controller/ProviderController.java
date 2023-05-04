package com.spring.docon.controller;

import com.spring.docon.model.Provider;
import com.spring.docon.response.ProviderResponse;
import com.spring.docon.service.ProviderService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Provider", description = "Provider add, get, delete methods")
@RequestMapping(path = "v1")
@CrossOrigin(originPatterns = "*")
@RestController
public class ProviderController {

    private final ProviderService providerService;

    @Autowired
    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    @ApiResponse(responseCode = "404", description = "Not found")
    @ApiResponse(responseCode = "500", description = "System error")
    @PostMapping(path = "/providers", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProviderResponse> addProvider(@RequestBody Provider provider) {

        ProviderResponse providerResponse = providerService.addProvider(provider);

        return new ResponseEntity<>(providerResponse, HttpStatus.OK);
    }

    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    @ApiResponse(responseCode = "404", description = "Not found")
    @ApiResponse(responseCode = "500", description = "System error")
    @GetMapping(value = "/providers/{providerId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Provider> getById(@PathVariable Long providerId) {

        Provider provider = providerService.getById(providerId);

        return new ResponseEntity<>(provider, HttpStatus.OK);
    }

    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    @ApiResponse(responseCode = "404", description = "Not found")
    @ApiResponse(responseCode = "500", description = "System error")
    @GetMapping(value = "/providers", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Provider>> getAllProvider() {

        List<Provider> provider = providerService.getAllprovider();

        return new ResponseEntity<>(provider, HttpStatus.OK);
    }

    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    @ApiResponse(responseCode = "404", description = "Not found")
    @ApiResponse(responseCode = "500", description = "System error")
    @PutMapping(value = "/providers/{providerId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Provider> updateProvider(@PathVariable Long providerId, @RequestBody Provider provider) {

        Provider provider1 = providerService.updateProviders(providerId, provider);

        return new ResponseEntity<>(provider1, HttpStatus.OK);
    }

    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    @ApiResponse(responseCode = "404", description = "Not found")
    @ApiResponse(responseCode = "500", description = "System error")
    @DeleteMapping(value = "/providers/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteProviderById(@PathVariable Long providerId) {

        providerService.deleteProviderById(providerId);

        return ResponseEntity.ok().build();
    }
}
