package com.user.demo.model;

import java.sql.Timestamp;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "images")
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String ImageLink;
	
	private String ImagedeleteHash;
	
	private String ImageHash;
	
	private Timestamp datetime;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private User user;

	public Image() {
		
	}
	public Image(String ImageLink,String ImagedeleteHash,String ImageHash, User user,Timestamp datetime) {
		super();
		this.ImageLink = ImageLink;
		this.ImagedeleteHash = ImagedeleteHash;
		this.ImageHash = ImageHash;
		this.user = user;
		this.datetime = datetime;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getImageLink() {
		return ImageLink;
	}
	public void setImageLink(String imageLink) {
		ImageLink = imageLink;
	}
	
	public String getImagedeleteHash() {
		return ImagedeleteHash;
	}
	public void setImagedeleteHash(String imagedeleteHash) {
		ImagedeleteHash = imagedeleteHash;
	}
	public String getImageHash() {
		return ImageHash;
	}
	public void setImageHash(String imageHash) {
		ImageHash = imageHash;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Timestamp getDatetime() {
		return datetime;
	}
	public void setDatetime(Timestamp datetime) {
		this.datetime = datetime;
	}
	
}
