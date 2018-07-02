package com.ayit.demo;

import javax.annotation.PostConstruct;

import com.ayit.demo.springboot.StaticServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



import io.vertx.core.Vertx;

@SpringBootApplication
public class VertxApplication {

    @Autowired
    private StaticServer staticServer;

    public static void main(String[] args) {

        // This is basically the same example as the web-examples static site example but it's booted using
        // Spring Boot, not Vert.x
        SpringApplication.run(VertxApplication.class, args);
    }

    @PostConstruct
    public void deployVerticle() {
        Vertx.vertx().deployVerticle(staticServer);
    }
}