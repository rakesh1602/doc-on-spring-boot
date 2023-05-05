package com.spring.docon.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.docon.model.base.Base;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Patient extends Base {

    private String bloodGroup;

    private Integer height;

    private Integer weight;

    private UserRegister person =new UserRegister();
}
