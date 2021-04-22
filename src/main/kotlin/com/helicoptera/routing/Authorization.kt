package com.helicoptera.routing

import com.google.gson.Gson
import com.helicoptera.authentication.session.Session
import com.helicoptera.data.db.model.User
import com.helicoptera.data.db.transaction.fetchUserById
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
        suspend fun postSuccess(user: User) {
            call.response.headers.append(HttpHeaders.Accept, "application/json")
            call.sessions.set(Session(user.id))
            val response = Gson().toJson(AuthorizationResponse(user))
            call.respond(response)
        }

        val body = call.receive<String>()
        val form = Gson().fromJson(body, User::class.java)
        val session = call.sessions.get<Session>()
        if (session != null) {
            val user = fetchUserById(session.userid)
            if (user != null) {
                postSuccess(user)
            } else {
                call.sessions.clear<Session>()
                call.respond(AuthorizationResponse(error = "Error with session"))
            }
        } else {
            if (form.password.length < 6) {
                call.respond(AuthorizationResponse(error = "Password should be at least 6 characters long"))
            } else if (form.username.length < 4) {
                call.respond(AuthorizationResponse(error = "Login should be at least 4 characters long"))
            } else if (!userNameValid(form.username)) {
                call.respond(AuthorizationResponse(error = "Login should be consists of digits, letters, dots or underscores"))
            } else {
                val hash = md5(form.password)
                val newUser = User(0, form.username, hash)

                try {
                    insertUser(newUser)
                } catch (ignore: Throwable) {
                }

                val user = fetchUserByUsername(newUser.username)
                if (user != null) {
                    postSuccess(user)
                } else {
                    call.respond(AuthorizationResponse(error = "Error in database"))
                }
            }
        }
    }
}

private fun fetchUserId(username: String): Int? {
    val user = fetchUserByUsername(username)
    return user?.id
}