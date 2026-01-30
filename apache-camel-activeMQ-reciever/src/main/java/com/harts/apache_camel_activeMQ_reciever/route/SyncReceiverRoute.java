package com.harts.apache_camel_activeMQ_reciever.route;

import com.harts.apache_camel_activeMQ_reciever.processor.SenderProcessor;
import com.harts.apache_camel_activeMQ_reciever.processor.UserModelProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SyncReceiverRoute extends RouteBuilder {


    @Override
    public void configure() throws Exception {
        from("activemq:sync-receiver-route")
                .log("Received JSON: ${body}")
                .process(new UserModelProcessor())
                .process(new SenderProcessor())
                .to("log:processed-json");
    }
}
