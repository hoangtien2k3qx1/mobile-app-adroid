package com.example.my_app

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var txtEmail: EditText
    private lateinit var txtPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var txtValidate: TextView
    // validation
    private var isValidEmail = false
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z]+\\.+[a-z]{2,}".toRegex()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtEmail = findViewById(R.id.txtEmail)
        txtPassword = findViewById(R.id.txtPassword)
        btnLogin = findViewById(R.id.btnLogin)
        txtValidate = findViewById(R.id.txtValidate)

        btnLogin.setOnClickListener {
            if (!isValidEmail) {
                Toast.makeText(applicationContext, "Validate email failed", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val intent = Intent(this, activity2::class.java)
            intent.putExtra("email", txtEmail.text.toString().trim())
            startActivity(intent)
        }

        // validation email
        txtEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // validate here
                txtValidate.text = ""
                val email = txtEmail.text.toString().trim()
                isValidEmail = emailPattern.matches(email) && s?.length ?: 0 > 0
                if (!isValidEmail) {
                    txtValidate.setTextColor(Color.rgb(255, 0, 0))
                    txtValidate.text = "Invalid email address"
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }
}