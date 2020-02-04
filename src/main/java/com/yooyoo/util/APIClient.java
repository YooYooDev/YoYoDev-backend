package com.yooyoo.util;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.yooyoo.vimeo.pojo.Vimeo;
import com.yooyoo.vimeo.pojo.VimeoVideo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class APIClient {
	
	private static String vimeoUrl = "https://player.vimeo.com/video/";
	
	public Vimeo getVideoLink(String videoId) {
		Vimeo vimeo = new Vimeo();
		String videoLink = null;
		String thumb = null;
		String title = null;
		RestTemplate restTemplate = new RestTemplate();
		if (videoId != null) {
			try{
			VimeoVideo quote = restTemplate.getForObject(vimeoUrl + videoId + "/config", VimeoVideo.class);
			String link = quote.getRequest().getFiles().getHls().getCdns().getAkfire_interconnect_quic().getUrl();
			thumb  = quote.getVideo().getThumbs().getBase();
			title = quote.getVideo().getTitle();
			if (link != null) {
				videoLink = link;
			} else {
				videoLink = videoId;
			}
			}catch(Exception e){
				log.info("Exception on getting video link...");
				videoLink = videoId;
			}
		}
		vimeo.setVideoLlink(videoLink);
		vimeo.setThumbLInk(thumb);
		vimeo.setSubTitle(title);
		
		return vimeo;
	}
	
	public static OtpPojo sendOtp(String mobileNo, String otp) throws MalformedURLException, UnsupportedEncodingException {
		OtpPojo otpPojo = null;
		
		String url = YooYooAppConstants.YOOYOO_OTP_PREFEX + mobileNo + YooYooAppConstants.MIDDLE + otp+YooYooAppConstants.YOOYOO_OTP_MESSAGE
				+ YooYooAppConstants.YOOYOO_OTP_POSTFIX;
		RestTemplate restTemplate = new RestTemplate();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();        
		//Add the Jackson Message converter
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

		// Note: here we are making this converter to process any kind of response, 
		// not only application/*json, which is the default behaviour
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));        
		messageConverters.add(converter);  
		restTemplate.setMessageConverters(messageConverters); 
		otpPojo = restTemplate.getForObject(url, OtpPojo.class);

		return otpPojo;

	}
	
	public static void main(String[] args) throws MalformedURLException, UnsupportedEncodingException {
		OtpPojo otpPojo = sendOtp("7899898555", "098990");
		System.out.println("Result is "+otpPojo.getResult().getStatus().getStatusCode());
	}
	 
	
	
	
	

}
