package com.helicoptera.routing

import com.helicoptera.data.db.model.User
import com.helicoptera.data.db.transaction.addUser
import com.helicoptera.data.db.transaction.fetchAllUsers
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Routing.root() {
    get("/users") {
        val users = fetchAllUsers()
        call.respond(users)
    }

    post("/registration") {
        val user = call.receive<User>()
        addUser(user)
        call.respond("OK")
    }

    authenticate {
        get("/lol") {
            call.respondText("Hello World!")
        }
    }
}