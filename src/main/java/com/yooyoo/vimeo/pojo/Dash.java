package com.yooyoo.vimeo.pojo;

import java.util.ArrayList;

public class Dash {

	 private boolean separate_av;
	 ArrayList < Object > streams = new ArrayList < Object > ();
	 Cdns CdnsObject;
	 private String default_cdn;


	 // Getter Methods 

	 public boolean getSeparate_av() {
	  return separate_av;
	 }

	 public Cdns getCdns() {
	  return CdnsObject;
	 }

	 public String getDefault_cdn() {
	  return default_cdn;
	 }

	 // Setter Methods 

	 public void setSeparate_av(boolean separate_av) {
	  this.separate_av = separate_av;
	 }

	 public void setCdns(Cdns cdnsObject) {
	  this.CdnsObject = cdnsObject;
	 }

	 public void setDefault_cdn(String default_cdn) {
	  this.default_cdn = default_cdn;
	 }


}
