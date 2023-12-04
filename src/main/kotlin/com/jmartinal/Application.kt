package com.jmartinal

import com.jmartinal.plugins.routing.configureRouting
import com.jmartinal.plugins.serialization.configureSerialization
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.cors.routing.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()
        configureSerialization()
        install(CORS) {
            anyHost() // In a complex app we should only allow known hosts, not every host
            allowNonSimpleContentTypes = true // To be able to send complex content via POST requests
            allowHeader(HttpHeaders.AccessControlAllowOrigin) // The header that the client uses to send CORS information
            allowMethod(HttpMethod.Put)
            allowMethod(HttpMethod.Delete)
        }
    }.start(wait = true)
}