package hotkitchen.routing

import hotkitchen.data.Category
import hotkitchen.data.Meal
import hotkitchen.database.DatabaseController
import hotkitchen.utils.BadRequestException
import hotkitchen.utils.ForbiddenException
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.mealsAndCategoriesRouting() = routing {
    authenticate {
        post("/meals") {
            val principal = call.principal<JWTPrincipal>()
            val userType = principal!!.payload.getClaim("userType").asString()
            if (userType != "staff") throw ForbiddenException("Access denied")
            val meal = call.receive<Meal>()
            if (DatabaseController.getMeal(meal.mealId) != null) throw BadRequestException()
            DatabaseController.saveMeal(meal)
            call.respond(HttpStatusCode.OK, meal)
        }

        post("/categories") {
            val principal = call.principal<JWTPrincipal>()
            val userType = principal!!.payload.getClaim("userType").asString()
            if (userType != "staff") throw ForbiddenException("Access denied")
            val category = call.receive<Category>()
            if (DatabaseController.getCategory(category.categoryId) != null) throw BadRequestException()
            DatabaseController.saveCategory(category)
            call.respond(HttpStatusCode.OK, category)

        }

        get("/meals") {
            val id = call.request.queryParameters["id"]
            if (id != null) {
                val meal = DatabaseController.getMeal(id.toInt()) ?: throw BadRequestException()
                call.respond(meal)
            } else
                call.respond(HttpStatusCode.OK, DatabaseController.getAllMeals())
        }

        get("/categories") {
            val id = call.request.queryParameters["id"]
            if (id != null) {
                val category = DatabaseController.getCategory(id.toInt()) ?: throw BadRequestException()
                call.respond(category)
            } else
                call.respond(HttpStatusCode.OK, DatabaseController.getAllCategories())
        }
    }
}