package com.harts.apache_camel_activeMQ.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.harts.apache_camel_activeMQ.model.UserModel;
import org.apache.camel.CamelContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserControllerTest {
    private UserController userController;
    @InjectMocks
    private ObjectMapper objectMapper;
    String userJson;


    @BeforeEach
    void setUp() throws IOException {
//        userController = new UserController(Mockito.mock(CamelContext.class));
        CamelContext camelContext = Mockito.mock(CamelContext.class);
        userController = new UserController(camelContext);
//        objectMapper = Mockito.mock(ObjectMapper.class);
        File file = ResourceUtils.getFile("classpath:userjson.json");
        userJson = Files.readString(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
//        ClassLoader classLoader = getClass().getClassLoader();
//        userJson = Files.readString(
//                Paths.get(classLoader.getResource("userjson.json").getPath()), StandardCharsets.UTF_8);
    }

    @Test
    void checkPositive() throws JsonProcessingException {
        when(objectMapper.writeValueAsString(any())).thenReturn(userJson);
        ResponseEntity<String> response = userController.processUser(new UserModel());
        assertEquals("User processed", response.getBody());
    }
}
