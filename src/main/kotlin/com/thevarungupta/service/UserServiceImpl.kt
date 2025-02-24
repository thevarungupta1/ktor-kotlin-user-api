package com.thevarungupta.service

import com.thevarungupta.db.DatabaseFactory.dbQuery
import com.thevarungupta.db.UserTable
import org.jetbrains.exposed.sql.statements.InsertStatement
import com.thevarungupta.model.User
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class UserServiceImpl : UserService {

    override suspend fun registerUser(params: CreateUserParams): User? {
        var statement: InsertStatement<Number>? = null
        dbQuery {
            statement = UserTable.insert {
                it[email] = params.email
                it[password] = params.password
                it[fullName] = params.fullName
                it[avatar] = params.avatar
            }
        }
        return rowToUser(statement?.resultedValues?.get(0))
    }

    override suspend fun findUserByEmail(email: String): User? {
        val user = dbQuery {
            UserTable.select { UserTable.email.eq(email) }
                .map { rowToUser(it) }.singleOrNull()
        }
        return user
    }

    private fun rowToUser(row: ResultRow?): User? {
        return if (row == null) null
        else User(
            id = row[UserTable.id],
            fullName = row[UserTable.fullName],
            avatar = row[UserTable.avatar],
            email = row[UserTable.email],
            createdAt = row[UserTable.createdAt].toString(),
        )
    }
}