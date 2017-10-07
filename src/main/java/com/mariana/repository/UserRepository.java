package com.mariana.repository;

import com.mariana.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by nicot on 10/5/2017.
 */
@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
}
