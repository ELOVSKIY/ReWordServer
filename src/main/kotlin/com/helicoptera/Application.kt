package com.helicoptera

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.google.gson.Gson
import com.helicoptera.authentication.session.Session
import com.helicoptera.data.db.initDB
import com.helicoptera.routing.root
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.application.*
import io.ktor.http.content.*
import io.ktor.locations.*
import io.ktor.sessions.*

fun main(args: Array<String>): Unit {
    initDB()
    io.ktor.server.netty.EngineMain.main(args)
}

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
//    val jwtIssuer = environment.config.property("jwt.domain").getString()
//    val jwtAudience = environment.config.property("jwt.audience").getString()
//    val jwtRealm = environment.config.property("jwt.realm").getString()
//    val jwtValidityMs = environment.config.property("jwt.validityMs").getString()

    val session = environment.config.property("session.authorization").getString()

    install(Locations)
    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Patch)
        method(HttpMethod.Get)
        header(HttpHeaders.Authorization)
        allowCredentials = true
        anyHost() // @TODO: Don't do this in production if possible. Try to limit it.
    }

    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
            disableHtmlEscaping()
            register(ContentType.Application.Any, GsonConverter(Gson()))
        }
    }

    install(Sessions) {
        cookie<Session>(session)
    }

//    install(Authentication) {
//        jwt {
//            realm = jwtRealm
//            verifier(makeJwtVerifier(jwtIssuer, jwtAudience))
//            validate { credential ->
//                if (credential.payload.audience.contains(jwtAudience)) {
//                    JWTPrincipal(credential.payload)
//                } else {
//                    null
//                }
//
//            }
//        }
//    }

    routing {
        static {
            static("static") {
                resources("static")
            }
        }
        this.root()
    }
}

private val algorithm = Algorithm.HMAC256("secret")
private fun makeJwtVerifier(issuer: String, audience: String): JWTVerifier = JWT
    .require(algorithm)
    .withAudience(audience)
    .withIssuer(issuer)
    .build()
