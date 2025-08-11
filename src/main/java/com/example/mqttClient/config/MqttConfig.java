package com.example.mqttClient.config;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;

@Slf4j
@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "mqtt") //prefix对应yaml配置文件中top为mqtt的所有参数
public class MqttConfig {

    private String username;

    private String password;

    // yaml中的 -会自动映射为驼峰命名
    // 对应yaml中的mqtt.broker-url
    private String brokerUrl;

    private String clientId;

    private String topic;

    private int timeout;

    private int keepalive;

    private static final byte[] WILL_DATA = "offline".getBytes();

    /**
     * Register clientFactory
     * @return MqttPahoClientFactory
     */
    @Bean
    public MqttPahoClientFactory clientFactory(){
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(getMqttConnectOptions());

        log.info("Get MQTT connection: BrokerUri-{}", brokerUrl);
        return factory;
    }

    private MqttConnectOptions getMqttConnectOptions() {
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();

        if (!brokerUrl.startsWith("tcp://")) {
            throw new IllegalArgumentException("MQTT broker URL must start with tcp://");
        }

        mqttConnectOptions.setServerURIs(new String[]{brokerUrl});
        mqttConnectOptions.setUserName(clientId);
        mqttConnectOptions.setPassword(password.toCharArray());
        mqttConnectOptions.setAutomaticReconnect(true);
        mqttConnectOptions.setConnectionTimeout(timeout);
        mqttConnectOptions.setKeepAliveInterval(keepalive);
        mqttConnectOptions.setCleanSession(true);
        return mqttConnectOptions;
    }
}
