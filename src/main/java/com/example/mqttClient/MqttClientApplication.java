package com.example.mqttClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

@SpringBootApplication //(scanBasePackages = "com.example.mqttClient.handler")
public class MqttClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(MqttClientApplication.class, args);
	}

}
