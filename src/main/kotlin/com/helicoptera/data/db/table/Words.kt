package com.helicoptera.data.db.table

import com.helicoptera.data.db.model.User
import com.helicoptera.data.db.model.Word
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object Words : Table("words") {
    val id = integer("id").autoIncrement()
    val category = integer("categoryId")

    val word = varchar("word", length = 255)
    val translation = varchar("translation", length = 255)
    val transcriptions = varchar("transcription", length = 255)
    val imageUrl= varchar("imageUrl", length = 255).nullable()


    override val primaryKey = PrimaryKey(id, name="PK_Word_ID")

}