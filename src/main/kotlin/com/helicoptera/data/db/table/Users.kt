package com.helicoptera.data.db.table

import com.helicoptera.data.db.model.User
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object Users : Table("users") {
    val id = integer("id").autoIncrement().primaryKey()
    val name = varchar("name", length = 50)

    fun toUser(row: ResultRow) : User {
        return User(
            name = row[Users.name]
        )
    }
}