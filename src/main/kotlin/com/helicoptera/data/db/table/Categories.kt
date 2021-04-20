package com.helicoptera.data.db.table

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object Categories : Table("Categories") {
    val id = integer("id").autoIncrement()
    val name = varchar("name", length = 255)

    val iconId = integer("iconId").references(Icons.id,
        onDelete = ReferenceOption.NO_ACTION,
        onUpdate = ReferenceOption.NO_ACTION,
        fkName = "FK_Icon_Id"
    )

    val userId = integer("userId").references(Users.id,
        onDelete = ReferenceOption.CASCADE,
        onUpdate = ReferenceOption.NO_ACTION,
        fkName = "FK_User_Id"
    )

    override val primaryKey = PrimaryKey(id, name = "PK_Category_ID")
}

