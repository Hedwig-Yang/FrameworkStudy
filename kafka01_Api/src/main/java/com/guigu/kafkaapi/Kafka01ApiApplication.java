package com.guigu.kafkaapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class Kafka01ApiApplication {
	public static Logger logger = LoggerFactory.getLogger(Kafka01ApiApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(Kafka01ApiApplication.class, args);
		logger.info("启动{}",new Date());
	}

}
