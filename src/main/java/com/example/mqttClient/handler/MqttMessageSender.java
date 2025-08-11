package com.example.mqttClient.handler;

import com.fasterxml.jackson.databind.util.JSONPObject;
import jakarta.annotation.Resource;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class MqttMessageSender {

    @Resource
    private MqttGateway mqttGateway;

    public void send(String topic, String message){
        mqttGateway.sendToMqtt(topic,message);
    }

    public void send(String topic, int qos, JSONPObject messageBody){
        mqttGateway.sendToMqtt(topic, qos, messageBody.toString());
    }

}
