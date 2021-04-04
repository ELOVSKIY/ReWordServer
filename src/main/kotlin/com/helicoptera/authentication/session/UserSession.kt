package com.helicoptera.authentication.session

data class UserSession(
    val username: String,
    val token: String
)