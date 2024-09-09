package com.prospecta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class CsvFileApplication {

	public static void main(String[] args) {
		SpringApplication.run(CsvFileApplication.class, args);
	}

}
