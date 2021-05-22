package com.helicoptera.data.db.model

data class Category(
    val id: Int,
    val name: String,
    val iconUrl: String,
    val selected: Boolean = false,
)
