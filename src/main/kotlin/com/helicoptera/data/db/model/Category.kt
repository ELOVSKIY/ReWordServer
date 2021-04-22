package com.helicoptera.data.db.model

data class Category(
    val categoryId: Int,
    val name: String,
    val iconUrl: String,
    val selected: Boolean = false,
)
