package br.com.dionataferraz.vendas.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import br.com.dionataferraz.vendas.R
import br.com.dionataferraz.vendas.databinding.ActivityTestandoProfileBinding
import br.com.dionataferraz.vendas.models.PersonModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class TestandoProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTestandoProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_testando_profile)

        val sharedPreferences = getSharedPreferences(
            "Profile",
            MODE_PRIVATE
        )

        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val adapter = moshi.adapter(PersonModel::class.java)

        findViewById<TextView>(R.id.teste).apply {
            val personFromSharedPreferences = sharedPreferences.getString("Person", null)
            val personFromAdapter = adapter.fromJson(
                personFromSharedPreferences

            )

            if (personFromAdapter != null) {
                text =
                    "${personFromAdapter.name} ${personFromAdapter.age} ${personFromAdapter.email} ${personFromAdapter.password} ${personFromAdapter.gender}"

            }

        }
    }
}
