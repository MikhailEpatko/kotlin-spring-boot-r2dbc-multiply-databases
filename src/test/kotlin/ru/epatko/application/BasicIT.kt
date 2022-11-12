package ru.epatko.application

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.containers.PostgreSQLContainer.POSTGRESQL_PORT
import org.testcontainers.ext.ScriptUtils
import org.testcontainers.jdbc.JdbcDatabaseDelegate
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName

@Testcontainers
@SpringBootTest
interface BasicIT {

    companion object {
        @Container
        val userDbPostgreSQLContainer: PostgreSQLContainer<*> = TestPostgreSQLContainer(
            DockerImageName.parse("postgres:13.5-alpine")
                .asCompatibleSubstituteFor("postgres")
        )
            .withDatabaseName("user_db")
            .withPassword("postgres")
            .withUsername("postgres")

        @Container
        val applicationDbPostgreSQLContainer: PostgreSQLContainer<*> = TestPostgreSQLContainer(
            DockerImageName.parse("postgres:13.5-alpine")
                .asCompatibleSubstituteFor("postgres")
        )
            .withDatabaseName("application_db")
            .withPassword("postgres")
            .withUsername("postgres")

        @JvmStatic
        @DynamicPropertySource
        fun postgresqlProperties(registry: DynamicPropertyRegistry) {
            registry.add("app.db.user.driver") { "postgres" }
            registry.add("app.db.user.host", userDbPostgreSQLContainer::getHost)
            registry.add("app.db.user.port") { userDbPostgreSQLContainer.getMappedPort(POSTGRESQL_PORT) }
            registry.add("app.db.user.database", userDbPostgreSQLContainer::getDatabaseName)
            registry.add("app.db.user.user", userDbPostgreSQLContainer::getUsername)
            registry.add("app.db.user.password", userDbPostgreSQLContainer::getPassword)

            registry.add("app.db.application.driver") { "postgres" }
            registry.add("app.db.application.host", applicationDbPostgreSQLContainer::getHost)
            registry.add("app.db.application.port") {
                applicationDbPostgreSQLContainer.getMappedPort(POSTGRESQL_PORT)
            }
            registry.add("app.db.application.database", applicationDbPostgreSQLContainer::getDatabaseName)
            registry.add("app.db.application.user", applicationDbPostgreSQLContainer::getUsername)
            registry.add("app.db.application.password", applicationDbPostgreSQLContainer::getPassword)
        }

        init {
            userDbPostgreSQLContainer.start()
            applicationDbPostgreSQLContainer.start()
            val userDbContainerDelegate = JdbcDatabaseDelegate(userDbPostgreSQLContainer, "")
            ScriptUtils.runInitScript(userDbContainerDelegate, "user-db/init.sql")
            val applicationDbContainerDelegate = JdbcDatabaseDelegate(applicationDbPostgreSQLContainer, "")
            ScriptUtils.runInitScript(applicationDbContainerDelegate, "app-db/init.sql")
        }
    }
}

class TestPostgreSQLContainer(imageName: DockerImageName) : PostgreSQLContainer<TestPostgreSQLContainer>(imageName)
