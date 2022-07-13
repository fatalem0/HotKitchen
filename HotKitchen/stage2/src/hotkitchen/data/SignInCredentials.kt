package hotkitchen.data

import kotlinx.serialization.Serializable

@Serializable
data class SignInCredentials(
    val email: String,
    val password: String,
)
