package com.helicoptera.data.db.transaction

import com.helicoptera.data.db.model.Icon
import com.helicoptera.data.db.table.Icons
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

fun insert(url: String) {
    transaction {
        Icons.insert {
            it[Icons.url] = url
        }
    }
}

fun fetchAllIcons() : List<Icon> {
    return transaction {
        val iconResultRows = Icons.selectAll()
        iconResultRows.map {
            Icon(it[Icons.id], it[Icons.url])
        }
    }
}