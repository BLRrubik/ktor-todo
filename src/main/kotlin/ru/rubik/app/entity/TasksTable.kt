package ru.rubik.app.entity

import org.jetbrains.exposed.sql.Table

object TasksTable: Table("tasks") {
    val id = long("task_id").primaryKey().autoIncrement()
    val context = varchar("context", 256)
    val status = enumerationByName("status", 24, TaskStatus::class )
//    val ownerId = long("owner_id") references (UsersTable.id)
}

enum class TaskStatus {
    TODO,
    IN_PROGRESS,
    DONE
}