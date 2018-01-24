package com.mariana.scheduler.entity.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by nicot on 1/24/2018.
 */
@ApiModel
@Getter
@Setter
public class UserDto {
    private final String name;
    private final String surname;
    private final String email;

    @JsonCreator
    public UserDto(@JsonProperty("name") String name,
                   @JsonProperty("surname") String surname,
                   @JsonProperty("email") String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
}
