package br.com.dionataferraz.vendas.data.model

import br.com.dionataferraz.vendas.domain.OperationTypeEnum

data class Transaction(
    val operationValue: Double,
    val operationTypeEnum: OperationTypeEnum,
    var accountBalance: Double,
    val date: String
)