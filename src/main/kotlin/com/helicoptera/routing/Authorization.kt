package com.helicoptera.routing

import com.google.gson.Gson
import com.helicoptera.authentication.session.Session
import com.helicoptera.data.db.model.User
import com.helicoptera.data.db.transaction.insertUser
import com.helicoptera.data.db.transaction.fetchUserByUsername
import com.helicoptera.data.db.transaction.md5
import com.helicoptera.userNameValid
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.sessions.*

fun Routing.authorization() {
    post("/authorization") {
        val body = call.receive<String>()
        val form = Gson().fromJson(body, User::class.java)
        val user = call.sessions.get<Session>()
        if (user != null) {

        } else {
            if (form.password.length < 6) {
                call.respond(AuthorizationResponse(error = "Password should be at least 6 characters long"))
            } else if (form.username.length < 4) {
                call.respond(AuthorizationResponse(error = "Login should be at least 4 characters long"))
            } else if (!userNameValid(form.username)) {
                call.respond(AuthorizationResponse(error = "Login should be consists of digits, letters, dots or underscores"))
//            } else if (fetchUserByUsername(form.username) != null) {
//                call.respond(AuthorizationResponse(error = "User with the following login is already registered"))
            } else {
                val hash = md5(form.password)
                val newUser = User(0, form.username, hash)

                try {
                    insertUser(newUser)
                } catch (e: Throwable) {
//                    if (dao.user(form.userId) != null) {
//                        call.respond(AuthorizationResponse(error = "User with the following login is already registered"))
//                    } else if (dao.userByEmail(form.email) != null) {
//                        call.respond(AuthorizationResponse(error = "User with the following email ${form.email} is already registered"))
//                    } else {
//                        application.environment.log.error("Failed to register user", e)
//                        call.respond(AuthorizationResponse(error = "Failed to register"))
//                    }

//                    val response = Gson().toJson(AuthorizationResponse(newUser))
//                    call.sessions.set(Session(newUser.username))
//                    call.response.headers.append(HttpHeaders.Accept, "application/json")
//                    call.respond(response)
                }

                call.response.headers.append(HttpHeaders.Accept, "application/json")
                call.sessions.set(Session(newUser.username))
                val response = Gson().toJson(AuthorizationResponse(newUser))
                call.respond(response)
            }
        }
    }
}