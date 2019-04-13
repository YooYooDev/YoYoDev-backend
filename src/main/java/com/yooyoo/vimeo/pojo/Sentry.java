package com.yooyoo.vimeo.pojo;

public class Sentry {

	 private String url;
	 private boolean enabled;
	 private boolean debug_enabled;
	 private float debug_intent;


	 // Getter Methods 

	 public String getUrl() {
	  return url;
	 }

	 public boolean getEnabled() {
	  return enabled;
	 }

	 public boolean getDebug_enabled() {
	  return debug_enabled;
	 }

	 public float getDebug_intent() {
	  return debug_intent;
	 }

	 // Setter Methods 

	 public void setUrl(String url) {
	  this.url = url;
	 }

	 public void setEnabled(boolean enabled) {
	  this.enabled = enabled;
	 }

	 public void setDebug_enabled(boolean debug_enabled) {
	  this.debug_enabled = debug_enabled;
	 }

	 public void setDebug_intent(float debug_intent) {
	  this.debug_intent = debug_intent;
	 }


}
