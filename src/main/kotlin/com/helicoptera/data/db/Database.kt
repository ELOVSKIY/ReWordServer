package com.helicoptera.data.db

import com.helicoptera.data.db.table.Categories
import com.helicoptera.data.db.table.Icons
import com.helicoptera.data.db.table.Users
import com.helicoptera.data.db.table.Words
import com.helicoptera.data.db.transaction.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun initDB() {
//    Database.connect("jdbc:sqlite:file:test?mode=memory&cache=shared", "org.sqlite.JDBC")
    Database.connect("jdbc:h2:./myh2file", "org.h2.Driver")
    initSchemas()
}

private fun initSchemas() {
    transaction {
        SchemaUtils.create(Users, Icons, Categories, Words)
//
//        try {
//            insert("static/video.png")
//        } catch (e: Exception) { }
//
//
//        val iconId = fetchAllIcons()[0].id
//        val users = fetchAllUsers()
//
//        try {
//            for (user in users) {
//                val userId = user.id
//                for (i in 1..10) {
//                    insertCategory("video$i", userId, iconId)
//                }
//            }
//        } catch (e: Exception) {
//            print(e.message)
//        }
//
//
//        val userId = users[0].id
//        val categories = fetchAllCategoriesByUseId(userId)
//        print(categories)
    }
}