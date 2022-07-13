package hotkitchen.database

import org.jetbrains.exposed.dao.id.UUIDTable

object OrderTable : UUIDTable() {
    val orderId = integer("orderId").autoIncrement()
    val userEmail = text("userEmail")
    val jsonMealsIds = text("jsonMealsIds")
    val price = float("price")
    val address = text("address")
    val status = text("status")
}