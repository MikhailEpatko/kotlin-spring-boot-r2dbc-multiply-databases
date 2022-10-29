package ru.epatko.application

import kotlinx.coroutines.runBlocking
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import ru.epatko.application.common.CommonService

@SpringBootApplication
class Application(
    private val commonService: CommonService,
) : CommandLineRunner {

    override fun run(vararg args: String?) = runBlocking { commonService.printTables() }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
