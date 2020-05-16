package davidhxxx.example.angularsboot.webapp;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import ch.qos.logback.access.tomcat.LogbackValve;
import davidhxxx.example.angularsboot.service.AppServiceConfig;

@Import(AppServiceConfig.class)
@SpringBootApplication
public class AppConfig extends SpringBootServletInitializer {

	private final Logger log = LoggerFactory.getLogger(AppConfig.class);

	@Value("${my.application.properties.file}")
	String currentApplicationPropertiesFileName;

	public static void main(String[] args) throws IOException {
		SpringApplication.run(AppConfig.class, args);
	}

	@PostConstruct
	private void postConstruct() {
		log.info("Current application properties file is " + currentApplicationPropertiesFileName);
	}

	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer(
			final @Value("${my.logbackaccess.path:}") String fileName) {
		return new EmbeddedServletContainerCustomizer() {
			public void customize(ConfigurableEmbeddedServletContainer container) {
				if ((container instanceof TomcatEmbeddedServletContainerFactory)) {
					((TomcatEmbeddedServletContainerFactory) container)
							.addContextCustomizers(new TomcatContextCustomizer[] { new TomcatContextCustomizer() {

								@Override
								public void customize(org.apache.catalina.Context context) {
									LogbackValve logbackValve = new LogbackValve();
									logbackValve.setFilename(fileName);
									context.getPipeline().addValve(logbackValve);
								}
							} });
				}
			}
		};
	}

}