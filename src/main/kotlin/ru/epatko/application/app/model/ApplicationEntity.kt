package ru.epatko.application.app.model

import java.time.Instant
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("application")
data class ApplicationEntity(
    @Id
    val id: Long? = null,
    val name: String,
    val description: String,
    val createdAt: Instant = Instant.now(),
    val modifiedAt: Instant = Instant.now(),
)