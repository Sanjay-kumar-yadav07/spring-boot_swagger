package com.springboot.service.impl;

import com.springboot.dao.UserDao;
import com.springboot.model.ImageUpload;
import com.springboot.model.User;
import com.springboot.model.UserDto;
import com.springboot.service.UserService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {
	
	private int maxUploadSizeInMb = 10 * 1024 * 1024;
	
	private static final String IMAGE_DIR="H:/sanju/image_storage";
	
	//private final Path fileStorageLocation;
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUsername(username);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority());
	}

	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	public List<User> findAll() {
		List<User> list = new ArrayList<>();
		userDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(int id) {
		userDao.deleteById(id);
	}

	@Override
	public User findOne(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public User findById(int id) {
		Optional<User> optionalUser = userDao.findById(id);
		return optionalUser.isPresent() ? optionalUser.get() : null;
	}

    @Override
    public UserDto update(UserDto userDto) {
        User user = findById(userDto.getId());
        if(user != null) {
            BeanUtils.copyProperties(userDto, user, "password");
            userDao.save(user);
        }
        return userDto;
    }

    @Override
    public User save(UserDto user) {
	    User newUser = new User();
	    newUser.setUsername(user.getUsername());
	    newUser.setFirstName(user.getFirstName());
	    newUser.setLastName(user.getLastName());
	    newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setAge(user.getAge());
		newUser.setSalary(user.getSalary());
		newUser.setAddress(user.getAddress());
		// newUser.setFiles(user.getFiles());
        return userDao.save(newUser);
    }

	@Override
	public User findByLastName(String lastName) {
		// TODO Auto-generated method stub
		User findByLastName = userDao.findByLastName(lastName);
		return findByLastName;
	}

	@Override
	public User getUser(int id) {
	
		User user=userDao.getUser(id);
		return user;
	}

	@Override
	public User saveUserFile(UserDto user,MultipartFile[] files) {
		 User newUser = new User();
		 
		 
		 
		    newUser.setUsername(user.getUsername());
		    newUser.setFirstName(user.getFirstName());
		    newUser.setLastName(user.getLastName());
		    newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
			newUser.setAge(user.getAge());
			newUser.setSalary(user.getSalary());
			newUser.setAddress(user.getAddress());
			 newUser.setImageUploadList(getImageUploadList(files));
	        return userDao.save(newUser);
		
	}
	
	public List<ImageUpload> getImageUploadList(MultipartFile[] files) {

		Path fileStorageLocation = Paths.get(IMAGE_DIR).toAbsolutePath().normalize();
		List<ImageUpload> imageUploadList = new ArrayList<>();
		try {
			Files.createDirectories(fileStorageLocation);
			Arrays.asList(files).stream().forEach(file -> {

				String fileName = StringUtils.cleanPath(file.getOriginalFilename());

				Path targetLocation = fileStorageLocation.resolve(fileName);

				try {
					Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				ImageUpload imageUpload = new ImageUpload(IMAGE_DIR + "/" + fileName);
				imageUploadList.add(imageUpload);

			});
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return imageUploadList;

	}
    
    
}
