package hu.due.document.management.ui;

import java.io.IOException;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

import com.vaadin.server.VaadinServlet;
import com.vaadin.spring.server.SpringVaadinServlet;

@Configuration
@ComponentScan
@EnableAsync
@EnableAutoConfiguration
//@Import({ ApplicationServiceModuleConfig.class })
public class ApplicationUIModuleConfig {

	@Bean
	public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(ApplicationContext appContext)
			throws IOException {
		PropertySourcesPlaceholderConfigurer placeholderConfigurer;
		placeholderConfigurer = new PropertySourcesPlaceholderConfigurer();
		placeholderConfigurer.setLocations(appContext.getResources("/properties/application.properties"));
		return placeholderConfigurer;
	}

	@Bean
	public VaadinServlet vaadinServlet() {
		return new SpringVaadinServlet();
	}

}
