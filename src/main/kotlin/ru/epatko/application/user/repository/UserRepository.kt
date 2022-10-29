package ru.epatko.application.user.repository

import org.springframework.data.repository.kotlin.CoroutineSortingRepository
import org.springframework.stereotype.Repository
import ru.epatko.application.user.model.UserEntity

/**
 * Added to the basePackageClasses of [ru.epatko.application.user.config.UserDatabaseConfig]
 */
@Repository
interface UserRepository : CoroutineSortingRepository<UserEntity, Long>