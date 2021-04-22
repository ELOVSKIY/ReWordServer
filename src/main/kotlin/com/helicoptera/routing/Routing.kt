package com.helicoptera.routing

import com.google.gson.Gson
import com.helicoptera.data.db.model.User
import com.helicoptera.data.db.transaction.insertUser
import com.helicoptera.data.db.transaction.fetchAllUsers
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Routing.root() {
    authorization()
    categories()
    get("/users") {
        val users = fetchAllUsers()
        call.respond(users)
    }

    post("/registration") {
//        val user = call.receive<User>()
//        addUser(user)
//        call.respond("lol")
//        val body = call.receive<String>()
//        val user = Gson().fromJson(body, User::class.java)
//        if (user != null) {
//            insertUser(user)
//        }
    }

//    authenticate {
        get("/lol") {
            call.respondText("Hello World!")
        }
//    }
}