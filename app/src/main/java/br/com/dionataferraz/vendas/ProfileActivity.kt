package br.com.dionataferraz.vendas

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.util.*

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val sharedPreferences = getSharedPreferences(
            "Profile",
            MODE_PRIVATE
        )
        val edit = sharedPreferences.edit()
        val person:Person? = null

        val moshi = Moshi
            .Builder()
            .addLast(KotlinJsonAdapterFactory())
            .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
            .build()

        val adapter = moshi.adapter(Person::class.java)
        val personString = adapter.toJson(person)
        edit.putString("person", personString)
        edit.apply()

        findViewById<TextView>(R.id.tv_name).apply {
            val personFromSharedPreferences = sharedPreferences.getString("person", null)
            val personFromAdapter = adapter.fromJson(
                personFromSharedPreferences
            )

            personFromAdapter?.also {
                text = it.name
            }
            if (personFromAdapter != null) {
                text = "${personFromAdapter.name} ${personFromAdapter.age} ${personFromAdapter.date.toString()}"
            }
        }

    }

    data class Person(
        val name: String,
        val age: Int,
        val date: Date
    )

    private fun saveAndFetchPreferences() {
        val sharedPreferences = getSharedPreferences(
            "Profile",
            MODE_PRIVATE
        )
        val edit = sharedPreferences.edit()

        edit.putString("name", "Dionata")
        edit.putInt("age", 28)
        edit.apply()

        findViewById<TextView>(R.id.tv_name).apply {
            val name = sharedPreferences.getString("name", null)
            val age = sharedPreferences.getInt("age", 0)
            if (!name.isNullOrEmpty() && age > 0) {
                text = "$name $age"
            }
        }
    }
}