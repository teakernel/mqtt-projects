package com.example.mqttClient.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MqttGatewayImpl implements MqttGateway {

    @Override
    public void sendToMqtt(String topic, String payload) {
        log.info("Send message {} with topic{}", payload, topic);
    }

    @Override
    public void sendToMqtt(String topic, int qos, String payload) {
        log.info("Send message {} with topic{}, QOS {}", payload, topic, qos);
    }
}
