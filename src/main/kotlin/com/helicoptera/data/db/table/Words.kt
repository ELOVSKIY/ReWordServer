package com.helicoptera.data.db.table

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object Words : Table("words") {
    val id = integer("id").autoIncrement()
    val category = integer("categoryId").references(
        Categories.iconId,
        onDelete = ReferenceOption.NO_ACTION,
        onUpdate = ReferenceOption.NO_ACTION,
        fkName = "FK_Category_Id"
    )

    val word = varchar("word", length = 255)
    val translation = varchar("translation", length = 255)
    val transcriptions = varchar("transcription", length = 255)
    val imageUrl = varchar("imageUrl", length = 255).nullable()


    override val primaryKey = PrimaryKey(id, name = "PK_Word_ID")

}