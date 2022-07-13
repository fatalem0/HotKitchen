package hotkitchen.data

import kotlinx.serialization.Serializable

@Serializable
data class ResponseStatus(
    var status: String
)