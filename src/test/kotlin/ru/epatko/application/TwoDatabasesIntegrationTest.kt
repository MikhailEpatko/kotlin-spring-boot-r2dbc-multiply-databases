package ru.epatko.application

import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import ru.epatko.application.app.repository.ApplicationRepository
import ru.epatko.application.user.repository.UserRepository

class TwoDatabasesIntegrationTest : BasicIT {

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var applicationRepository: ApplicationRepository

    @Test
    fun `query data from two databases`(): Unit = runBlocking {
        val users = userRepository.findAll().toList()
        val applications = applicationRepository.findAll().toList()

        assertEquals(3, users.size)
        assertEquals(3, applications.size)
    }
}