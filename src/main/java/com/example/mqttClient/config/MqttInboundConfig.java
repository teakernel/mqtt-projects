package com.example.mqttClient.config;

import com.example.mqttClient.constant.MqttConstant;
import com.example.mqttClient.handler.MqttMessageReceiver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
@Slf4j
@AllArgsConstructor
@IntegrationComponentScan
public class MqttInboundConfig {

    private MqttMessageReceiver mqttMessageReceiver;

    @Autowired
    private MqttConfig mqttConfig;

    @Bean
    public MessageChannel mqttInboundChannel(){
        return new DirectChannel();
    }

    // 绑定到MQTT入站通道，处理接收到的消息
    @Bean
    @ServiceActivator(inputChannel = "mqttInboundChannel")
    public MessageHandler mqttInboundHandler() {
        return this.mqttMessageReceiver;
    }

    @Bean
    public MessageProducer mqttProducer(){
        String clientId = mqttConfig.getClientId();
        String topic = mqttConfig.getTopic();

        MqttPahoClientFactory mqttPahoClientFactory = mqttConfig.clientFactory();

        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(clientId + MqttConstant.CLIENT_SUFFIX_CONSUMERS, mqttPahoClientFactory,
                        // 可变参数：需要订阅的参数，逗号隔开
                        "test/topic/+", "mqtt/test/+");
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(mqttInboundChannel());

        return adapter;

    }

    // 定义输出通道对应的bean
//    @Bean
//    public MessageChannel nextProcessingChannel() {
//        return new DirectChannel();
//    }

    // 为 nextProcessingChannel 通道添加订阅者
//    @ServiceActivator(inputChannel = "nextProcessingChannel")
//    public void handleNextProcessing(Message<?> message) {
//        // 处理从 nextProcessingChannel 通道接收到的消息
//        Object payload = message.getPayload();
//        System.out.println("handle message from nextProcessingChannel: " + payload);
//
//        // 在这里添加后续业务逻辑，例如：
//        // 1. 数据存储
//        // 2. 格式转换
//        // 3. 转发到其他系统等
//    }

//    @Bean
//    @ServiceActivator(inputChannel = "mqttOutboundChannel")
//    //Description:当有消息发送到 mqttOutboundChannel 通道时， mqttOutbound 处理器会接受并处理这些消息（发送到MQTT服务器）
//    public MessageHandler mqttOutbound() {
//        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(
//                CLIENT_ID + "-publisher", mqttClientFactory());
//        messageHandler.setAsync(true);
//        messageHandler.setDefaultTopic(TOPIC);
//        return messageHandler;
//    }

}
