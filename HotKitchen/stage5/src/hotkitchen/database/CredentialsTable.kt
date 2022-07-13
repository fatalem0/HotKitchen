package hotkitchen.database

import org.jetbrains.exposed.dao.id.UUIDTable

object CredentialsTable : UUIDTable() {
    val email = text("email").uniqueIndex()
    val userType = text("userType")
    val password = text("password")
}