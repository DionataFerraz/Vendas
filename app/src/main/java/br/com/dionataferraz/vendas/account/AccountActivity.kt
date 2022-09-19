package br.com.dionataferraz.vendas.account

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.dionataferraz.vendas.databinding.ActivityAccountBinding

fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)

class AccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityAccountBinding.inflate(layoutInflater).run {
            binding = this
            setContentView(root)
        }
        loadAccountData()
        binding.btClear.setOnClickListener { clearData() }
        binding.btSave.setOnClickListener {
            saveData()
            finish()
        }
    }

    private fun saveData() {
        val accountName = binding.etAccountName.text.toString()
        val accountManager = binding.etAccountM.text.toString()
        val accountBalance = binding.etAccountBalance.text.toString()
        val creditType = binding.rdAccountTypeCredit.isChecked
        val debitType = binding.rdAccountTypeDebit.isChecked

        val sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply {
            putString("accountName", accountName)
            putBoolean("creditType", creditType)
            putBoolean("debitType", debitType)
            putString("accountManager", accountManager)
            putString("accountBalance", accountBalance)
        }.apply()
        Toast.makeText(this, "Account settings saved successfully", Toast.LENGTH_SHORT).show()
    }

    private fun clearData() {
        val sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.apply {
            putString("accountName", "")
            putString("accountManager", "")
            putString("accountBalance", "")
            putBoolean("creditType", true)
            putBoolean("debitType", false)
        }.clear().apply()
        loadAccountData()
        Toast.makeText(this, "Account settings cleared successfully", Toast.LENGTH_SHORT).show()
    }

    private fun loadAccountData() {
        val sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE)
        val accountNameSaved = sharedPreferences.getString("accountName", null)
        val accountManagerSaved = sharedPreferences.getString("accountManager", null)
        val accountBalanceSaved = sharedPreferences.getString("accountBalance", null)
        val creditTypeSaved = sharedPreferences.getBoolean("creditType", true)
        val debitTypeSaved = sharedPreferences.getBoolean("debitType", false)

        binding.etAccountName.text = accountNameSaved.toString().toEditable()
        binding.etAccountM.text = accountManagerSaved.toString().toEditable()
        binding.etAccountBalance.text = accountBalanceSaved.toString().toEditable()
        binding.rdAccountTypeCredit.isChecked = creditTypeSaved
        binding.rdAccountTypeDebit.isChecked = debitTypeSaved
    }
}