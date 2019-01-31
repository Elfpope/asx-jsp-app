package com.asx.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class AsxJspAppApplication extends SpringBootServletInitializer {

	private static Logger LOG = LoggerFactory.getLogger(AsxJspAppApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AsxJspAppApplication.class, args);
		LOG.debug("************************ ASX JSP APP STARTED ************************");
	}

	/**
	 * Produce a deployable war file when packaging Spring Boot application instead of the default jar file.
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AsxJspAppApplication.class);
	}

}
