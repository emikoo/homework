package com.example.sharedpreferencehw

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val profLogin = findViewById<EditText>(R.id.profLogin)
        val profPassword = findViewById<EditText>(R.id.profPassword)
        val btnUpdate = findViewById<Button>(R.id.btnUpdate)
        val btnBack = findViewById<Button>(R.id.btnBack)

        val preference = getSharedPreferences("SharedPreferenceHW", Context.MODE_PRIVATE)

        val loginFromPref = preference.getString("login", "no saved data")
        profLogin.setText(loginFromPref)
        val passwordFromPref = preference.getString("password", "no saved data")
        profPassword.setText(passwordFromPref)

        btnUpdate.setOnClickListener {
            preference.edit().putString("login", profLogin.text.toString()).apply()
            preference.edit().putString("password", profPassword.text.toString()).apply()

            Toast.makeText(this,"Данные обновлены", Toast.LENGTH_LONG).show()
        }

        btnBack.setOnClickListener {
            preference.edit().putString("login", "").apply()
            preference.edit().putString("password", "").apply()

            intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


    }
}