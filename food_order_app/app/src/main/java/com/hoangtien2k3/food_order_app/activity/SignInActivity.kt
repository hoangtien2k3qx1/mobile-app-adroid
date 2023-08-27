package com.hoangtien2k3.food_order_app.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.hoangtien2k3.food_order_app.R
import com.hoangtien2k3.food_order_app.databinding.ActivitySignInBinding


class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth

    private var isValidEmail = false
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z]+\\.+[a-z]{2,}".toRegex()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance()
        FirebaseApp.initializeApp(this);

        checkLogin()
        signup_form()
    }

    public fun checkLogin() {

        // check login email and password
        binding.btnSignInApp.setOnClickListener {

            val email = binding.txtEmail.text.toString().trim()
            val password = binding.txtPassword.text.toString().trim()

            if (!isValidEmail) {
                Toast.makeText(applicationContext, "Email hoặc Password không đúng !!!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (email.isNotEmpty() && password.isNotEmpty()){
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener{
                    if (it.isSuccessful){
                        val intent = Intent(this, IntroActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Nhập đầy đủ username và password.", Toast.LENGTH_SHORT).show()
            }

        }

        // validation email
        binding.txtEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.validate.text = ""
                isValidEmail = emailPattern.matches(binding.txtEmail.text.toString().trim()) && s?.length ?: 0 > 0
                if (!isValidEmail) {
                    binding.validate.setTextColor(Color.rgb(255, 0, 0))
                    binding.validate.text = "Email không đúng định dạng."
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

    }

    // Quay lại trang đăng ký tài khoản
    public fun signup_form() {
        binding.textSignUpApp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

}
