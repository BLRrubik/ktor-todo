package ru.rubik.app.repository.task.impl

import org.jetbrains.exposed.sql.transactions.transaction
import ru.rubik.app.entity.TaskEntity
import ru.rubik.app.entity.TaskStatus
import ru.rubik.app.entity.TasksTable
import ru.rubik.app.entity.UserEntity
import ru.rubik.app.repository.task.TaskRepository
import ru.rubik.app.request.task.TaskCreateRequest

class TaskRepositoryImpl: TaskRepository {
    override suspend fun getTasks(userId: Long): List<TaskEntity> {
        return transaction{
            TaskEntity.find { TasksTable.ownerId eq userId }.toList()
        }
    }

    override suspend fun saveTask(request: TaskCreateRequest, userEntity: UserEntity): TaskEntity {

        return transaction {
            TaskEntity.new {
                context = request.context
                status = TaskStatus.TODO
                owner = userEntity
            }
        }
    }
}