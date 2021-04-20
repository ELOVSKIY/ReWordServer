package com.helicoptera.data.db.transaction

import com.helicoptera.data.db.model.User
import com.helicoptera.data.db.table.Users
import com.helicoptera.data.db.table.Users.toUser
import io.ktor.html.*
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

fun insertUser(user: User) {
    transaction {
        Users.insert {
            it[username] = user.username
            it[password] = md5(user.password)
        }
    }
}

fun fetchUserByUsername(username: String) : User? {
    val users = transaction {
        Users.select { Users.username eq username }.toList()
    }
    return if (users.isEmpty()) null else toUser(users[0])
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