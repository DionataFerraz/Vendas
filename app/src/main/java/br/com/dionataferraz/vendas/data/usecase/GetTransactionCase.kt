package br.com.dionataferraz.vendas.data.usecase

import androidx.lifecycle.LiveData
import br.com.dionataferraz.vendas.data.model.Transaction
import br.com.dionataferraz.vendas.data.repository.TransactionRepository

class GetTransactionCase {

    private val repository: TransactionRepository by lazy {
        TransactionRepository()
    }

    suspend fun createTransaction(transaction: Transaction) {
        repository.createTransaction(transaction)
    }

    suspend fun getTransactions(): List<Transaction> {
       return repository.getTransactions()
    }

    suspend fun getAccountBalance(): Double {
        return repository.getAccountBalance()
    }
}