package com.example.demo.config;

import java.util.Arrays;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@Configuration
public class ExternalApiConfig {
	
	public HttpEntity<String> getExternalEntity() {
		HttpHeaders headers = new HttpHeaders();
		 headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		 headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
	     HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
	     return entity;
	}

}
