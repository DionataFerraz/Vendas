package br.com.dionataferraz.vendas.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.dionataferraz.vendas.databinding.ActivityHomeBinding
import br.com.dionataferraz.vendas.databinding.ActivityProfileBinding
import br.com.dionataferraz.vendas.home.view.HomeActivityViewModel
import br.com.dionataferraz.vendas.model.GetUserModel
import br.com.dionataferraz.vendas.profile.view.ProfileActivityViewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: HomeActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        viewModel = HomeActivityViewModel()
        setContentView(binding.root)

        val result = viewModel.getUser

        viewModel.getUser.observe(this) {
            setBindingFieldsValue(result.value?.name ?: "")
        }
    }

    private fun setBindingFieldsValue(user: String) {
        binding.tvAccountName.text = user
    }
}