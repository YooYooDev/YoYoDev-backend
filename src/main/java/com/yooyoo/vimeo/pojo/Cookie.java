package com.yooyoo.vimeo.pojo;

public class Cookie {
	

	 private float scaling;
	 private float volume;
	 private String quality = null;
	 private float hd;
	 private String captions = null;


	 // Getter Methods 

	 public float getScaling() {
	  return scaling;
	 }

	 public float getVolume() {
	  return volume;
	 }

	 public String getQuality() {
	  return quality;
	 }

	 public float getHd() {
	  return hd;
	 }

	 public String getCaptions() {
	  return captions;
	 }

	 // Setter Methods 

	 public void setScaling(float scaling) {
	  this.scaling = scaling;
	 }

	 public void setVolume(float volume) {
	  this.volume = volume;
	 }

	 public void setQuality(String quality) {
	  this.quality = quality;
	 }

	 public void setHd(float hd) {
	  this.hd = hd;
	 }

	 public void setCaptions(String captions) {
	  this.captions = captions;
	 }


}
