package ru.rubik.app.entity

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.LongIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.transactions.transaction
import ru.rubik.app.dto.TaskDto
import ru.rubik.app.entity.TaskEntity.Companion.referrersOn

object TasksTable: LongIdTable("tasks") {
    val context = varchar("context", 256)
    val status = enumerationByName("status", 24, TaskStatus::class )
    val ownerId = reference("owner_id", UsersTable)
}

class TaskEntity(id: EntityID<Long>) : Entity<Long>(id) {
    companion object : EntityClass<Long, TaskEntity>(TasksTable)

    var context by TasksTable.context
    var status  by TasksTable.status
    var owner by UserEntity referencedOn TasksTable.ownerId

    fun toDto(): TaskDto {
        return TaskDto(
            id.value, context, status,
            transaction {
                owner.toDto()
            }
        )
    }
}

enum class TaskStatus {
    TODO,
    IN_PROGRESS,
    DONE
}