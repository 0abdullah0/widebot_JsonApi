package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class FbGalleryCard {
	
	public HashMap<String, Object> mapToFbGalleryCard(User user) throws JsonProcessingException {
		
	    HashMap<String, Object> galleryCard = new HashMap<String, Object>();
	    HashMap<String, Object> defaultActions = new HashMap<String, Object>();
	    HashMap<String, Object> buttonItem = new HashMap<String, Object>();
	    List<HashMap<String, Object>> buttons = new ArrayList<HashMap<String, Object>> ();
	    
	    galleryCard.put("title", user.getFirstName());
	    galleryCard.put("image_url", user.getAvatar());
	    galleryCard.put("subtitle", user.getLastName());
	    
	    defaultActions.put("type", "web_url");
	    defaultActions.put("url", "malito:"+user.getEmail()+"?Subject=hello");
	    defaultActions.put("webview_height_ratio", "tall");
	    
	    buttonItem.put("type", "web_url");
	    buttonItem.put("url", "malito:"+user.getEmail()+"?Subject=hello");
	    buttonItem.put("title", "Send Email");
	
	    buttons.add(buttonItem);
	        
	    galleryCard.put("default_action",defaultActions);
	    galleryCard.put("buttons",buttons);
	    
	    //ObjectMapper mapper = new ObjectMapper();
	    //JsonNode jsonNodeMap = mapper.convertValue(galleryCard, JsonNode.class);
	    
	    return galleryCard;
		
	}

}
