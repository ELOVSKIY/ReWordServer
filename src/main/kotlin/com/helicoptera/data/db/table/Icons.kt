package com.helicoptera.data.db.table

import org.jetbrains.exposed.sql.Table

object Icons : Table("Icons") {
    val id = integer("id").autoIncrement()
    //TODO может ли не хватить длинны?
    val url = varchar("url", length = 255).uniqueIndex()

    override val primaryKey = PrimaryKey(id, name="PK_Icon_ID")
}