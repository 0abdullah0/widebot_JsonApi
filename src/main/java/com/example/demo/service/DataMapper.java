package com.example.demo.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DataMapper {
	
	public User extractData(ResponseEntity<String> response) throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
        Map<String, Map<String,Object>> map = mapper.readValue(response.getBody(), Map.class);
        
        User user =new User();	
    	user.setId(map.get("data").get("id").toString());
    	user.setFirstName(map.get("data").get("first_name").toString());
    	user.setLastName(map.get("data").get("last_name").toString());
    	user.setEmail(map.get("data").get("email").toString());
    	user.setAvatar(map.get("data").get("avatar").toString());
    	
		return user;
	}

}
