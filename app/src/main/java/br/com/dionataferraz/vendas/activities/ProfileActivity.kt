package br.com.dionataferraz.vendas.activities

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.dionataferraz.vendas.databinding.ActivityProfileBinding
import br.com.dionataferraz.vendas.models.PersonModel
import br.com.dionataferraz.vendas.viewModels.ProfileViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory


class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var viewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileBinding.inflate(layoutInflater)
        viewModel = ProfileViewModel()

        val view = binding.root
        setContentView(view)

        val sharedPreferences = getSharedPreferences(
            "Profile",
            MODE_PRIVATE
        )

        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val adapter = moshi.adapter(PersonModel::class.java)

        binding.pfSaveButton.setOnClickListener {

            val radioGroup: RadioGroup
            radioGroup = binding.radioGroup
            val radioButtonSelected: Int = radioGroup.checkedRadioButtonId
            val radioButton: RadioButton = findViewById(radioButtonSelected)

            val name = binding.tvName.text.toString()
            val age = binding.tvAge.text.toString()
            val email = binding.tvEmail.text.toString()
            val password = binding.tvPassword.text.toString()
            val gender = radioButton.text.toString()

            viewModel.createPerson(name, age, email, password, gender)

            viewModel.personModelLiveData.observe(this) { person ->

                val edit = sharedPreferences.edit()

                val personSave = adapter.toJson(person)
                edit.putString("Person", personSave)
                edit.apply()

                val intent = Intent(this, TestandoProfileActivity::class.java)
                startActivity(intent)
                finish()
            }

        }

        binding.pfClearButton.setOnClickListener {

            clearFields()
            clearSharedPreferences()

            val intent = Intent(this, TestandoProfileActivity::class.java)
            startActivity(intent)
            finish()
        }

        viewModel.shouldShowError.observe(this) { shouldShow ->
            if (shouldShow != null) {
                Toast.makeText(
                    this,
                    shouldShow,
                    Toast.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(
                    this,
                    "Perfil criado com sucesso!",
                    Toast.LENGTH_LONG
                )
            }
        }
    }

    private fun clearFields() {
        binding.tvName.text = null
        binding.tvAge.text = null
        binding.tvEmail.text = null
        binding.tvPassword.text = null
        binding.radioGroup.clearCheck()

    }

    private fun clearSharedPreferences() {
        val sharedPreferences = getSharedPreferences(
            "Profile",
            MODE_PRIVATE
        )

        sharedPreferences.edit().clear().apply()
    }

}