package com.helicoptera.data.db.transaction

import com.helicoptera.data.db.table.Users
import io.ktor.html.*
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

fun createUser(name: String) {
    transaction {
        Users.insert {
            it[Users.name] = name
        }
    }
}