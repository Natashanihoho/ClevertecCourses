package ru.clevertec.gordievich.api.servlet.flyway;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.flywaydb.core.Flyway;
import org.springframework.stereotype.Component;

@Component
public record FlywayMigration(Flyway flyway) {

    @PostConstruct
    private void init() {
        flyway.migrate();
    }

    @PreDestroy
    private void destroy() {
        flyway.clean();
    }

}
