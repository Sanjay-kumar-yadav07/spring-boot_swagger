package com.springboot.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.model.ApiResponse;
import com.springboot.model.ImageUpload;
import com.springboot.model.User;
import com.springboot.model.UserDto;
import com.springboot.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")

public class UserController {
	
	Logger log = Logger.getLogger(UserController.class.getName());
	
	

    @Autowired
    private UserService userService;

    @PostMapping
    public ApiResponse<User> saveUser(@RequestBody UserDto user){
    	log.info("UserController:saveUser()::START");
         ApiResponse<User> response=new ApiResponse<>(HttpStatus.OK.value(), "User saved successfully.",userService.save(user));
        log.info("UserController:saveUser()::END");
        return response;
         
    }

    @GetMapping
    public ApiResponse<List<User>> listUser(){
    	log.info("UserController:listUser()::START");
    	ApiResponse<List<User>> response= new ApiResponse<>(HttpStatus.OK.value(), "User list fetched successfully.",userService.findAll());
    	 log.info("UserController:listUserr()::END");
         return response;
          
    }

    @GetMapping("/{id}")
    public ApiResponse<User> getOne(@PathVariable int id){
        return new ApiResponse<>(HttpStatus.OK.value(), "User fetched successfully.",userService.findById(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<UserDto> update(@RequestBody UserDto userDto) {
        return new ApiResponse<>(HttpStatus.OK.value(), "User updated successfully.",userService.update(userDto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable int id) {
        userService.delete(id);
        return new ApiResponse<>(HttpStatus.OK.value(), "User deleted successfully.", null);
    }
    @GetMapping("/lasname/{lastName}")
    public  ApiResponse<User>  getUserByLastName(@PathVariable String lastName) {
    	userService.findByLastName(lastName);
    	return new ApiResponse<>(HttpStatus.OK.value(), "User lastName fetched successfully.",userService.findByLastName(lastName));
    }
    
    @GetMapping("/getUser/{id}")
    public User getUser(@PathVariable int id) {
    	
    	User user = userService.getUser(id);
    	return user;
    }
    @PostMapping("/uploadFileDb")
    public User uploadFileToDb(@RequestParam("file") MultipartFile[] files,String userDetails) {
    	
    	ObjectMapper objectMapper=new ObjectMapper();
    	UserDto userDto=null;
    	User user=null;
    	try {
    		userDto=objectMapper.readValue(userDetails, UserDto.class);
			
    		List<byte[]> arrayList = new ArrayList<>();
    		Arrays.asList(files).stream().forEach(file -> {
    			
    	       try {
    	    	   //Files.copy(file.getInputStream(), IMAGE_DIR, StandardCopyOption.REPLACE_EXISTING);
    	    	   
    	    	   
    	    	   
				byte[] byteFile=file.getBytes();
				
				
				arrayList.add(byteFile);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	        
    	      });
  		//userDto.setFiles(arrayList);
  		user=userService.save(userDto);
		
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		return user;
    }
    
    	 @RequestMapping(value = "/uploadFile", consumes = {"multipart/form-data"}, method = RequestMethod.POST) 
		 public User uploadFile(@RequestParam("file") MultipartFile[] files,String userDetails) {
		    	
		    	ObjectMapper objectMapper=new ObjectMapper();
		    	UserDto userDto=null;
		    	User user=null;
		    	
		    		try {
						userDto=objectMapper.readValue(userDetails, UserDto.class);
						user=userService.saveUserFile(userDto,files);
				
		    		} catch (JsonMappingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (JsonProcessingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    	
				return user;
		      
    }

}
