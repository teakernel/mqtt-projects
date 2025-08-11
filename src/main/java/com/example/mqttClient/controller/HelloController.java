package com.example.mqttClient.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController  // 标识为 Web 控制器，返回 JSON 数据
public class HelloController {

    // 定义 GET 请求接口：http://localhost:8080/hello/xxx
    @GetMapping("/hello/{name}")
    public String sayHello(@PathVariable String name) {
        return "Hello, " + name + "! This is a Spring Boot project.";
    }
}