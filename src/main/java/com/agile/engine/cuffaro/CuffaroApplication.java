package com.agile.engine.cuffaro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class CuffaroApplication {

	public static void main(String[] args) {
		SpringApplication.run(CuffaroApplication.class, args);
	}

}
