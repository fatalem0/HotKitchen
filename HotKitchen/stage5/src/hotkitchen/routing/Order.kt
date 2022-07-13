package hotkitchen.routing

import hotkitchen.data.Order
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

fun Application.orderRouting() = routing {
    authenticate {
        post("/order") {
            val principal = call.principal<JWTPrincipal>()
            val email = principal!!.payload.getClaim("email").asString()
            val user = DatabaseController.getUser(email) ?: throw BadRequestException()
            val mealsIds = call.receive<List<Int>>()
            var price = 0f
            for (mealId in mealsIds) {
                val meal = DatabaseController.getMeal(mealId) ?: throw BadRequestException()
                price += meal.price
            }
            val order = Order(System.currentTimeMillis().toInt(), user.email, mealsIds, price, user.address, "COOK")
            DatabaseController.saveOrder(order)
            call.respond(HttpStatusCode.OK, order)
        }

        post("/order/{orderId}/markReady") {
            val orderId = call.parameters["orderId"]!!.toInt()
            val principal = call.principal<JWTPrincipal>()
            val userType = principal!!.payload.getClaim("userType").asString()
            if (userType != "staff") throw ForbiddenException("Access denied")
            val order = DatabaseController.getOrder(orderId) ?: throw BadRequestException()
            order.status = "COMPLETE"
            DatabaseController.updateOrder(order)
            call.respond(HttpStatusCode.OK, order)
        }

        get("/orderHistory") {
            call.respond(HttpStatusCode.OK, DatabaseController.getAllOrders())
        }
        get("/orderIncomplete") {
            call.respond(HttpStatusCode.OK, DatabaseController.getAllIncompleteOrders())
        }
    }
}