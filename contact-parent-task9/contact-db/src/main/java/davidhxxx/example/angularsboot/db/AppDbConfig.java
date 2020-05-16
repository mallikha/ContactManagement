package davidhxxx.example.angularsboot.db;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import davidhxxx.example.angularsboot.db.model.Contact;

@EnableAutoConfiguration
@ComponentScan
@EntityScan(basePackageClasses = Contact.class)
@EnableJpaRepositories
public class AppDbConfig {

}
