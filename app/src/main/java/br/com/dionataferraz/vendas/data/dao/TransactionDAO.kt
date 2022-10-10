package br.com.dionataferraz.vendas.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.dionataferraz.vendas.data.entity.TransactionEntity

@Dao
interface TransactionDAO {

    @Insert
    fun createTransaction(transactionEntity: TransactionEntity)

    @Query("SELECT * FROM transactionEntity t ORDER BY t.date DESC")
    fun getAllTransactions(): List<TransactionEntity>

    @Query("SELECT t.accountBalance FROM transactionEntity t")
    fun getAccountBalance(): Double
}