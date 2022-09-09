package ru.clevertec.gordievich.api.servlet.configuration;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.clevertec.gordievich.infrastructure.property.YamlPropertySourceFactory;

@Configuration
@ComponentScan(basePackages = "ru.clevertec.gordievich")
@PropertySource(value = "classpath:application.yaml", factory = YamlPropertySourceFactory.class)
public class ContextConfiguration {

    @Bean
    public Flyway flyway(@Value("${db.url}") String url,
                         @Value("${db.username}") String username,
                         @Value("${db.password}") String password) {
        return Flyway.configure()
                .dataSource(url, username, password)
                .baselineOnMigrate(true)
                .cleanDisabled(false)
                .load();
    }
}
