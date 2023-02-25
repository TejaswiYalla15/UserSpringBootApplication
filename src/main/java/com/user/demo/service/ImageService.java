package com.user.demo.service;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;


import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.user.demo.exception.ResourceNotFoundException;
import com.user.demo.model.Image;
import com.user.demo.model.User;
import com.user.demo.repository.ImageRepository;
import com.user.demo.repository.UserRepository;

@Service
public class ImageService {

	 private static final Logger LOGGER = LoggerFactory.getLogger(ImageService.class);

	@Autowired
	ImageRepository imageRepo;
	
	@Autowired
	UserRepository userRepo;
	
//	Client ID is generated when we register our application in IMGUR 
	private static final String IMGUR_CLIENT_ID = "29c3eb6600919ab";
	
	public Optional<Image> UploadImage(Long userId, MultipartFile image) throws Exception {
		
//		Calculating the current timestamp to keep track of when images are created
		Date date = new Date();
		long time = date.getTime();
		Timestamp dateTime = new Timestamp(time);
		
//		Adding Authorization to the headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		headers.add("Authorization", "Client-ID " + IMGUR_CLIENT_ID);
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("image", image.getBytes());
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
		      
//		Integrating Imgur's API to upload the image
		String url = "https://api.imgur.com/3/image";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
		String output = response.getBody();
		LOGGER.info(output);
		
//		Converting the String obtained into JSONObject
		Object obj=JSONValue.parse(output);   
		JSONObject jsonObject = (JSONObject) obj; 
		
//		Retrieving the required fields from the JSONObject
		JSONObject Img_data	=  (JSONObject) jsonObject.get("data");
		String Image_link = (String) Img_data.get("link");
		LOGGER.info("Link of the image " + Image_link);
		String Image_deletehash = (String) Img_data.get("deletehash");
		LOGGER.info("DeleteHash " + Image_deletehash);
		String Image_Hash = (String) Img_data.get("id");
		LOGGER.info("Image Hash " + Image_Hash);
		
//		Retrieve the user based on userId given
		User u = userRepo.findById(userId).orElse(null);
		Image image1 = new Image(Image_link,Image_deletehash,Image_Hash, u,dateTime);
		
//		Map the user with the image
		return userRepo.findById(userId).map(user ->{
			user.getUser_images().add(image1);
			return imageRepo.save(image1);
		});				
	}
	
	
	public String viewImage(Long imageId) throws ResourceNotFoundException {

//		Retrieve the image based on the imageId
		Image i = imageRepo.findById(imageId)
				.orElseThrow(() -> new ResourceNotFoundException("Image is not found with the id :: " + imageId));
		
		HttpHeaders headers = new HttpHeaders();
	    HttpEntity <String> entity = new HttpEntity<String>(headers);

//	    Get the link of the image stored in the table
		String url = i.getImageLink();
		LOGGER.info("Get image link "+url);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		return url;
	}
	
	
	public String DeleteImage(Long imageid) throws ResourceNotFoundException{
		
//		Retrieve the image to be deleted based on the imageid
		Image i = imageRepo.findById(imageid)
				.orElseThrow(() -> new ResourceNotFoundException("Image is not found with the id :: " + imageid));
		
//		Add authorization to the headers
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Client-ID " + IMGUR_CLIENT_ID);
		HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

//		Get the "deletehash" value of the image stored in the table
		String imagedeletehash = i.getImagedeleteHash();
		LOGGER.info("Image Delete hash "+ imagedeletehash);
//		Integrating the Imgur's delete image API
		String url = "https://api.imgur.com/3/image"+"/"+imagedeletehash;
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, String.class);
		return response.getBody();
	}
}
