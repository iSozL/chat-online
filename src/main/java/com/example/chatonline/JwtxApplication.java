package com.example.chatonline;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@MapperScan("com.example.chatonline.Dao") //扫描的mapper
@SpringBootApplication
public class JwtxApplication {

    public static void main(String[] args) {

        SpringApplication.run(JwtxApplication.class, args);
    }

}
