package com.helicoptera.data.db.transaction

import com.helicoptera.data.db.model.User
import com.helicoptera.data.db.table.Users
import com.helicoptera.data.db.table.Users.toUser
import io.ktor.html.*
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

fun addUser(user: User) {
    transaction {
        Users.insert {
            it[username] = user.username
            it[password] = md5(user.password)
        }
    }
}

fun fetchAllUsers() : List<User> {
    val users = mutableListOf<User>()
    transaction {
        val query = Users.selectAll()
        query.forEach {
            val user = toUser(it)
            users.add(user)
        }

    }

    return users
}