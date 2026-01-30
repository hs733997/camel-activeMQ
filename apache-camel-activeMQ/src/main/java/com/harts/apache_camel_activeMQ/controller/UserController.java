package com.harts.apache_camel_activeMQ.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.harts.apache_camel_activeMQ.model.UserModel;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final CamelContext camelContext;
    @Autowired
    private ObjectMapper mapper;
    public UserController(CamelContext camelContext) {
        this.camelContext = camelContext;
    }

    @PostMapping("/processUser")
    public ResponseEntity<String> processUser(@RequestBody UserModel userModel) throws JsonProcessingException {
        ProducerTemplate template = camelContext.createProducerTemplate();
        String userJson = mapper.writeValueAsString(userModel);
        template.sendBody("direct:JsonNode-sender-route", userJson);
        return ResponseEntity.ok("User processed");
    }

    @PostMapping("/synchronousProcessUser")
    public ResponseEntity<String> synchronusProcessUser(@RequestBody UserModel userModel) throws JsonProcessingException {
        ProducerTemplate template = camelContext.createProducerTemplate();
        String userJson = mapper.writeValueAsString(userModel);
        String response = template.requestBody("direct:sync-sender-route", userJson, String.class);
        return ResponseEntity.ok(response);
    }
}
