package davidhxxx.example.angularsboot.db;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import davidhxxx.example.angularsboot.db.model.Contact;

@EnableAutoConfiguration
@ComponentScan
@EntityScan(basePackageClasses = Contact.class)
@EnableMongoRepositories
public class AppDbConfig {

}
