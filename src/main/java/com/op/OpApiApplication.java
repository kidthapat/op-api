package com.op;

import com.op.file.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(StorageProperties.class)
@SpringBootApplication
public class OpApiApplication {

    public static void main(String[] args) {
        System.getProperty("java.home");
        SpringApplication.run(OpApiApplication.class, args);
    }
}
