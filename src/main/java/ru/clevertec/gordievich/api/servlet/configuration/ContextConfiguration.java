package ru.clevertec.gordievich.api.servlet.configuration;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.clevertec.gordievich.infrastructure.property.DbSetting;
import ru.clevertec.gordievich.infrastructure.property.PropertiesUtil;

@Configuration
@ComponentScan(basePackages = "ru.clevertec.gordievich")
public class ContextConfiguration {

    private final DbSetting dbSetting = PropertiesUtil.getYamlFile().getDb();

    @Bean
    public Flyway flyway() {
        return Flyway.configure()
                .dataSource(dbSetting.getUrl(), dbSetting.getUsername(), dbSetting.getPassword())
                .baselineOnMigrate(true)
                .cleanDisabled(false)
                .load();
    }

}
