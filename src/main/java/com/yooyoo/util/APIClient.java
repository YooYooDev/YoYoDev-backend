package com.yooyoo.util;

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
	
	 
	
	
	
	

}
