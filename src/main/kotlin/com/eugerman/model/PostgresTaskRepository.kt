package com.eugerman.model

import com.eugerman.db.TaskEntity
import com.eugerman.db.TaskTable
import com.eugerman.db.entityToModel
import com.eugerman.db.suspendTransaction
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere

class PostgresTaskRepository : TaskRepository {
    override suspend fun allTasks(): List<Task> = suspendTransaction {
        TaskEntity.all().map(::entityToModel)
    }

    override suspend fun tasksByPriority(priority: Priority): List<Task> = suspendTransaction {
        TaskEntity
            .find { TaskTable.priority eq priority.toString() }
            .map { taskEntity -> entityToModel(taskEntity) }
    }

    override suspend fun taskByName(name: String): Task? = suspendTransaction {
        TaskEntity
            .find { TaskTable.name eq name }
            .map { taskEntity -> entityToModel(taskEntity) }
            .singleOrNull()
    }

    override suspend fun addTask(task: Task): Unit = suspendTransaction {
        TaskEntity.new {
            name = task.name
            description = task.description
            priority = task.priority.toString()
        }
    }

    override suspend fun removeTask(name: String): Boolean = suspendTransaction {
        val removedRows = TaskTable.deleteWhere {
            TaskTable.name eq name
        }
        removedRows == 1
    }
}