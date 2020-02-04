package com.yooyoo.util;

import com.yooyoo.util.OtpStatus.OtpStatusBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class OtpPojo {
	 Result ResultObject;


	 // Getter Methods 

	 public Result getResult() {
	  return ResultObject;
	 }

	 // Setter Methods 

	 public void setResult(Result resultObject) {
	  this.ResultObject = resultObject;
	 }
	}
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@EqualsAndHashCode
class Result {
	 Status StatusObject;
	 private String msisdn;
	 private String req_id;


	 // Getter Methods 

	 public Status getStatus() {
	  return StatusObject;
	 }

	 public String getMsisdn() {
	  return msisdn;
	 }

	 public String getReq_id() {
	  return req_id;
	 }

	 // Setter Methods 

	 public void setStatus(Status statusObject) {
	  this.StatusObject = statusObject;
	 }

	 public void setMsisdn(String msisdn) {
	  this.msisdn = msisdn;
	 }

	 public void setReq_id(String req_id) {
	  this.req_id = req_id;
	 }
	}
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@EqualsAndHashCode
	 class Status {
	 private String errorMessage;
	 private String statusCode;
	 private String errorCode;


	 // Getter Methods 

	 public String getErrorMessage() {
	  return errorMessage;
	 }

	 public String getStatusCode() {
	  return statusCode;
	 }

	 public String getErrorCode() {
	  return errorCode;
	 }

	 // Setter Methods 

	 public void setErrorMessage(String errorMessage) {
	  this.errorMessage = errorMessage;
	 }

	 public void setStatusCode(String statusCode) {
	  this.statusCode = statusCode;
	 }

	 public void setErrorCode(String errorCode) {
	  this.errorCode = errorCode;
	 }
	}