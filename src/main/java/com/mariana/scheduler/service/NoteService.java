package com.mariana.scheduler.service;

import com.mariana.scheduler.entity.NoteEntity;
import com.mariana.scheduler.entity.UserEntity;
import com.mariana.scheduler.entity.dto.NoteDto;
import com.mariana.scheduler.mail.Mail;
import com.mariana.scheduler.repository.NoteRepository;
import com.mariana.scheduler.repository.UserRepository;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * Created by nicot on 1/6/2018.
 */
@Service
public class NoteService {
    private final String STANDART_SUBJECT = "Hello, you have a reminder for today!";
    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    NoteEmailNotificationJob noteJob;

    public NoteEntity saveNote(NoteDto noteDto) {
        NoteEntity noteEntity = new NoteEntity();
        noteEntity.setUserId(noteDto.getUserId());
        noteEntity.setText(noteDto.getText());
        noteEntity.setTitle(noteDto.getTitle());
        noteEntity.setExpireDate(LocalDate.parse(noteDto.getExpireDate()));
        return noteRepository.save(noteEntity);
    }

    public void sceduleReminder(NoteEntity noteEntity) {
        UserEntity user = userRepository.findOne(noteEntity.getUserId());
        Mail mail = new Mail(user.getEmail(),
                STANDART_SUBJECT,
                user.getName(),
                noteEntity.getTitle(),
                noteEntity.getText());
                noteJob.setTriggerDate(noteEntity.getExpireDate());
        try {
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.getContext().put("mail", mail);
            scheduler.getContext().put("date", noteEntity.getExpireDate());
            scheduler.scheduleJob(noteJob.getJob(), noteJob.getTrigger());
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
