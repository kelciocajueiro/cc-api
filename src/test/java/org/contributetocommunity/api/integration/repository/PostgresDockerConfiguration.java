package org.contributetocommunity.api.integration.repository;

import lombok.extern.slf4j.Slf4j;
import org.contributetocommunity.api.infra.PostgresContainer;
import org.junit.ClassRule;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.shaded.com.google.common.base.Preconditions;

/**
 * Initializes the Container and set application properties
 * from the Container to the Spring context
 */
@Slf4j
@SpringBootTest
abstract class PostgresDockerConfiguration {

    @ClassRule
    public static PostgresContainer container = PostgresContainer.getInstance();

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {

        Preconditions.checkState(container.isRunning(), "PostgresContainer must be started first");

        String postgresUri = String.format(
                "jdbc:postgresql://%s:%d/ccapi", container.getHost(), container.getMappedPort(5432));

        registry.add("spring.datasource.url", () -> postgresUri);
        registry.add("spring.datasource.username", () -> "ccapi_user");
        registry.add("spring.datasource.password", () -> "ccapi_pass");

        log.info("Postgres connection string for Integration tests: {}", postgresUri);
    }

}
