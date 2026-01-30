package com.harts.apache_camel_activeMQ_reciever.config;

import com.harts.apache_camel_activeMQ_reciever.model.UserModel;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqConfig {
    @Bean
    public JacksonDataFormat jacksonDataFormat(){
        return new JacksonDataFormat(UserModel.class);
    }
}
