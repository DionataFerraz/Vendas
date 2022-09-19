package br.com.dionataferraz.vendas

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.dionataferraz.vendas.databinding.ActivityTransactionsBinding
import br.com.dionataferraz.vendas.transactions.TransactionAdapter
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
    private val adapter: TransactionAdapter by lazy {
        TransactionAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListOfTransactions()
        displayTransactionsList()
    }

    override fun onItemClick(text: String) {
        Toast.makeText(
            this, text, Toast.LENGTH_LONG
        ).show()
    }

    private fun displayTransactionsList() {
        binding.rcList.adapter = adapter
        adapter.addList(
            getList("transactionsItems")
        )
    }

    private fun setListOfTransactions() {
        setList(
            "transactionsItems", listOf(
                TransactionItem(
                    "POSTO INCA PUC",
                    Calendar.getInstance().time,
                    11.57,
                    TransactionType.GAS_STATION
                ),
                TransactionItem("CLOVER COFFE", Date(2022, 9, 1, 10, 2), 8.32, TransactionType.BAR),
                TransactionItem("ZAFFARI", Date(2022, 9, 4, 21, 10), 11.57, TransactionType.MARKET),
                TransactionItem("F1.COM", Date(2022, 9, 4, 11, 5), 28.98, TransactionType.SERVICES_ON_DEMAND)
            )
        )
    }

    private fun <T> setList(key: String, list: List<T>?) {
        val gson = Gson()
        val json: String = gson.toJson(list)
        set(key, json)
    }

    private fun set(key: String?, value: String) {
        val sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply { editor.putString(key, value) }.apply()
    }

    private fun getList(key: String): List<TransactionItem> {
        val sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE)

        val arrayItems: List<TransactionItem>
        val serializedObject: String? = sharedPreferences.getString(key, null)
        val gson = Gson()
        val type: Type = object : TypeToken<List<TransactionItem?>?>() {}.type
        arrayItems = gson.fromJson(serializedObject, type)

        return arrayItems
    }
}