package ru.clevertec.gordievich.api.servlet.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "ru.clevertec.gordievich")
public class SpringDataConfiguration {



}
