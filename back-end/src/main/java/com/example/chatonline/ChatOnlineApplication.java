package com.example.chatonline;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.chatonline.Dao") //扫描的mapper
@SpringBootApplication
public class ChatOnlineApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatOnlineApplication.class, args);
    }

}
