package com.mariana.entity;

import com.mariana.repository.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by nicot on 10/5/2017.
 */
@Entity(name = "note")
public class NoteEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Lob
    @Column
    public String text;

    @Column
    public String title;

    @Column
    public LocalDate expireDate;

    @Column
    public Long user_id;

    @ManyToOne  (targetEntity = UserEntity.class)
    @JoinColumn(name = "id")
    public UserEntity user;

    public String getText() {
        return text;
    }

    public NoteEntity setText(String text) {
        this.text = text;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public NoteEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public NoteEntity setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
        return this;
    }

    public Long getUser_id() {
        return user_id;
    }

    public NoteEntity setUser_id(Long user_id) {
        this.user_id = user_id;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public NoteEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }
}
