package com.helicoptera

private val userIdPattern = "[a-zA-Z0-9_.]+".toRegex()
internal fun userNameValid(userId: String) = userId.matches(userIdPattern)