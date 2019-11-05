package com.tfg.wekaWeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties
public class WekaWebApplication {


	
	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(WekaWebApplication.class, args);	
	}
}
