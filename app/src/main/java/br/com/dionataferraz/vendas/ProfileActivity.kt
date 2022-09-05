package br.com.dionataferraz.vendas

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val sharedPreferences = getSharedPreferences(
            "Profile", MODE_PRIVATE
        )

        val edit = sharedPreferences.edit()

        val person = Person(
            name = "Charlie brown",
            age = 32
        )

        val moshi = Moshi
            .Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()

        val adapter = moshi.adapter(Person::class.java)
        val personString = adapter.toJson(person)
        edit.putString("person", personString)
        edit.apply()

        sharedPreferences.getString("name", null)
        sharedPreferences.getInt("age", 0)

        findViewById<TextView>(R.id.ti_name).apply{
            val personFromSharedPreferences = sharedPreferences.getString("person", null)
            val personFromAdapter = adapter.fromJson(personFromSharedPreferences)

            if(personFromAdapter != null){
                text = "${personFromAdapter.name} ${personFromAdapter.age}"
            }
        }
    }

    data class Person(
        val name: String,
        val age: Int
    )
}