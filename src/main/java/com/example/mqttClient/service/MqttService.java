package com.example.mqttClient.service;

import com.example.mqttClient.config.MqttInboundConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class MqttService {
//
//    @Autowired
//    private MessageHandler mqttOutbound;
//
////    @Autowired
////    private MessageChannel mqttSendChannel;
//
////    public void publish(String payload) {
////        publish(MqttInboundConfig.TOPIC, payload);
////    }
//
////    public void sendMessage(String message) {
////        mqttSendChannel.send(new GenericMessage<>(message));
////    }
//
//    public void publish(String topic, String payload){
//        Message<String> message = MessageBuilder
//                .withPayload(payload)
//                .setHeader(MqttHeaders.TOPIC, topic)
//                .setHeader(MqttHeaders.QOS, 1)
//                .build();
//
//        try {
//            mqttOutbound.handleMessage(message);
//            System.out.println("消息发布成功 - 主题: " + topic + ", 内容: " + payload);
//        } catch (MessagingException e) {
//            System.err.println("消息发布失败: " + e.getMessage());
//        }
//    }
}
