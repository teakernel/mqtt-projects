package com.example.mqttClient.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MqttMessageReceiver implements MessageHandler {

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        try{
            MessageHeaders headers = message.getHeaders();
            String topic = headers.get(MqttHeaders.RECEIVED_TOPIC).toString();
            log.info("Get message with TOPIC: {}",topic);

            //message body
            String payload = message.getPayload().toString();
            log.info("Get message body: {}",payload);

            // message handler

        } catch (Exception e) {
            log.error(e.toString());
            throw new RuntimeException(e);
        }
    }
}
