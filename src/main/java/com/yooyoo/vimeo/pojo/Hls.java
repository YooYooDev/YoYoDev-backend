package com.yooyoo.vimeo.pojo;

public class Hls {

	 private boolean separate_av;
	 private String default_cdn;
	 Cdns CdnsObject;


	 // Getter Methods 

	 public boolean getSeparate_av() {
	  return separate_av;
	 }

	 public String getDefault_cdn() {
	  return default_cdn;
	 }

	 public Cdns getCdns() {
	  return CdnsObject;
	 }

	 // Setter Methods 

	 public void setSeparate_av(boolean separate_av) {
	  this.separate_av = separate_av;
	 }

	 public void setDefault_cdn(String default_cdn) {
	  this.default_cdn = default_cdn;
	 }

	 public void setCdns(Cdns cdnsObject) {
	  this.CdnsObject = cdnsObject;
	 }


}
