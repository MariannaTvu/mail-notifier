package com.mariana.scheduler.controller;

import com.mariana.scheduler.entity.NoteEntity;
import com.mariana.scheduler.entity.UserEntity;
import com.mariana.scheduler.entity.dto.NoteDto;
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
public class UserController {




    @Autowired
    UserRepository userRepository;

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    NoteService noteService;

    @Autowired
    NoteEmailNotificationJob noteJob;

    @RequestMapping("/")
    public String index() {
        UserEntity userEntity = userRepository.findOne(1L);
        List<NoteEntity> noteEntities = noteRepository.findByUserId(userEntity.id);
        return noteEntities.toString();
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody UserEntity userEntity) {
        userRepository.save(userEntity);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "note", method = RequestMethod.PUT)
    public ResponseEntity saveNewNote(@RequestBody NoteDto noteDto) {
        noteService.saveNote(noteDto);
        Mail mail = new Mail("nico.tvu@gmail.com", "hello", "fff", "dfe", "fefe");
        noteJob.setTriggerDate("2018-01-07");
        try {
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.getContext().put("mail", mail);
            scheduler.getContext().put("date", "2018-01-07");
            scheduler.scheduleJob(noteJob.getJob(), noteJob.getTrigger());
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.OK);
    }

}
