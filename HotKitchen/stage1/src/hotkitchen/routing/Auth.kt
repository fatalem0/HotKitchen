package hotkitchen.routing

import hotkitchen.data.Credentials
import hotkitchen.data.ResponseStatus
import hotkitchen.data.SignInCredentials
import hotkitchen.database.DatabaseController
import io.ktor.application.*
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
        if (DatabaseController.getCredentials(credentials.email) == null) {
            credentials.password = md5(credentials.password)
            DatabaseController.saveCredentials(credentials)
            call.respond(HttpStatusCode.OK, ResponseStatus("Signed In"))
        } else
            call.respond(HttpStatusCode.Forbidden, ResponseStatus("Registration failed"))

    }
    post("/signin") {
        val signUpCredentials = call.receive<SignInCredentials>()
        val credentials = DatabaseController.getCredentials(signUpCredentials.email)
        if (credentials != null && md5(signUpCredentials.password) == credentials.password)
            call.respond(HttpStatusCode.OK, ResponseStatus("Signed Up"))
        else
            call.respond(HttpStatusCode.Forbidden, ResponseStatus("Authorization failed"))
    }
}