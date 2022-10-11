package br.com.dionataferraz.vendas.profile

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.dionataferraz.vendas.databinding.ActivityLoginBinding
import br.com.dionataferraz.vendas.databinding.ActivityProfileBinding
import br.com.dionataferraz.vendas.home.HomeActivity
import br.com.dionataferraz.vendas.login.LoginActivity
import br.com.dionataferraz.vendas.model.AddUserModel
import br.com.dionataferraz.vendas.model.GetUserModel
import br.com.dionataferraz.vendas.profile.view.ProfileActivityViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var viewModel: ProfileActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        viewModel = ProfileActivityViewModel()
        setContentView(binding.root)


        binding.btSave.setOnClickListener {
            val values = getBindingFieldsValue()

            viewModel.createUser(
                AddUserModel(
                    name = values.name,
                    email = values.email,
                    password = values.password
                )
            )
        }

        viewModel.shouldShowLogin.observe(this) {
            if (it) {
                val intent: Intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun getBindingFieldsValue(): GetUserModel {
        return GetUserModel(
            name = binding.etName.text.toString(),
            email = binding.etEmail.text.toString(),
            password = binding.etPassword.text.toString()
        )
    }
}