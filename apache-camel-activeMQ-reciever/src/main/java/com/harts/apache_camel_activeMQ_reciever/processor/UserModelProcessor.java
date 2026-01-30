package com.harts.apache_camel_activeMQ_reciever.processor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.harts.apache_camel_activeMQ_reciever.model.UserModel;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.jackson.transform.Json;
import org.apache.camel.model.dataformat.JaxbDataFormat;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserModelProcessor implements Processor {
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void process(Exchange exchange) throws Exception {
        String userModelJson = exchange.getIn().getBody(String.class);
        UserModel userModel = objectMapper.readValue(userModelJson, UserModel.class);
        List<String> mobiles = new ArrayList<>();
        mobiles.add("1234567890");
        mobiles.add("0987654321");
        userModel.setMobile(mobiles);
        System.out.println("Received UserModel: " + userModel);
    }
}

//    JsonNode jsonNode = objectMapper.readValue(userModelJson, JsonNode.class);
//    UserModel userModel = objectMapper.writeValue(jsonNode, UserModel.class);