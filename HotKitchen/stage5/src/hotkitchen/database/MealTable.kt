package hotkitchen.database

import org.jetbrains.exposed.dao.id.UUIDTable

object MealTable : UUIDTable() {
    val mealId            = integer("mealId").autoIncrement()
    val title             = text("title")
    val price            = float("price")
    val imageUrl        = text("imageUrl")
    val jsonCategoryIds = text("jsonCategoryIds")
}