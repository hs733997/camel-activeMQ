package com.harts.apache_camel_activeMQ.routes;

import com.harts.apache_camel_activeMQ.processor.JsonNodeProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SyncResponseRoute extends RouteBuilder {

    private JsonNodeProcessor jsonNodeProcessor;

    public SyncResponseRoute(JsonNodeProcessor jsonNodeProcessor) {
        this.jsonNodeProcessor = jsonNodeProcessor;
    }

    /**
     * This route is used to send a message to the ActiveMQ queue and wait for a synchronous response.
     * It uses the 'activemq:queue:my-activeMq-queue' endpoint to send the message and receive the response.
     */
    @Override
    public void configure() throws Exception {
        from("direct:sync-sender-route")
                .log("Sending message to ActiveMQ queue")
                .process(jsonNodeProcessor)
                .to("activemq:sync-receiver-route")
//                .log("Waiting for synchronous response from ActiveMQ queue")
//                .to("activemq:queue:my-activeMq-response-queue")
                .log("Received synchronous response: ${body}")
                .bean("syncService", "processResponse");
    }
}
