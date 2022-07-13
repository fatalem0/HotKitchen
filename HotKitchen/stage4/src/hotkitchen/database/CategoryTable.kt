package hotkitchen.database

import org.jetbrains.exposed.dao.id.UUIDTable

object CategoryTable : UUIDTable() {
    val categoryId = integer("categoryId").autoIncrement()
    val title = text("title")
    val description = text("description")
}