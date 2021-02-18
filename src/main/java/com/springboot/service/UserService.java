package com.springboot.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.springboot.model.User;
import com.springboot.model.UserDto;

public interface UserService {

    User save(UserDto user);
    List<User> findAll();
    void delete(int id);

    User findOne(String username);

    User findById(int id);

    UserDto update(UserDto userDto);
     
    User findByLastName(String lastName);
    
    public User getUser(int id);
    
    User saveUserFile(UserDto user,MultipartFile[] files);
    
}
