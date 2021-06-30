package com.example.demo.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class WidebotApi {
	
	public JsonNode toWideBotJsonApi(HashMap<String, Object> gallery_format,String id) {
		
		HashMap<String, Object> widebotResponse = new HashMap<String, Object>();
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		HashMap<String, Object> facebookResponse = new HashMap<String, Object>();
		HashMap<String, Object> message = new HashMap<String, Object>();
		HashMap<String, Object> attachment = new HashMap<String, Object>();
		HashMap<String, Object> recipient = new HashMap<String, Object>();
		HashMap<String, Object> payload = new HashMap<String, Object>();
		
		attributes.put("id", id);
		widebotResponse.put("attributes", attributes);
		
		widebotResponse.put("FlowName", "taskFlow");
		
		attachment.put("type","template");
		payload.put("template_type","generic");
		payload.put("elements",gallery_format);
		attachment.put("payload",payload);
		message.put("attachment", attachment);
		message.put("text", "hello, world!");
		facebookResponse.put("message", message);
		
		recipient.put("id", "{psid}");
		facebookResponse.put("recipient", recipient);
		facebookResponse.put("messaging_type", "RESPONSE");
		widebotResponse.put("FacebookResponse", facebookResponse);
		
		ObjectMapper mapper = new ObjectMapper();
	    JsonNode jsonNodeMap = mapper.convertValue(widebotResponse, JsonNode.class);
	    
		return jsonNodeMap;
		
	}
	
	

}
