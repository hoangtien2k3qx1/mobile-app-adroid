package com.hoangtien2k3.food_order_app.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.hoangtien2k3.food_order_app.R

class IntroActivity : AppCompatActivity() {

    private lateinit var startBtn: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        startBtn = findViewById(R.id.startBtn)
        startBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }


    }
}


