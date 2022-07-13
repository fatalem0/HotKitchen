package hotkitchen.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.typesafe.config.ConfigFactory
import hotkitchen.data.Credentials
import io.ktor.config.*
import java.util.*

val emailRegex = """(^[a-zA-Z0-9_+-.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+${'$'})""".toRegex()
val secret = HoconApplicationConfig(ConfigFactory.load()).property("jwt.secret").getString()

fun getToken(credentials: Credentials): String = JWT.create()
    .withClaim("email", credentials.email)
    .withClaim("userType", credentials.userType)
    .withExpiresAt(Date(System.currentTimeMillis() + 60 * 60 * 1000))
    .sign(Algorithm.HMAC256(secret))

fun checkEmail(email: String) {
    if (!email.matches(emailRegex)) throw ForbiddenException("Invalid email")
}

fun checkPassword(password: String) {
    if (password.length < 6 ||
        password.all { it.isLetter() } ||
        password.all { it.isDigit() } ||
        password.all { !it.isLetter() && !it.isDigit() }
    )
        throw ForbiddenException("Invalid password")
}