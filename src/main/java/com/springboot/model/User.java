package com.springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String username;
    @Column
    @JsonIgnore
    private String password;
    @Column
    private long salary;
    @Column
    private int age;
    
    @OneToMany(targetEntity = ImageUpload.class ,cascade = CascadeType.ALL)
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private List<ImageUpload> imageUploadList;
   
   
    
    
    
   // @Column(name="address_id")
    
	
	  @Lob //@Column(length=100000)
	  
	  @ElementCollection
		/*
		 * @CollectionTable(name="image_table",joinColumns
		 * = @JoinColumn(name="user_id"))
		 * 
		 * @Column(name="image", length=100000) private List<byte[]> files=new
		 * ArrayList() ;
		 * 
		 */
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
 
    
    
    
    
    private Address address;

	/*
	 * public List<byte[]> getFiles() { return files; }
	 * 
	 * public void setFiles(List<byte[]> files) { this.files = files; }
	 */

	public int getId() {
        return id;
    }

    public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
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

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

	public List<ImageUpload> getImageUploadList() {
		return imageUploadList;
	}

	public void setImageUploadList(List<ImageUpload> imageUploadList) {
		this.imageUploadList = imageUploadList;
	}

	
}
