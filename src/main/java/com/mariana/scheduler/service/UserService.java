package com.mariana.scheduler.service;

import com.mariana.scheduler.entity.UserEntity;
import com.mariana.scheduler.entity.dto.UserDto;
import com.mariana.scheduler.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by nicot on 1/24/2018.
 */
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;


    public UserEntity save(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userDto.getName());
        userEntity.setSurname(userDto.getSurname());
        userEntity.setEmail(userDto.getEmail());
        return userRepository.save(userEntity);
    }
}
