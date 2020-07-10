package com.example.sharedpreferencehw

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etLogin = findViewById<EditText>(R.id.etLogin)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnSignUp = findViewById<Button>(R.id.btnSignUp)

        val preference = getSharedPreferences("SharedPreferenceHW", Context.MODE_PRIVATE)

        btnLogin.setOnClickListener {
            val login = etLogin.text.toString()
            val password = etPassword.text.toString()

            val loginFromPref = preference.getString("login", "no saved data")
            val passwordFromPref = preference.getString("password", "no saved data")

            if (loginFromPref == login && passwordFromPref == password) {
                val intent = Intent(applicationContext, ProfileActivity::class.java)

                intent.putExtra("login", login)
                intent.putExtra("password", password)

                startActivityForResult(intent, 1)
            } else {
                Toast.makeText(this, "Логин или пароль не верны", Toast.LENGTH_LONG).show()
            }
        }

        btnSignUp.setOnClickListener {
            val login = etLogin.text.toString()
            val password = etPassword.text.toString()

            preference.edit().putString("login", login).apply()
            preference.edit().putString("password", password).apply()

            Toast.makeText(this, "изменения сохранены", Toast.LENGTH_LONG).show()
        }
    }
}