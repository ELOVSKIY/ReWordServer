package com.helicoptera.routing

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.helicoptera.authentication.session.Session
import com.helicoptera.data.db.model.Identifier
import com.helicoptera.data.db.model.Word
import com.helicoptera.data.db.transaction.removeWordById
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.sessions.*
import com.helicoptera.data.db.transaction.insertWord
import com.helicoptera.routing.dto.WordWithCategoryId

fun Routing.word() {

    post("/word/add") {
        val session = call.sessions.get<Session>()
        if (session != null) {
            val body = call.receive<String>()
            val wordWithCategoryId = Gson().fromJson(body, WordWithCategoryId::class.java)
            insertWord(wordWithCategoryId)
        } else
            call.respond(EmptyResponse(error = "Forbidden"))
    }

    //TODO добавить проверку что слово принадлежит пользователю
    post("word/remove") {
        val session = call.sessions.get<Session>()
        if (session != null) {
            val body = call.receive<String>()
            val identifier = Gson().fromJson(body, Identifier::class.java)
            val wordId = identifier.value
            removeWordById(wordId)
        } else
            call.respond(EmptyResponse(error = "Forbidden"))
    }
}