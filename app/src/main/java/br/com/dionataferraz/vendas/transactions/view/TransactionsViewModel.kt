package br.com.dionataferraz.vendas.transactions.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.dionataferraz.vendas.data.model.Transaction
import br.com.dionataferraz.vendas.data.usecase.GetTransactionCase
import kotlinx.coroutines.launch

class TransactionsViewModel : ViewModel() {

    private val case: GetTransactionCase by lazy {
        GetTransactionCase()
    }

    private val transactions: MutableLiveData<List<Transaction>> = MutableLiveData()
    val listTransactions: LiveData<List<Transaction>> = transactions

    fun getTransactions() {
        viewModelScope.launch {
            val list = case.getTransactions()
            transactions.value = list
        }
    }

}