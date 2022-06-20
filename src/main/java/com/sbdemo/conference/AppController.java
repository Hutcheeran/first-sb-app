package com.sbdemo.conference;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AppController {
    // gets value from environment variables
    @Value("${app.version}")
    private String appVersion;

    @GetMapping("/hello")
    public String hello(){
        return "Hello world\n";
    }

    @GetMapping
    @RequestMapping("/")
    public Map getStatus(){
        Map map = new HashMap<String, String>();
        map.put("app-version", appVersion);
        return map;
    }
}
