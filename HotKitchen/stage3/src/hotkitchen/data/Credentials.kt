package hotkitchen.data

import kotlinx.serialization.Serializable

@Serializable
data class Credentials(
    val email: String,
    val userType: String,
    var password: String,
)