package it.spaceapps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@SpringBootApplication
public class SpaceAppsApplication extends SpringBootServletInitializer {

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpaceAppsApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpaceAppsApplication.class, args);
	}

//	@Bean
//	InternalResourceViewResolver viewResolver() {
//
//		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//		resolver.setPrefix("/webapp/");
//		resolver.setSuffix(".html");
//
//		return resolver;
//	}

}
