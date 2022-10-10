package br.com.dionataferraz.vendas.data.repository

import androidx.lifecycle.LiveData
import br.com.dionataferraz.vendas.data.local.VendasDatasource
import br.com.dionataferraz.vendas.data.model.Transaction

class TransactionRepository {

    private val datasource: VendasDatasource by lazy {
        VendasDatasource()
    }

    suspend fun createTransaction(transaction: Transaction) {
        datasource.createTransaction(transaction)
    }

    suspend fun getTransactions(): List<Transaction> {
        return datasource.getTransactions()
    }

    suspend fun getAccountBalance(): Double {
        return datasource.getAccountBalance()
    }
}