package com.helicoptera.data.db.transaction

import com.helicoptera.data.db.model.Category
import com.helicoptera.data.db.table.Categories
import com.helicoptera.data.db.table.Icons
import org.jetbrains.exposed.sql.Join
import org.jetbrains.exposed.sql.JoinType
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

fun insertCategory(name: String, userId: Int, iconId: Int) {
    transaction {
        Categories.insert {
            it[Categories.name] = name
            it[Categories.userId] = userId
            it[Categories.iconId] = iconId
        }
    }
}

fun fetchAllCategories(userId: Int): List<Category> {
    val categories = mutableListOf<Category>()
    transaction {
        val categoriesResultRows = Join(Categories, Icons,
            joinType = JoinType.INNER,
            additionalConstraint = { Categories.iconId eq Icons.id })
            .select {
                Categories.userId eq userId
            }
        categories.addAll(
            categoriesResultRows.map {
                Category(it[Categories.name], it[Icons.url])
            }
        )
    }

    return categories;
}