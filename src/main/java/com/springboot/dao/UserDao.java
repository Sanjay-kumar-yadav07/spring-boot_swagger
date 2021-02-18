package com.springboot.dao;

import javax.websocket.server.PathParam;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.model.User;

@Repository
public interface UserDao extends CrudRepository<User, Integer> {
 
    User findByUsername(String username);
    
    User findByLastName(String lastName);
    
    @Query(value="select *from user where id =:id",nativeQuery = true)
    User getUser(@PathParam("id") int id);
    
    
    
}
