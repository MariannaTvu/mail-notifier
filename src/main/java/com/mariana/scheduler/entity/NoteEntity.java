package com.mariana.scheduler.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by nicot on 10/5/2017.
 */
@Getter
@Setter
@Data
@Entity(name = "note")
public class NoteEntity {
    @Id
    @GeneratedValue
    private Long id;

    public String text;

    public String title;

    public LocalDate expireDate;

    public Long userId;
}
