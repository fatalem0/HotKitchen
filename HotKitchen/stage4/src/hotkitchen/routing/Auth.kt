package hotkitchen.routing

import hotkitchen.data.Credentials
import hotkitchen.data.ResponseToken
import hotkitchen.data.SignInCredentials
import hotkitchen.database.DatabaseController
import hotkitchen.utils.*
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import java.math.BigInteger
import java.security.MessageDigest

fun md5(input: String): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
}


fun Application.authRouting() = routing {
    post("/signup") {
        val credentials = call.receive<Credentials>()
        checkEmail(credentials.email)
        checkPassword(credentials.password)
        if (DatabaseController.getCredentials(credentials.email) == null) {
            credentials.password = md5(credentials.password)
            DatabaseController.saveCredentials(credentials)
            call.respond(HttpStatusCode.OK, ResponseToken(getToken(credentials)))
        } else
            throw ForbiddenException("User already exists")

    }
    post("/signin") {
        val signInCredentials = call.receive<SignInCredentials>()
        val credentials = DatabaseController.getCredentials(signInCredentials.email)
        if (credentials != null && md5(signInCredentials.password) == credentials.password)
            call.respond(HttpStatusCode.OK, ResponseToken(getToken(credentials)))
        else
            throw ForbiddenException("Invalid email or password")
    }

    authenticate {
        get("/validate") {
            try {
                val principal = call.principal<JWTPrincipal>()
                val email = principal!!.payload.getClaim("email").asString()
                val userType = principal.payload.getClaim("userType").asString()
                call.respondText("Hello, $userType $email")
            } catch (e: Exception) {
                throw UnauthorizedException()
            }
        }
    }
}