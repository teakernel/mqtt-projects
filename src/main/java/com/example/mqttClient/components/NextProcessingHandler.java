package com.example.mqttClient.components;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class NextProcessingHandler {

    // 为 nextProcessingChannel 通道添加订阅者
//    @ServiceActivator(inputChannel = "nextProcessingChannel")
//    public void handleNextProcessing(Message<?> message) {
//        // 处理从 nextProcessingChannel 通道接收到的消息
//        Object payload = message.getPayload();
//        System.out.println("处理 nextProcessingChannel 消息: " + payload);
//
//        // 在这里添加后续业务逻辑，例如：
//        // 1. 数据存储
//        // 2. 格式转换
//        // 3. 转发到其他系统等
//    }
}
