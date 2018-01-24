package com.mariana.scheduler.repository;

import com.mariana.scheduler.entity.NoteEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by nicot on 10/5/2017.
 */
@Repository
public interface NoteRepository extends CrudRepository<NoteEntity, Long> {

    List<NoteEntity> findByUserId(Long userId);
}
