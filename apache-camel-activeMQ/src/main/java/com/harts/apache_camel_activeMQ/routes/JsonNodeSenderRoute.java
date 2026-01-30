package com.harts.apache_camel_activeMQ.routes;

import com.harts.apache_camel_activeMQ.processor.JsonNodeProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class JsonNodeSenderRoute extends RouteBuilder {

    private JsonNodeProcessor jsonNodeProcessor;

    public JsonNodeSenderRoute(JsonNodeProcessor jsonNodeProcessor) {
        this.jsonNodeProcessor = jsonNodeProcessor;
    }

    @Override
    public void configure() throws Exception {
        from("direct:JsonNode-sender-route")
//                .process(jsonNodeProcessor)
                .log("Sending JSON: ${body}")
                .to("activemq:JsonNode-Data");
    }
}
