package ru.epatko.application.common

import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Service
import ru.epatko.application.app.repository.ApplicationRepository
import ru.epatko.application.user.repository.UserRepository

@Service
class CommonService(
    private val userRepository: UserRepository,
    private val applicationRepository: ApplicationRepository,
) {

    suspend fun printTables() {
        println(userRepository.findAll().toList())
        println(applicationRepository.findAll().toList())
    }
}