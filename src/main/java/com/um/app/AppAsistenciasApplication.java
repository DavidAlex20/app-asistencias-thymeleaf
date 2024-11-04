package com.um.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@EnableR2dbcRepositories
@SpringBootApplication
public class AppAsistenciasApplication implements CommandLineRunner{
	
	private static final Logger log = LoggerFactory.getLogger(AppAsistenciasApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AppAsistenciasApplication.class, args);
		System.out.println("\n--------------------------------\nAPLICACIÓN INICIALIZADA\n--------------------------------");
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("APP ASISTENCIAS - INICIADA");
	}
}