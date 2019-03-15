package hu.due.document.management.service;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan
@EntityScan
@EnableJpaRepositories
@ServletComponentScan
public class ApplicationServiceModuleConfig {
	// Szerver szinü Bean-ek definiálása
}
