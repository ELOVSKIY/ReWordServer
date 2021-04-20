package com.helicoptera.routing

import io.ktor.locations.*

@Location("/authorization")
data class Authorization(val username: String, val password: String)