package com.yooyoo.vimeo.pojo;

public class Request {

	 Files FilesObject;
	 private String lang;
	 Sentry SentryObject;
	 Ab_tests Ab_testsObject;
	 private String referrer = null;
	 private String cookie_domain;
	 private float timestamp;
	 Gc_debug Gc_debugObject;
	 private float expires;
	 private String currency;
	 private String session;
	 Cookie CookieObject;
	 Build BuildObject;
	 Urls UrlsObject;
	 private String signature;
	 Flags FlagsObject;
	 private String country;


	 // Getter Methods 

	 public Files getFiles() {
	  return FilesObject;
	 }

	 public String getLang() {
	  return lang;
	 }

	 public Sentry getSentry() {
	  return SentryObject;
	 }

	 public Ab_tests getAb_tests() {
	  return Ab_testsObject;
	 }

	 public String getReferrer() {
	  return referrer;
	 }

	 public String getCookie_domain() {
	  return cookie_domain;
	 }

	 public float getTimestamp() {
	  return timestamp;
	 }

	 public Gc_debug getGc_debug() {
	  return Gc_debugObject;
	 }

	 public float getExpires() {
	  return expires;
	 }

	 public String getCurrency() {
	  return currency;
	 }

	 public String getSession() {
	  return session;
	 }

	 public Cookie getCookie() {
	  return CookieObject;
	 }

	 public Build getBuild() {
	  return BuildObject;
	 }

	 public Urls getUrls() {
	  return UrlsObject;
	 }

	 public String getSignature() {
	  return signature;
	 }

	 public Flags getFlags() {
	  return FlagsObject;
	 }

	 public String getCountry() {
	  return country;
	 }

	 // Setter Methods 

	 public void setFiles(Files filesObject) {
	  this.FilesObject = filesObject;
	 }

	 public void setLang(String lang) {
	  this.lang = lang;
	 }

	 public void setSentry(Sentry sentryObject) {
	  this.SentryObject = sentryObject;
	 }

	 public void setAb_tests(Ab_tests ab_testsObject) {
	  this.Ab_testsObject = ab_testsObject;
	 }

	 public void setReferrer(String referrer) {
	  this.referrer = referrer;
	 }

	 public void setCookie_domain(String cookie_domain) {
	  this.cookie_domain = cookie_domain;
	 }

	 public void setTimestamp(float timestamp) {
	  this.timestamp = timestamp;
	 }

	 public void setGc_debug(Gc_debug gc_debugObject) {
	  this.Gc_debugObject = gc_debugObject;
	 }

	 public void setExpires(float expires) {
	  this.expires = expires;
	 }

	 public void setCurrency(String currency) {
	  this.currency = currency;
	 }

	 public void setSession(String session) {
	  this.session = session;
	 }

	 public void setCookie(Cookie cookieObject) {
	  this.CookieObject = cookieObject;
	 }

	 public void setBuild(Build buildObject) {
	  this.BuildObject = buildObject;
	 }

	 public void setUrls(Urls urlsObject) {
	  this.UrlsObject = urlsObject;
	 }

	 public void setSignature(String signature) {
	  this.signature = signature;
	 }

	 public void setFlags(Flags flagsObject) {
	  this.FlagsObject = flagsObject;
	 }

	 public void setCountry(String country) {
	  this.country = country;
	 }


}
