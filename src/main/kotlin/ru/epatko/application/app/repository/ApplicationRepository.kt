package ru.epatko.application.app.repository

import org.springframework.data.repository.kotlin.CoroutineSortingRepository
import org.springframework.stereotype.Repository
import ru.epatko.application.app.model.ApplicationEntity

/**
 * Added to the basePackageClasses of [ru.epatko.application.app.config.UserDatabaseConfig]
 */
@Repository
interface ApplicationRepository : CoroutineSortingRepository<ApplicationEntity, Long>