package com.helicoptera.data.db.model

data class User(
    val id: Int,
    val username: String,
    val password: String
)

fun User.empty() = User(0, "", "")