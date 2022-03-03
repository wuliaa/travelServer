package com.example.travelserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@MapperScan(value = "com.example.travelserver.mapper")
@ComponentScan(basePackages = {"com.example.travelserver","org.n3r.idworker"})
public class TravelserverApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(TravelserverApplication.class);
    }


    public static void main(String[] args) {
        SpringApplication.run(TravelserverApplication.class, args);
    }

}
