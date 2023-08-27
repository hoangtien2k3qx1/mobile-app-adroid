package com.hoangtien2k3.food_order_app.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.hoangtien2k3.food_order_app.BaseActivity
import com.hoangtien2k3.food_order_app.R
import com.hoangtien2k3.food_order_app.databinding.ActivitySignInBinding


class SignInActivity : BaseActivity() {
//    retrofit ,Gson, mvvm , view model, lifecycle activity và fragment



    private val firebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivitySignInBinding.inflate(layoutInflater)
    }

    private var isValidEmail = false
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z]+\\.+[a-z]{2,}".toRegex()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun initData() {
        checkLogin()
        signUpFrom()
    }

    override fun loadData() {

    }

    private fun checkLogin() {

        // check login email and password
        binding.btnSignInApp.setOnClickListener {

            val email = binding.txtEmail.text.toString().trim()
            val password = binding.txtPassword.text.toString().trim()

            if (!isValidEmail) {
                Toast.makeText(
                    applicationContext,
                    "Email hoặc Password không đúng !!!",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            if (email.isNotEmpty() && password.isNotEmpty()){
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener{
                    if (it.isSuccessful){
                        Toast.makeText(this, "Dang nhap thanh cong", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, IntroActivity::class.java))
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Nhập đầy đủ username và password.", Toast.LENGTH_SHORT).show()
            }

        }

        binding.apply {
            txtEmail.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    validate.text = ""
                    isValidEmail = emailPattern.matches(binding.txtEmail.text.toString().trim()) && s?.length ?: 0 > 0
                    if (!isValidEmail) {
                        validate.setTextColor(Color.rgb(255, 0, 0))
                        validate.text = resources.getString(R.string.email_failed)
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                }
            })
        }

    }

    // Quay lại trang đăng ký tài khoản
    private fun signUpFrom() {
        binding.textSignUpApp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

}
