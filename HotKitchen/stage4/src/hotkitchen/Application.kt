package hotkitchen

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import hotkitchen.data.ResponseStatus
import hotkitchen.database.configureDatabase
import hotkitchen.routing.authRouting
import hotkitchen.routing.mealsAndCategoriesRouting
import hotkitchen.routing.userRouting
import hotkitchen.utils.BadRequestException
import hotkitchen.utils.ForbiddenException
import hotkitchen.utils.UnauthorizedException
import hotkitchen.utils.checkEmail
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.serialization.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    install(ContentNegotiation) { json() }
    install(Authentication) {
        jwt {
            val secret = environment.config.property("jwt.secret").getString()
            verifier(JWT.require(Algorithm.HMAC256(secret)).build())
            validate { credential ->
                try {
                    checkEmail(credential.payload.getClaim("email").asString())
                    JWTPrincipal(credential.payload)
                } catch (e: Exception) {
                    null
                }

            }
        }
    }
    install(StatusPages) {
        exception<ForbiddenException> { cause -> // 403
            call.respond(HttpStatusCode.Forbidden, ResponseStatus(cause.message ?: ""))
        }
        exception<UnauthorizedException> {  // 401
            call.respond(HttpStatusCode.Unauthorized)
        }
        exception<BadRequestException> { // 400
            call.respond(HttpStatusCode.BadRequest)
        }
    }
    authRouting()
    userRouting()
    mealsAndCategoriesRouting()
    configureDatabase()
}