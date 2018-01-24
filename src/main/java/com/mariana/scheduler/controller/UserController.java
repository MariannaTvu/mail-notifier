package com.mariana.scheduler.controller;

import com.google.gson.Gson;
import com.mariana.scheduler.entity.NoteEntity;
import com.mariana.scheduler.entity.UserEntity;
import com.mariana.scheduler.entity.dto.NoteDto;
import com.mariana.scheduler.entity.dto.UserDto;
import com.mariana.scheduler.mail.Mail;
import com.mariana.scheduler.repository.NoteRepository;
import com.mariana.scheduler.repository.UserRepository;
import com.mariana.scheduler.service.NoteEmailNotificationJob;
import com.mariana.scheduler.service.NoteService;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("user")
@RequestMapping(value = "user", produces = "application/json")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    NoteService noteService;

    @RequestMapping("/test")
    public ResponseEntity index() {
        UserEntity userEntity = userRepository.findOne(1L);
        List<NoteEntity> noteEntities = noteRepository.findByUserId(userEntity.id);
        return new ResponseEntity(new Gson().toJson(noteEntities), HttpStatus.OK);
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody UserDto userDto) {
        userRepository.save(new UserEntity(userDto.getName(), userDto.getSurname(), userDto.getEmail()));
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "note", method = RequestMethod.PUT)
    public ResponseEntity saveNewNote(@RequestBody NoteDto noteDto) {
        noteService.sceduleReminder(noteService.saveNote(noteDto));
        return new ResponseEntity(HttpStatus.OK);
    }
}
