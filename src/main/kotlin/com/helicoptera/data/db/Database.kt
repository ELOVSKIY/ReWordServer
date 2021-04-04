package com.helicoptera.data.db

import com.helicoptera.data.db.table.Users
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun initDB() {
    Database.connect("jdbc:sqlite:file:test?mode=memory&cache=shared", "org.sqlite.JDBC")
    initSchemas()
}

private fun initSchemas() {
    transaction {
        SchemaUtils.create(Users)
    }
}