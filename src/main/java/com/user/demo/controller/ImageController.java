package com.user.demo.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;


import com.user.demo.model.Image;
import com.user.demo.model.User;
import com.user.demo.repository.ImageRepository;
import com.user.demo.repository.UserRepository;
import com.user.demo.service.ImageService;
import com.user.demo.service.UserService;

import static org.springframework.web.bind.annotation.RequestMethod.POST;


@CrossOrigin
@RestController
public class ImageController {
	
	@Autowired
	ImageRepository imageRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	ImageService imageService;
		
//	Upload Image API integrated with Imgur's API to upload the image
	@RequestMapping(path = "/uploadImage/{userId}")
	public Optional<Image> saveImage(@PathVariable("userId") Long userId,
			@RequestParam("image") MultipartFile image) throws Exception{
		return imageService.UploadImage(userId, image);
	}
	
//	View Image API integrated with Imgur's API to get the image
	@GetMapping(path = "/viewImage/{imageId}")
	public String getImage(@PathVariable("imageId") Long imageId) throws Exception{
		return imageService.viewImage(imageId);
	}
	
//	Delete Image API integrated with Imgur's API to delete the image
	@DeleteMapping(path = "/deleteImage/{imageId}")
	public String DeleteImage(@PathVariable("imageId") Long imageId) throws Exception{
		return imageService.DeleteImage(imageId);
	}
	
}
