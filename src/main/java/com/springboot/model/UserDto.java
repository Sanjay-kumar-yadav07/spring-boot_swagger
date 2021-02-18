package com.springboot.model;

import java.util.List;

public class UserDto {

    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private int age;
    private int salary;
    
    private Address address;
    
    //private List<byte[]> files;
    
    private List<ImageUpload> imageUploadList;
    

   

	/*
	 * public List<byte[]> getFiles() { return files; }
	 * 
	 * public void setFiles(List<byte[]> files) { this.files = files; }
	 */
    
    
    

	public Address getAddress() {
		return address;
	}

	public List<ImageUpload> getImageUploadList() {
		return imageUploadList;
	}

	public void setImageUploadList(List<ImageUpload> imageUploadList) {
		this.imageUploadList = imageUploadList;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

	
}
