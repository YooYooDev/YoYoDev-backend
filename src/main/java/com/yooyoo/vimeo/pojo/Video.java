package com.yooyoo.vimeo.pojo;

public class Video {

	 Version VersionObject;
	 private float height;
	 private float duration;
	 Thumbs ThumbsObject;
	 Owner OwnerObject;
	 File_codecs File_codecsObject;
	 private float id;
	 private String embed_code;
	 private String title;
	 private String share_url;
	 private float width;
	 private String embed_permission;
	 private float fps;
	 private float spatial;
	 private String live_event = null;
	 private float allow_hd;
	 private float hd;
	 private String lang = null;
	 private float default_to_hd;
	 private String url = null;
	 private String privacy;
	 private String bypass_token;
	 private String unlisted_hash;


	 // Getter Methods 

	 public Version getVersion() {
	  return VersionObject;
	 }

	 public float getHeight() {
	  return height;
	 }

	 public float getDuration() {
	  return duration;
	 }

	 public Thumbs getThumbs() {
	  return ThumbsObject;
	 }

	 public Owner getOwner() {
	  return OwnerObject;
	 }

	 public File_codecs getFile_codecs() {
	  return File_codecsObject;
	 }

	 public float getId() {
	  return id;
	 }

	 public String getEmbed_code() {
	  return embed_code;
	 }

	 public String getTitle() {
	  return title;
	 }

	 public String getShare_url() {
	  return share_url;
	 }

	 public float getWidth() {
	  return width;
	 }

	 public String getEmbed_permission() {
	  return embed_permission;
	 }

	 public float getFps() {
	  return fps;
	 }

	 public float getSpatial() {
	  return spatial;
	 }

	 public String getLive_event() {
	  return live_event;
	 }

	 public float getAllow_hd() {
	  return allow_hd;
	 }

	 public float getHd() {
	  return hd;
	 }

	 public String getLang() {
	  return lang;
	 }

	 public float getDefault_to_hd() {
	  return default_to_hd;
	 }

	 public String getUrl() {
	  return url;
	 }

	 public String getPrivacy() {
	  return privacy;
	 }

	 public String getBypass_token() {
	  return bypass_token;
	 }

	 public String getUnlisted_hash() {
	  return unlisted_hash;
	 }

	 // Setter Methods 

	 public void setVersion(Version versionObject) {
	  this.VersionObject = versionObject;
	 }

	 public void setHeight(float height) {
	  this.height = height;
	 }

	 public void setDuration(float duration) {
	  this.duration = duration;
	 }

	 public void setThumbs(Thumbs thumbsObject) {
	  this.ThumbsObject = thumbsObject;
	 }

	 public void setOwner(Owner ownerObject) {
	  this.OwnerObject = ownerObject;
	 }

	 public void setFile_codecs(File_codecs file_codecsObject) {
	  this.File_codecsObject = file_codecsObject;
	 }

	 public void setId(float id) {
	  this.id = id;
	 }

	 public void setEmbed_code(String embed_code) {
	  this.embed_code = embed_code;
	 }

	 public void setTitle(String title) {
	  this.title = title;
	 }

	 public void setShare_url(String share_url) {
	  this.share_url = share_url;
	 }

	 public void setWidth(float width) {
	  this.width = width;
	 }

	 public void setEmbed_permission(String embed_permission) {
	  this.embed_permission = embed_permission;
	 }

	 public void setFps(float fps) {
	  this.fps = fps;
	 }

	 public void setSpatial(float spatial) {
	  this.spatial = spatial;
	 }

	 public void setLive_event(String live_event) {
	  this.live_event = live_event;
	 }

	 public void setAllow_hd(float allow_hd) {
	  this.allow_hd = allow_hd;
	 }

	 public void setHd(float hd) {
	  this.hd = hd;
	 }

	 public void setLang(String lang) {
	  this.lang = lang;
	 }

	 public void setDefault_to_hd(float default_to_hd) {
	  this.default_to_hd = default_to_hd;
	 }

	 public void setUrl(String url) {
	  this.url = url;
	 }

	 public void setPrivacy(String privacy) {
	  this.privacy = privacy;
	 }

	 public void setBypass_token(String bypass_token) {
	  this.bypass_token = bypass_token;
	 }

	 public void setUnlisted_hash(String unlisted_hash) {
	  this.unlisted_hash = unlisted_hash;
	 }


}
