package br.com.dionataferraz.vendas.data.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import br.com.dionataferraz.vendas.data.entity.TransactionEntity
import br.com.dionataferraz.vendas.data.entity.UserEntity
import br.com.dionataferraz.vendas.data.model.Transaction
import br.com.dionataferraz.vendas.data.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VendasDatasource {

    private val database: VendasDatabase by lazy {
        VendasDatabase.getInstance()
    }

    suspend fun createTransaction(transaction: Transaction) {
        withContext(Dispatchers.IO) {
            database.dao().createTransaction(transaction.toEntity())
        }
    }

    suspend fun getTransactions(): List<Transaction> {
        return withContext(Dispatchers.IO) {
            database.dao().getAllTransactions().map {
                it.toModel()
            }
        }
    }

    suspend fun createUser(user: User) {
        withContext(Dispatchers.IO) {
            database.userDao().insertUser(user.toEntity())
        }
    }

    suspend fun getUser(): User {
        val result = withContext(Dispatchers.IO) {
            database.userDao().getUser()
        }

        return result.toModel()
    }

    suspend fun getAccountBalance(): Double {
        return withContext(Dispatchers.IO) {
            database.dao().getAccountBalance()
        }
    }

    private fun UserEntity.toModel(): User {
        return User(
            name = name,
            accountBalance = accountBalance,
            accountManager = accountManager
        )
    }

    private fun User.toEntity(): UserEntity {
        return UserEntity(
            name = name,
            accountBalance = accountBalance,
            accountManager = accountManager
        )
    }

    private fun TransactionEntity.toModel(): Transaction {
        return Transaction(
            operationValue = operationValue,
            operationTypeEnum = operationTypeEnum,
            accountBalance = accountBalance,
            date = date
        )
    }

    private fun Transaction.toEntity(): TransactionEntity {
        return TransactionEntity(
            operationValue = operationValue,
            operationTypeEnum = operationTypeEnum,
            accountBalance = accountBalance,
            date = date
        )
    }
}