package com.yooyoo.vo;

import com.yooyoo.vimeo.pojo.Embed;
import com.yooyoo.vimeo.pojo.Request;
import com.yooyoo.vimeo.pojo.User;
import com.yooyoo.vimeo.pojo.Video;

public class VimeoVideo {

	 private String cdn_url;
	 private String vimeo_api_url;
	 Request RequestObject;
	 private String player_url;
	 Video VideoObject;
	 User UserObject;
	 Embed EmbedObject;
	 private float view;
	 private String vimeo_url;


	 // Getter Methods 

	 public String getCdn_url() {
	  return cdn_url;
	 }

	 public String getVimeo_api_url() {
	  return vimeo_api_url;
	 }

	 public Request getRequest() {
	  return RequestObject;
	 }

	 public String getPlayer_url() {
	  return player_url;
	 }

	 public Video getVideo() {
	  return VideoObject;
	 }

	 public User getUser() {
	  return UserObject;
	 }

	 public Embed getEmbed() {
	  return EmbedObject;
	 }

	 public float getView() {
	  return view;
	 }

	 public String getVimeo_url() {
	  return vimeo_url;
	 }

	 // Setter Methods 

	 public void setCdn_url(String cdn_url) {
	  this.cdn_url = cdn_url;
	 }

	 public void setVimeo_api_url(String vimeo_api_url) {
	  this.vimeo_api_url = vimeo_api_url;
	 }

	 public void setRequest(Request requestObject) {
	  this.RequestObject = requestObject;
	 }

	 public void setPlayer_url(String player_url) {
	  this.player_url = player_url;
	 }

	 public void setVideo(Video videoObject) {
	  this.VideoObject = videoObject;
	 }

	 public void setUser(User userObject) {
	  this.UserObject = userObject;
	 }

	 public void setEmbed(Embed embedObject) {
	  this.EmbedObject = embedObject;
	 }

	 public void setView(float view) {
	  this.view = view;
	 }

	 public void setVimeo_url(String vimeo_url) {
	  this.vimeo_url = vimeo_url;
	 }

	@Override
	public String toString() {
		return "VimeoVideo [cdn_url=" + cdn_url + ", vimeo_api_url=" + vimeo_api_url + ", RequestObject=" + RequestObject
				+ ", player_url=" + player_url + ", VideoObject=" + VideoObject + ", UserObject=" + UserObject
				+ ", EmbedObject=" + EmbedObject + ", view=" + view + ", vimeo_url=" + vimeo_url + "]";
	}
	 
	 


}
