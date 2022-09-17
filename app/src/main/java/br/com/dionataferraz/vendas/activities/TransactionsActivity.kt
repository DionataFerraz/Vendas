package br.com.dionataferraz.vendas.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.dionataferraz.vendas.adapters.TransactionAdapter
import br.com.dionataferraz.vendas.databinding.ActivityTransactionsBinding

class TransactionsActivity : AppCompatActivity(), TransactionAdapter.Listener {

    private lateinit var binding: ActivityTransactionsBinding

    private val adapter: TransactionAdapter by lazy {
        TransactionAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTransactionsBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        binding.rcList.adapter = adapter
        adapter.addList(listOf("Maria", "Gabriel", "Lucas", "Bruna", "João", "Pedro"))


    }

    override fun onItemClick(text: String) {
        Toast.makeText(
            this,
            text,
            Toast.LENGTH_LONG
        ).show()
    }
}