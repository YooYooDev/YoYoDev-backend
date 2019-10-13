package com.yooyoo.vimeo.pojo;

public class Owner {

	 private String account_type;
	 private String name;
	 private String img;
	 private String url;
	 private String img_2x;
	 private float id;


	 // Getter Methods 

	 public String getAccount_type() {
	  return account_type;
	 }

	 public String getName() {
	  return name;
	 }

	 public String getImg() {
	  return img;
	 }

	 public String getUrl() {
	  return url;
	 }

	 public String getImg_2x() {
	  return img_2x;
	 }

	 public float getId() {
	  return id;
	 }

	 // Setter Methods 

	 public void setAccount_type(String account_type) {
	  this.account_type = account_type;
	 }

	 public void setName(String name) {
	  this.name = name;
	 }

	 public void setImg(String img) {
	  this.img = img;
	 }

	 public void setUrl(String url) {
	  this.url = url;
	 }

	 public void setImg_2x(String img_2x) {
	  this.img_2x = img_2x;
	 }

	 public void setId(float id) {
	  this.id = id;
	 }


}
