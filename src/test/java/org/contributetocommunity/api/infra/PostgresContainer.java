package org.contributetocommunity.api.infra;

import lombok.SneakyThrows;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.images.builder.ImageFromDockerfile;

import java.nio.file.Paths;
import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;

/**
 * This is a Singleton container, which means that is only started once for some IT test classes
 */
public class PostgresContainer extends GenericContainer<PostgresContainer> {

    private static PostgresContainer container;

    /**
     * Creates a Container based on a specified Dockerfile
     */
    private PostgresContainer() {
        super(new ImageFromDockerfile()
                .withDockerfile(Paths.get("src/test/resources/Dockerfile-test")));
    }

    /**
     * Exposes default ports
     * Defines a container name
     * Add a TCP container port that should be bound to a fixed port on the docker host
     * Activates container reuse
     * Starts the container
     */
    @SneakyThrows
    public static PostgresContainer getInstance() {
        if (container == null) {
            container = new PostgresContainer()
                    .withExposedPorts(5432)
                    .withCreateContainerCmdModifier(createContCmd -> createContCmd.withName("postgres-test"))
                    .withReuse(true);

            container.addFixedExposedPort(5433, 5432);

            container.setWaitStrategy(Wait.defaultWaitStrategy()
                    .withStartupTimeout(Duration.of(60, SECONDS)));

            container.start();
        }

        return container;
    }

    /**
     * JVM needs to handle the stop, not Testcontainers
     */
    @Override
    public void stop() {
        assert true;
    }

}
