package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.config.ExternalApiConfig;
import com.example.demo.model.User;
import com.example.demo.service.DataMapper;
import com.example.demo.service.FbGalleryCard;
import com.example.demo.service.WidebotApi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/myapi/")
public class SampleController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ExternalApiConfig externalApiConfig;
	
	@Autowired
	private FbGalleryCard fbGalleryCard;
	
	@Autowired
	private DataMapper dataMapper;
	
	@Autowired
	WidebotApi widebotApi;
	
	
	/* WEB API THAT MAPS SAMPLE API --> WIDEBOT JSONAPI  
	  (http://localhost:9090/myapi/sample?id=<number>)*/
	
	 @GetMapping("/sample")
	 @ResponseBody
	 public ResponseEntity<JsonNode> getSampleData(@RequestParam String id) throws JsonProcessingException {
		 
		 HashMap<String, Object> gallery_format;
		 JsonNode widebotJsonResponse;
		 // configurations to consume api 
		 HttpEntity entity = externalApiConfig.getExternalEntity();
		 
		 //consume external endpoint
		 String url = "https://reqres.in/api/users?id="+id+"";
		 ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, 
				 entity, String.class);
		 
		 // get "data" property -> (user data) from response
		 User user = dataMapper.extractData(response);
		 
		 //convert user data to facebook gallery card format
		 gallery_format=fbGalleryCard.mapToFbGalleryCard(user);
		 
		 //inject facebook gallery into widebot JsonApi
		 widebotJsonResponse=widebotApi.toWideBotJsonApi(gallery_format, id);
		 
		 return ResponseEntity.ok(widebotJsonResponse);
	
	   }

}
