package com.helicoptera.data.db.table

import com.helicoptera.data.db.model.User
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object Users : Table("users") {

    val id = integer("id").autoIncrement()
    val username = varchar("username", length = 255).uniqueIndex()
    val password = varchar("password", length = 255)

    override val primaryKey = PrimaryKey(id, name="PK_User_ID")

    fun toUser(row: ResultRow): User {
        return User(
            id = row[id],
            username = row[username],
            password = row[password]
        )
    }
}