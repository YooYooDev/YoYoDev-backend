package com.yooyoo.vimeo.pojo;

public class User {

	 private String vimeo_api_interaction_tokens = null;
	 private float liked;
	 private String account_type;
	 private String vimeo_api_client_token = null;
	 private float logged_in;
	 private float owner;
	 private float watch_later;
	 private float id;
	 private float mod;


	 // Getter Methods 

	 public String getVimeo_api_interaction_tokens() {
	  return vimeo_api_interaction_tokens;
	 }

	 public float getLiked() {
	  return liked;
	 }

	 public String getAccount_type() {
	  return account_type;
	 }

	 public String getVimeo_api_client_token() {
	  return vimeo_api_client_token;
	 }

	 public float getLogged_in() {
	  return logged_in;
	 }

	 public float getOwner() {
	  return owner;
	 }

	 public float getWatch_later() {
	  return watch_later;
	 }

	 public float getId() {
	  return id;
	 }

	 public float getMod() {
	  return mod;
	 }

	 // Setter Methods 

	 public void setVimeo_api_interaction_tokens(String vimeo_api_interaction_tokens) {
	  this.vimeo_api_interaction_tokens = vimeo_api_interaction_tokens;
	 }

	 public void setLiked(float liked) {
	  this.liked = liked;
	 }

	 public void setAccount_type(String account_type) {
	  this.account_type = account_type;
	 }

	 public void setVimeo_api_client_token(String vimeo_api_client_token) {
	  this.vimeo_api_client_token = vimeo_api_client_token;
	 }

	 public void setLogged_in(float logged_in) {
	  this.logged_in = logged_in;
	 }

	 public void setOwner(float owner) {
	  this.owner = owner;
	 }

	 public void setWatch_later(float watch_later) {
	  this.watch_later = watch_later;
	 }

	 public void setId(float id) {
	  this.id = id;
	 }

	 public void setMod(float mod) {
	  this.mod = mod;
	 }


}
