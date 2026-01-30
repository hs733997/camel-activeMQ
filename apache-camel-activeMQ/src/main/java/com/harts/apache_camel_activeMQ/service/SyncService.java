package com.harts.apache_camel_activeMQ.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.harts.apache_camel_activeMQ.model.UserModel;
import org.springframework.stereotype.Service;

@Service
public class SyncService {

    public void processResponse(String response) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.readValue(response, UserModel.class);

    }
}
