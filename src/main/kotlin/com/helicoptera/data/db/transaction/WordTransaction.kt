package com.helicoptera.data.db.transaction

import com.helicoptera.data.db.model.Word
import com.helicoptera.data.db.table.Words
import com.helicoptera.routing.dto.WordWithCategoryId
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

fun fetchWordsByCategoryId(categoryId: Int): List<Word> {
    return transaction {
        Words.select {
            Words.categoryId eq categoryId
        }.map {
            Word(it[Words.word],
                it[Words.translation],
                it[Words.transcriptions])
        }
    }
}

fun removeWordById(wordId: Int) {
    transaction {
        Words.deleteWhere {
            Words.id eq  wordId
        }
    }
}

fun insertWord(wordWithCategoryId: WordWithCategoryId) {
    transaction {
        Words.insert {
            it[Words.word] = wordWithCategoryId.word
            it[Words.translation] = wordWithCategoryId.translation
            it[Words.transcriptions] = wordWithCategoryId.transcriptions
            it[Words.categoryId] = wordWithCategoryId.categoryId
        }
    }
}