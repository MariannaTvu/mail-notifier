package com.mariana.controller;

import com.mariana.entity.NoteEntity;
import com.mariana.entity.UserEntity;
import com.mariana.repository.NoteRepository;
import com.mariana.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    NoteRepository noteRepository;

    @RequestMapping("/")
    public String index() {
        UserEntity userEntity = userRepository.findOne(1L);
//        List<NoteEntity> noteEntities = noteRepository.findByUserId(userEntity.id);
        return userEntity.toString();
    }

}
