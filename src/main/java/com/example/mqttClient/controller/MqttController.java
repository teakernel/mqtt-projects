package com.example.mqttClient.controller;

import com.example.mqttClient.service.MqttService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mqtt")
public class MqttController {

    @Autowired
    private MqttService mqttService;

    @GetMapping
    public String getMqtt(){
        System.out.println("111111111111111111");
        return "消息已发布: ";
    }

//    @GetMapping("/send")
//    public String sendMessage(@RequestParam String msg){
//        mqttService.sendMessage(msg);
//        return "Message send: "+msg;
//    }

   /* @PostMapping("/publish")
    public String publish(@RequestBody String message) {
        mqttService.publish(message);
        return "消息已发布: " + message;
    }

    @PostMapping("/publish/{topic}")
    public String publishToTopic(@PathVariable String topic, @RequestBody String message) {
        mqttService.publish(topic, message);
        return "消息已发布到主题 [" + topic + "]: " + message;
    }*/
}
