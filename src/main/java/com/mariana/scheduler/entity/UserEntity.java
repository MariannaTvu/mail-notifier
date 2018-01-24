package com.mariana.scheduler.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by nicot on 10/5/2017.
 */
@Getter
@Setter
@Data
@Entity(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue
    public Long id;

    public String name;

    public String surname;

    public String password;

    public String email;

    public UserEntity() {
    }

    public UserEntity(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
}
