package com.yooyoo.vimeo.pojo;

import java.util.ArrayList;

public class Files {

	 Dash DashObject;
	 Hls HlsObject;
	 ArrayList < Object > progressive = new ArrayList < Object > ();


	 // Getter Methods 

	 public Dash getDash() {
	  return DashObject;
	 }

	 public Hls getHls() {
	  return HlsObject;
	 }

	 // Setter Methods 

	 public void setDash(Dash dashObject) {
	  this.DashObject = dashObject;
	 }

	 public void setHls(Hls hlsObject) {
	  this.HlsObject = hlsObject;
	 }


}
