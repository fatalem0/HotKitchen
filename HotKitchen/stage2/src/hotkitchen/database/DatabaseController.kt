package hotkitchen.database

import hotkitchen.data.Credentials
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
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
}