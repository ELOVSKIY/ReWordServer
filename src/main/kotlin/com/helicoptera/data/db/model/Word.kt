package com.helicoptera.data.db.model

data class Word(
    val word: String,
    val translation: String,
    val transcriptions: String,
    val categoryName: String,
    val imageUrl : String?
)
