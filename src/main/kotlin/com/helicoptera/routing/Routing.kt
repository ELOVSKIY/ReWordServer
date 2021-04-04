package com.helicoptera.routing

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Routing.root() {
    get("/") {
        call.respondText("Hello World!")
    }

    post("/") {
        val post = call.receive<String>()
        call.respondText("Received $post from the post bidy.", ContentType.Text.Plain )
    }

    authenticate {
        get("/lol") {
            call.respondText("Hello World!")
        }
    }
}