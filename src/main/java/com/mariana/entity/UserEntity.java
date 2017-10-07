package com.mariana.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by nicot on 10/5/2017.
 */

@Entity   (name = "user")
public class UserEntity {
    @Id
    @GeneratedValue
    public Long id;

    @Column
    public String name;

    @Column
    public String surname;

    @Column
    public String email;

    @OneToMany(targetEntity = NoteEntity.class, fetch = FetchType.EAGER, mappedBy = "user")
    public List<NoteEntity> notes;

    public String getName() {
        return name;
    }

    public UserEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public UserEntity setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public List<NoteEntity> getNotes() {
        return notes;
    }

    public UserEntity setNotes(List<NoteEntity> notes) {
        this.notes = notes;
        return this;
    }
}
