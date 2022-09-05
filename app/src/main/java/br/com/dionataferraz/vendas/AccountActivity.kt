package br.com.dionataferraz.vendas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.dionataferraz.vendas.databinding.ActivityAccountBinding

class AccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccountBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_account)

        val view = binding.root
        setContentView(view)

        binding.btSave.setOnClickListener {
            val intent = Intent(this, TransactionsActivity::class.java)
            startActivity(intent)
        }
        Toast.makeText(
            this,
            "Cadastro concluido com sucesso",
            Toast.LENGTH_LONG
        ).show()
    }

}