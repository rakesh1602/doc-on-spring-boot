package com.spring.docon.controller;

import com.spring.docon.entity.PrefixEntity;
import com.spring.docon.service.PrefixService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Prefix", description = "Get Prefix")
@RequestMapping(path = "v1")
@RestController
@CrossOrigin(originPatterns = "*")
public class PrefixController {

    private final PrefixService prefixService;

    @Autowired
    public PrefixController(PrefixService prefixService){
        this.prefixService=prefixService;
    }

    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    @ApiResponse(responseCode = "404", description = "Not found")
    @ApiResponse(responseCode = "500", description = "System error")
    @GetMapping(path = "/prefixs", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PrefixEntity>> Gender(){

        List<PrefixEntity> prefix=prefixService.getPrefix();

        return new ResponseEntity<>(prefix, HttpStatus.OK);
    }
}
