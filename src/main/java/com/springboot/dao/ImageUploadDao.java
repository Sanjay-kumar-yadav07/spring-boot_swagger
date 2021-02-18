package com.springboot.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.model.ImageUpload;

@Repository
public interface ImageUploadDao extends JpaRepository<ImageUpload, Long>{

}
