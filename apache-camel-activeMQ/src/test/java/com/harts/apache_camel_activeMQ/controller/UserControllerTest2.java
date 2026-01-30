package com.harts.apache_camel_activeMQ.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.harts.apache_camel_activeMQ.model.UserModel;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest2 {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CamelContext camelContext;

//    @MockBean
//    private ObjectMapper mapper;
    private UserModel userModel;
    String userJson;

    @Test
    void testProcessUser() throws Exception {

        userModel = new UserModel();
        userModel.setUserName("test name");
        userModel.setUserId(1234);
        List<String> list = Arrays.asList("123","456","789");
        userModel.setMobile(list);

        ObjectMapper objectMapper = new ObjectMapper();
        userJson = objectMapper.writeValueAsString(userModel);

//        // Mocking ObjectMapper behavior
//        Mockito.when(mapper.writeValueAsString(userModel)).thenReturn(userJson);

        // Mocking CamelContext behavior
        ProducerTemplate template = Mockito.mock(ProducerTemplate.class);
        Mockito.when(camelContext.createProducerTemplate()).thenReturn(template);

        // When & Then
        mockMvc.perform(post("/processUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk());

        // Verify that the template was called with the correct parameters
        Mockito.verify(template).sendBody("direct:JsonNode-sender-route", userJson);
    }
}

