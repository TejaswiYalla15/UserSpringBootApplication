package com.user.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.demo.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long>{

}
