package ru.rubik.app.repository.task.impl

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.transactions.transaction
import ru.rubik.app.database.DatabaseFactory
import ru.rubik.app.entity.TaskStatus
import ru.rubik.app.entity.TasksTable
import ru.rubik.app.model.Task
import ru.rubik.app.repository.task.TaskRepository
import ru.rubik.app.request.task.TaskCreateRequest

class TaskRepositoryImpl: TaskRepository {
    override suspend fun getTasks(): List<Task?> {
        var tasks: List<Task?> = listOf()

        transaction {
            tasks = TasksTable.selectAll()
                .map {  rowToTask(it)  }
                .toList()
        }
        return tasks
    }

    override suspend fun saveTask(request: TaskCreateRequest): Task? {
        var statement: InsertStatement<Number>? = null
        DatabaseFactory.dbQuery {
            statement = TasksTable.insert {
                it[context] = request.context
                it[status] = TaskStatus.TODO
            }
        }
        return rowToTask(statement?.resultedValues?.get(0))
    }

    private fun rowToTask(row: ResultRow?): Task? {
        return if (row == null) null
        else Task(
            id = row[TasksTable.id],
            context = row[TasksTable.context],
            status = row[TasksTable.status],
        )
    }
}