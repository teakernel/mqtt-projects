package com.example.mqttClient.config;

import com.example.mqttClient.constant.MqttConstant;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Slf4j
@Configuration
@AllArgsConstructor
public class MqttOutboundConfig {

    @Autowired
    private MqttConfig mqttConfig;

    @Bean
    public MessageChannel mqttOutboundChannel(){
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutboundHandler(){
        String clientId = mqttConfig.getClientId();
        String topic = mqttConfig.getTopic();

        MqttPahoClientFactory clientFactory = mqttConfig.clientFactory();

        MqttPahoMessageHandler messageHandler =
                new MqttPahoMessageHandler(clientId + MqttConstant.CLIENT_SUFFIX_PRODUCERS, clientFactory);

        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic(topic);
        messageHandler.setDefaultQos(1);
        messageHandler.setConverter(new DefaultPahoMessageConverter());
        return messageHandler;
    }
}
