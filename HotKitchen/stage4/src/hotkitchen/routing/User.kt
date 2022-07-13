package hotkitchen.routing

import hotkitchen.data.User
import hotkitchen.database.DatabaseController
import hotkitchen.utils.BadRequestException
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.userRouting() = routing {

    authenticate {
        get("/me") {
            val principal = call.principal<JWTPrincipal>()
            val email = principal!!.payload.getClaim("email").asString()
            val user = DatabaseController.getUser(email) ?: throw BadRequestException()
            call.respond(HttpStatusCode.OK, user)
        }

        put("/me") {
            val principal = call.principal<JWTPrincipal>()
            val email = principal!!.payload.getClaim("email").asString()
            val user = call.receive<User>()
            if (user.email != email) throw BadRequestException()
            if (DatabaseController.getUser(email) == null)
                DatabaseController.saveUser(user)
            else
                DatabaseController.updateUser(user)
            call.respond(HttpStatusCode.OK, user)
        }

        delete("/me") {
            val principal = call.principal<JWTPrincipal>()
            val email = principal!!.payload.getClaim("email").asString()
            val result = DatabaseController.deleteUser(email)
            DatabaseController.deleteCredentials(email)
            if (result == 1)
                call.respond(HttpStatusCode.OK)
            else
                call.respond(HttpStatusCode.BadRequest)
        }
    }
}