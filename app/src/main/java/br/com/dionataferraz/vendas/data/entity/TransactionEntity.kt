package br.com.dionataferraz.vendas.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.dionataferraz.vendas.domain.OperationTypeEnum

@Entity(tableName = "transactionEntity")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0,
    val operationValue: Double,
    val operationTypeEnum: OperationTypeEnum,
    val accountBalance:Double,
    val date: String
)