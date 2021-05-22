package com.helicoptera.routing

import com.google.gson.Gson
import com.helicoptera.authentication.session.Session
import com.helicoptera.data.db.model.CategoryStatus
import com.helicoptera.data.db.transaction.changeCategoryStatus
import com.helicoptera.data.db.transaction.fetchAllCategoriesByUseId
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.sessions.*

fun Routing.categories() {
    post("/categories") {
        val session = call.sessions.get<Session>()
        if (session != null) {
            val categories = fetchAllCategoriesByUseId(session.userid)
            val response = Gson().toJson(CategoriesResponse(categories))
            call.respond(response)
        } else {
            call.respond(CategoriesResponse(error = "Forbidden"))
        }
    }

    post("/categories/select") {
        val session = call.sessions.get<Session>()
        if (session != null) {
            val body = call.receive<String>()
            val categoryStatus = Gson().fromJson(body, CategoryStatus::class.java)
            changeCategoryStatus(categoryStatus.categoryId, categoryStatus.selected)
            call.respond(EmptyResponse())
        } else {
            call.respond(EmptyResponse(error = "Forbidden"))
        }
    }

    post("categories/words") {

    }
}