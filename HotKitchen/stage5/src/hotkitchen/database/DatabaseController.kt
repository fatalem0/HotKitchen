package hotkitchen.database

import hotkitchen.data.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseController {
    suspend fun getCredentials(email: String) = transaction {
        val query = CredentialsTable.select { (CredentialsTable.email eq email) }
        query.mapNotNull {
            Credentials(
                email = it[CredentialsTable.email],
                userType = it[CredentialsTable.userType],
                password = it[CredentialsTable.password]
            )
        }.singleOrNull()
    }

    suspend fun saveCredentials(credentials: Credentials) = transaction {
        CredentialsTable.insert {
            it[email] = credentials.email
            it[userType] = credentials.userType
            it[password] = credentials.password
        }
    }

    suspend fun getUser(email: String) = transaction {
        val query = UserTable.select { (UserTable.email eq email) }
        query.mapNotNull {
            User(
                name = it[UserTable.name],
                userType = it[UserTable.userType],
                phone = it[UserTable.phone],
                email = it[UserTable.email],
                address = it[UserTable.address]
            )
        }.singleOrNull()
    }

    suspend fun saveUser(user: User) = transaction {
        UserTable.insert {
            it[name] = user.name
            it[userType] = user.userType
            it[phone] = user.phone
            it[email] = user.email
            it[address] = user.address
        }
    }

    suspend fun updateUser(user: User) = transaction {
        UserTable.update {
            it[name] = user.name
            it[userType] = user.userType
            it[phone] = user.phone
            it[address] = user.address
        }
    }

    suspend fun deleteUser(email: String) = transaction {
        return@transaction UserTable.deleteWhere { (UserTable.email eq email) }
    }


    suspend fun deleteCredentials(email: String) = transaction {
        return@transaction CredentialsTable.deleteWhere { (CredentialsTable.email eq email) }
    }


    suspend fun saveMeal(meal: Meal) = transaction {
        MealTable.insert {
            it[mealId] = meal.mealId
            it[title] = meal.title
            it[price] = meal.price
            it[imageUrl] = meal.imageUrl
            it[jsonCategoryIds] = Json.encodeToString(meal.categoryIds)
        }
    }

    suspend fun getMeal(mealId: Int) = transaction {
        val query = MealTable.select { (MealTable.mealId eq mealId) }
        query.mapNotNull {
            Meal(
                mealId = it[MealTable.mealId],
                title = it[MealTable.title],
                price = it[MealTable.price],
                imageUrl = it[MealTable.imageUrl],
                categoryIds = Json.decodeFromString(it[MealTable.jsonCategoryIds])
            )
        }.singleOrNull()
    }

    suspend fun saveCategory(category: Category) = transaction {
        CategoryTable.insert {
            it[categoryId] = category.categoryId
            it[title] = category.title
            it[description] = category.description
        }
    }

    suspend fun getCategory(categoryId: Int) = transaction {
        val query = CategoryTable.select { (CategoryTable.categoryId eq categoryId) }
        query.mapNotNull {
            Category(
                categoryId = it[CategoryTable.categoryId],
                title = it[CategoryTable.title],
                description = it[CategoryTable.description],
            )
        }.singleOrNull()
    }

    suspend fun getAllMeals(): List<Meal> = transaction {
        MealTable.selectAll().map {
            Meal(
                mealId = it[MealTable.mealId],
                title = it[MealTable.title],
                price = it[MealTable.price],
                imageUrl = it[MealTable.imageUrl],
                categoryIds = Json.decodeFromString(it[MealTable.jsonCategoryIds])
            )
        }
    }

    suspend fun getAllCategories(): List<Category> = transaction {
        CategoryTable.selectAll().map {
            Category(
                categoryId = it[CategoryTable.categoryId],
                title = it[CategoryTable.title],
                description = it[CategoryTable.description],
            )
        }
    }

    suspend fun saveOrder(order: Order) = transaction {
        OrderTable.insert {
            it[orderId] = order.orderId
            it[userEmail] = order.userEmail
            it[jsonMealsIds] = Json.encodeToString(order.mealsIds)
            it[price] = order.price
            it[address] = order.address
            it[status] = order.status
        }
    }

    suspend fun getOrder(orderId: Int) = transaction {
        val query = OrderTable.select { (OrderTable.orderId eq orderId) }
        query.mapNotNull {
            Order(
                orderId = it[OrderTable.orderId],
                userEmail = it[OrderTable.userEmail],
                mealsIds = Json.decodeFromString(it[OrderTable.jsonMealsIds]),
                price = it[OrderTable.price],
                address = it[OrderTable.address],
                status = it[OrderTable.status],
            )
        }.singleOrNull()
    }

    suspend fun updateOrder(order: Order) = transaction {
        OrderTable.update({OrderTable.orderId eq order.orderId}) {
            it[status] = order.status
        }
    }

    suspend fun getAllOrders(): List<Order> = transaction {
        OrderTable.selectAll().map {
            Order(
                orderId = it[OrderTable.orderId],
                userEmail = it[OrderTable.userEmail],
                mealsIds = Json.decodeFromString(it[OrderTable.jsonMealsIds]),
                price = it[OrderTable.price],
                address = it[OrderTable.address],
                status = it[OrderTable.status]
            )
        }
    }
    suspend fun getAllIncompleteOrders(): List<Order> = transaction {
        OrderTable.select { OrderTable.status eq "COOK" }.map {
            Order(
                orderId = it[OrderTable.orderId],
                userEmail = it[OrderTable.userEmail],
                mealsIds = Json.decodeFromString(it[OrderTable.jsonMealsIds]),
                price = it[OrderTable.price],
                address = it[OrderTable.address],
                status = it[OrderTable.status]
            )
        }
    }
}