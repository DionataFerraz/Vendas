package br.com.dionataferraz.vendas.transactions

import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import br.com.dionataferraz.vendas.account.view.AccountViewModel
import br.com.dionataferraz.vendas.domain.OperationTypeEnum
import br.com.dionataferraz.vendas.databinding.ActivityCreateTransactionBinding
import br.com.dionataferraz.vendas.data.model.Transaction
import br.com.dionataferraz.vendas.transactions.view.CreateTransactionViewModel
import java.time.Instant

fun <T> getEditable(value: T): Editable? {
    return Editable.Factory.getInstance().newEditable(value.toString())
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

class CreateTransactionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateTransactionBinding
    private lateinit var viewModel: CreateTransactionViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateTransactionBinding.inflate(layoutInflater)
        viewModel = CreateTransactionViewModel()
        val view = binding.root
        setContentView(view)

        viewModel.shouldShowError.observe(this) {
        }

        binding.btDepositar.setOnClickListener {
            createDepositTransaction()
            hideKeyboard()
        }

        binding.btSacar.setOnClickListener {
            createWithdrawTransaction()
            hideKeyboard()
        }

        binding.tvVoltar.setOnClickListener {
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.getBalance()
        setBindingValues(viewModel.showBalance.value)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createDepositTransaction() {
        val value = getBindingValues()

        viewModel.createTransaction(
            Transaction(
                operationValue = value,
                operationTypeEnum = OperationTypeEnum.DEPOSITAR,
                accountBalance = viewModel.showBalance.value!!,
                date = Instant.now().toString()
            )
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createWithdrawTransaction() {
        val value = getBindingValues()

        viewModel.createTransaction(
            Transaction(
                operationValue = value,
                operationTypeEnum = OperationTypeEnum.SACAR,
                accountBalance = viewModel.showBalance.value!!,
                date = Instant.now().toString()
            )
        )
    }

    private fun getBindingValues(): Double {
        return binding.etOperacao.text.toString().trim().toDouble()
    }

    private fun setBindingValues(value: Double?) {
        binding.tvAccountBalance.text = getEditable(value)
    }

}


