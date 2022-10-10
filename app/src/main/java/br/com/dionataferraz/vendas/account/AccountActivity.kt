package br.com.dionataferraz.vendas.account

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import androidx.lifecycle.LiveData
import br.com.dionataferraz.vendas.account.view.AccountViewModel
import br.com.dionataferraz.vendas.data.model.User
import br.com.dionataferraz.vendas.databinding.ActivityAccountBinding
import br.com.dionataferraz.vendas.transactions.getEditable

class AccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccountBinding
    private lateinit var viewModel: AccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate(layoutInflater)
        viewModel = AccountViewModel()
        val view = binding.root
        setContentView(view)
        setValues(viewModel.getUser())

        binding.btSave.setOnClickListener {
            viewModel.createUser(getBindingValues())
        }
    }

    private fun setValues(user: User) {
        binding.etAccountName.text = getEditable(user)
        binding.etAccountBalance.text = getEditable(user)
        binding.etAccountM.text = getEditable(user)
    }

    private fun getBindingValues(): User {
        return User(
            name = binding.etAccountName.text.toString(),
            accountBalance = binding.etAccountBalance.text.toString().toDouble(),
            accountManager = binding.etAccountM.text.toString()
        )
    }
}