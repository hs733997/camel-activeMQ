package com.harts.apache_camel_activeMQ_reciever.route;

import com.harts.apache_camel_activeMQ_reciever.model.UserModel;
import com.harts.apache_camel_activeMQ_reciever.processor.UserModelProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class UserRecieverRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        JacksonDataFormat userModelDataFormat = new JacksonDataFormat(UserModel.class);
        from("activemq:JsonNode-Data?jmsMessageType=Text")
//                .unmarshal(userModelDataFormat)       // use eigher unmarshal or processor to deserialize the object
                .process(new UserModelProcessor())
                .log("received JSON: ${body}")
                .to("log:received-message-from-activemq");
    }
}
/***
 * use either unmarshal or processor to deserialize the object.
 */