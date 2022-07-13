package hotkitchen.database

import hotkitchen.data.Credentials
import hotkitchen.data.User
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

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

}