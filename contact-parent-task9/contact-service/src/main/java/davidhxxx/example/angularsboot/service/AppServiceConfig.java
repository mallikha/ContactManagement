package davidhxxx.example.angularsboot.service;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import davidhxxx.example.angularsboot.db.AppDbConfig;

@Import(AppDbConfig.class)
@EnableAutoConfiguration
@ComponentScan
public class AppServiceConfig {

}
