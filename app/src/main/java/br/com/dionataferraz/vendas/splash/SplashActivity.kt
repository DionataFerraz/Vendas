package br.com.dionataferraz.vendas.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.dionataferraz.vendas.home.HomeActivity
import br.com.dionataferraz.vendas.databinding.ActivitySplashBinding
import br.com.dionataferraz.vendas.profile.ProfileActivity
import br.com.dionataferraz.vendas.splash.view.SplashViewModel

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = SplashViewModel()
        viewModel.getUser()

        viewModel.shouldShowHome.observe(this) {
            if (it) {
                val intent: Intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
        }

        viewModel.shouldShowError.observe(this) {
            if (it) {
                val intent: Intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
            }
        }
    }
}