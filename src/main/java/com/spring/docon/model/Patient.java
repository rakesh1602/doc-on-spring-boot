package com.spring.docon.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Patient {

    private String bloodGroup;

    private Integer height;

    private Integer weight;

    private UserRegister person =new UserRegister();
}
