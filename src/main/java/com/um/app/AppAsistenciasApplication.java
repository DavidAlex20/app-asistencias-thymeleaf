package com.um.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
//import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

import io.r2dbc.spi.ConnectionFactory;

@EnableR2dbcRepositories
@SpringBootApplication
public class AppAsistenciasApplication implements CommandLineRunner{
	
	private static final Logger log = LoggerFactory.getLogger(AppAsistenciasApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AppAsistenciasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("APP ASISTENCIAS - INICIADA");
	}
	
	@Bean
	ConnectionFactoryInitializer initializer(ConnectionFactory conn) {
		ConnectionFactoryInitializer ini = new ConnectionFactoryInitializer();
		ini.setConnectionFactory(conn);
		// ini.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));
		return ini;
	}

}