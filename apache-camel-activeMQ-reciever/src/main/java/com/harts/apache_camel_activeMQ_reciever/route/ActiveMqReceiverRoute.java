package com.harts.apache_camel_activeMQ_reciever.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class ActiveMqReceiverRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("activemq:my-activeMq-queue")
                .to("log:received-message-from-activemq");
    }
}
