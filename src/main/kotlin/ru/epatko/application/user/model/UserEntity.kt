package ru.epatko.application.user.model

import java.time.Instant
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("app_user")
data class UserEntity(
    @Id
    val id: Long? = null,
    val name: String,
    val avatar: String,
    val position: String,
    val createdAt: Instant = Instant.now(),
    val modifiedAt: Instant = Instant.now(),
)