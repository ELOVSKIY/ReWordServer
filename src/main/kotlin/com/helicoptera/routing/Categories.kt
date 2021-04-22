package com.helicoptera.routing

import com.helicoptera.authentication.session.Session
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.sessions.*

fun Routing.categories() {
    post("/categories") {
        val user = call.sessions.get<Session>()
        if (user != null) {
            call.respond("SUCCESS")
        } else {
            call.respond("PIZDA")
        }
    }
}