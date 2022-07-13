package hotkitchen.database

import org.jetbrains.exposed.dao.id.UUIDTable

object UserTable : UUIDTable() {
    val name = text("name")
    val userType = text("userType")
    val phone = text("phone")
    val email = text("email").uniqueIndex()
    val address = text("address")
}