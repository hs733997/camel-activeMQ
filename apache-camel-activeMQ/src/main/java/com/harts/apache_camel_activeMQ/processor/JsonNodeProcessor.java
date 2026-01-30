package com.harts.apache_camel_activeMQ.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.harts.apache_camel_activeMQ.model.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JsonNodeProcessor implements Processor {

    @Autowired
    private ObjectMapper mapper;
    private static final Logger logger = LoggerFactory.getLogger(JsonNodeProcessor.class);
    @Override
    public void process(Exchange exchange) throws Exception {
        UserModel userModel = exchange.getIn().getBody(UserModel.class);
        String userJson = mapper.writeValueAsString(userModel);
        logger.info("Processing user: {}", userModel);
    }
}
