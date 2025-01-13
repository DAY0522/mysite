package mysite.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"mysite.service", "mysite.repository", "mysite.aspsect"})
public class AppConfig {
}
