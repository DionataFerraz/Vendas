package br.com.dionataferraz.vendas.transactions

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.dionataferraz.vendas.databinding.ActivityTransactionsBinding
import br.com.dionataferraz.vendas.transactions.adapter.TransactionAdapter
import br.com.dionataferraz.vendas.transactions.view.TransactionsViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*


enum class TransactionType { MARKET, GAS_STATION, BAR, SERVICES_ON_DEMAND }

data class TransactionItem(
    val title: String, val time: Date, val price: Double, val type: TransactionType
)

class TransactionsActivity : AppCompatActivity(), TransactionAdapter.Listener {

    private lateinit var binding: ActivityTransactionsBinding
    private lateinit var viewModel: TransactionsViewModel
    private val adapter: TransactionAdapter by lazy {
        TransactionAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = TransactionsViewModel()

        viewModel.getTransactions()
        viewModel.listTransactions.observe(this) {
            binding.rcList.adapter = adapter
            if (it != null) {
                adapter.addList(it)
            }
        }
    }

    override fun onItemClick(text: String) {
        Toast.makeText(
            this, text, Toast.LENGTH_LONG
        ).show()
    }
}