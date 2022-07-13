package hotkitchen

import hotkitchen.database.configureDatabase
import hotkitchen.routing.authRouting
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.serialization.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) { json() }
    authRouting()
    configureDatabase()
}