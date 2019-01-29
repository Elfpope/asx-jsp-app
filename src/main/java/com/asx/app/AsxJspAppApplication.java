package com.asx.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class AsxJspAppApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(AsxJspAppApplication.class, args);
	}

	/**
	 * Produce a deployable war file when packaging Spring Boot application instead of the default jar file.
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AsxJspAppApplication.class);
	}

	/*
	 * @Bean public DataSource dataSource() { // no need shutdown, EmbeddedDatabaseFactoryBean will take care of this
	 * EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder(); EmbeddedDatabase db = builder
	 * .setType(EmbeddedDatabaseType.HSQL) //.H2 or .DERBY .addScript("db/sql/create-db.sql")
	 * .addScript("db/sql/insert-data.sql") .build(); return db; }
	 */

}
