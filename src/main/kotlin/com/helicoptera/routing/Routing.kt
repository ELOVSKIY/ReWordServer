package com.helicoptera.routing

import io.ktor.routing.*

fun Routing.root() {
    authorization()
    categories()
    word()
}