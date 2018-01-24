package com.mariana.scheduler.service;

import com.mariana.scheduler.entity.NoteEntity;
import com.mariana.scheduler.entity.dto.NoteDto;
import com.mariana.scheduler.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * Created by nicot on 1/6/2018.
 */
@Service
public class NoteService {
    @Autowired
    private NoteRepository noteRepository;

    public void saveNote(NoteDto noteDto) {
        NoteEntity noteEntity = new NoteEntity();
        noteEntity.setUserId(noteDto.getUserId());
        noteEntity.setText(noteDto.getText());
        noteEntity.setTitle(noteDto.getTitle());
        noteEntity.setExpireDate(LocalDate.parse(noteDto.getExpireDate()));
        noteRepository.save(noteEntity);
    }
}
