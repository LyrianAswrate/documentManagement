package hu.due.document.management.web;

import java.util.Locale;
import java.util.TimeZone;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import hu.due.document.management.ui.ApplicationUIModuleConfig;

public class ApplicationLauncher {

	public static void main(String[] args) {
		new ApplicationLauncher().run(args);
	}

	protected Locale getDefaultLocale() {
		return new Locale("hu", "HU");
	}

	protected TimeZone getDefaultTimeZone() {
		return TimeZone.getTimeZone("Europe/Budapest");
	}

	public ConfigurableApplicationContext run(String[] args) {

		Locale defaultLocale = getDefaultLocale();
		if (defaultLocale != null) {
			Locale.setDefault(getDefaultLocale());
		}
		TimeZone defaultTimeZone = getDefaultTimeZone();
		if (defaultTimeZone != null) {
			TimeZone.setDefault(defaultTimeZone);
		}

		return new SpringApplicationBuilder().sources(ApplicationUIModuleConfig.class).build().run(args);
		// .properties(ImmutableMap.<String, Object>builder()//
		// .put(LoggingApplicationListener.CONFIG_PROPERTY,
		// "C:\\Magán\\eclipse\\workspace\\music-box\\music-box-web\\src\\main\\resources\\logback-spring.xml")//
		// .put(LogFile.PATH_PROPERTY, "C:\\Magán\\eclipse").build())//
	}
}
