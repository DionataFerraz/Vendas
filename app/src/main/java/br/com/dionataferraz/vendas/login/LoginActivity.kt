package br.com.dionataferraz.vendas.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.dionataferraz.vendas.activities.ProfileActivity
import br.com.dionataferraz.vendas.databinding.ActivityLoginBinding
import br.com.dionataferraz.vendas.viewModels.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        viewModel = LoginViewModel()

        val view = binding.root
        setContentView(view)

        binding.btLogin.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        viewModel.shouldShowError.observe(this) { shouldShow ->
            if (shouldShow) {
                Toast.makeText(
                    this,
                    "Deu ruim",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(
                    this,
                    "Login realizado",
                    Toast.LENGTH_LONG
                )
            }
        }
    }
}