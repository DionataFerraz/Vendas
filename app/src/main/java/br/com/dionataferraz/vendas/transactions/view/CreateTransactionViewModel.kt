package br.com.dionataferraz.vendas.transactions.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.dionataferraz.vendas.data.model.Transaction
import br.com.dionataferraz.vendas.data.model.User
import br.com.dionataferraz.vendas.data.usecase.GetTransactionCase
import br.com.dionataferraz.vendas.data.usecase.GetUsersCase
import br.com.dionataferraz.vendas.domain.OperationTypeEnum
import kotlinx.coroutines.launch

class CreateTransactionViewModel : ViewModel() {

    private val case: GetTransactionCase by lazy {
        GetTransactionCase()
    }

    private val balance: MutableLiveData<Double> = MutableLiveData()
    val showBalance: LiveData<Double> = balance

    private val error: MutableLiveData<Boolean> = MutableLiveData(false)
    val shouldShowError: LiveData<Boolean> = error

    private val success: MutableLiveData<Boolean> = MutableLiveData(false)
    val shouldShowTransactions: LiveData<Boolean> = success

    fun verifyBalance(transaction: Transaction) {
        viewModelScope.launch {
            if (transaction.operationValue <= 0) {
                error.value = true
            } else {
                case.createTransaction(transaction)
                success.value = true
            }
        }
    }

    fun getBalance() {
        viewModelScope.launch {
            balance.value = case.getAccountBalance()
        }
    }

    fun createTransaction(transaction: Transaction) {
        viewModelScope.launch {
            if (transaction.operationTypeEnum == OperationTypeEnum.SACAR) {
                verifyBalance(transaction)
            } else {
                transaction.accountBalance += transaction.operationValue
                case.createTransaction(transaction)
            }
        }
    }
}
