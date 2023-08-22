package com.example.my_app

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class activity2 : AppCompatActivity() {
    private lateinit var textView: TextView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)
//        val extras: Bundle? = intent.extras
//        if (extras != null) {
//            val email: String? = extras.getString("email")
//            textView.text = "Your email: " + email
//        }
    }
}