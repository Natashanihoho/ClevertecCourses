package ru.clevertec.gordievich.api.flyway;

import javax.annotation.PreDestroy;
import org.flywaydb.core.Flyway;
import org.springframework.stereotype.Component;

@Component
public record FlywayMigration(Flyway flyway) {

    @PreDestroy
    private void destroy() {
        flyway.clean();
    }

}
