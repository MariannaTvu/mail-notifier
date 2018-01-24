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
@Entity(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue
    public Long id;

    public String name;

    public String surname;

    public String email;
}
