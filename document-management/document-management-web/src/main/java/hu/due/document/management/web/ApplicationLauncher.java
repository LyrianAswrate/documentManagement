package hu.due.document.management.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import hu.due.document.management.ui.ApplicationUIModuleConfig;

@SpringBootApplication
@Import({ ApplicationUIModuleConfig.class })
public class ApplicationLauncher {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationLauncher.class, args);
	}

}
