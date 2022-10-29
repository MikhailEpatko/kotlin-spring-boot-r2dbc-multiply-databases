package ru.epatko.application.app.config

import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import io.r2dbc.spi.ConnectionFactoryOptions
import io.r2dbc.spi.ConnectionFactoryOptions.DATABASE
import io.r2dbc.spi.ConnectionFactoryOptions.DRIVER
import io.r2dbc.spi.ConnectionFactoryOptions.HOST
import io.r2dbc.spi.ConnectionFactoryOptions.PASSWORD
import io.r2dbc.spi.ConnectionFactoryOptions.PORT
import io.r2dbc.spi.ConnectionFactoryOptions.USER
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.data.r2dbc.core.DefaultReactiveDataAccessStrategy
import org.springframework.data.r2dbc.core.R2dbcEntityOperations
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.r2dbc.dialect.PostgresDialect
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.r2dbc.core.DatabaseClient
import ru.epatko.application.app.repository.ApplicationRepository

@Configuration
@EnableR2dbcRepositories(
    entityOperationsRef = "applicationEntityTemplate",
    basePackageClasses = [
        ApplicationRepository::class,
    ],
)
class ApplicationDatabaseConfig @Autowired constructor(
    private val env: Environment,
) {

    private val db = "app.db.application"

    @Bean(name = ["applicationConnectionFactory"])
    fun applicationConnectionFactory(): ConnectionFactory = ConnectionFactories.get(
        ConnectionFactoryOptions.builder()
            .option(DRIVER, env.getProperty("$db.driver")!!)
            .option(HOST, env.getProperty("$db.host")!!)
            .option(PORT, env.getProperty("$db.port")!!.toInt())
            .option(DATABASE, env.getProperty("$db.database")!!)
            .option(USER, env.getProperty("$db.user")!!)
            .option(PASSWORD, env.getProperty("$db.password")!!)
            .build()
    )

    @Bean
    fun applicationEntityTemplate(
        @Qualifier("applicationConnectionFactory") applicationConnectionFactory: ConnectionFactory,
    ): R2dbcEntityOperations {
        val strategy = DefaultReactiveDataAccessStrategy(PostgresDialect.INSTANCE)
        val databaseClient: DatabaseClient = DatabaseClient.builder()
            .connectionFactory(applicationConnectionFactory)
            .bindMarkers(PostgresDialect.INSTANCE.bindMarkersFactory)
            .build()
        return R2dbcEntityTemplate(databaseClient, strategy)
    }
}
