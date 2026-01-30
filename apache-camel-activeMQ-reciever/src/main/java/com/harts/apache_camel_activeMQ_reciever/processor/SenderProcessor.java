package com.harts.apache_camel_activeMQ_reciever.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class SenderProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(exchange.getIn().getBody());
        exchange.getIn().setBody(json); // replace with JSON string
    }
}
