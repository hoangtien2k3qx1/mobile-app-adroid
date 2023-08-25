package com.hoangtien2k3.food_order_app.Activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.hoangtien2k3.food_order_app.Domain.FoodDomain
import com.hoangtien2k3.food_order_app.R

class ShowDetailActivity : AppCompatActivity() {

    private lateinit var addToCartBtn: TextView
    private lateinit var titleTxt: TextView
    private lateinit var feeTxt: TextView
    private lateinit var description: TextView
    private lateinit var numberOrderTxt: TextView
    private lateinit var plusBtn: ImageView
    private lateinit var minusBtn: ImageView
    private lateinit var picFood: ImageView
    private lateinit var object1: FoodDomain
    private var numberOrder = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_detail)

        initView()
        getBundle()
    }

    private fun getBundle() {
        object1 = intent.getSerializableExtra("object1") as FoodDomain

        val drawableResourceId: Int = this.resources.getIdentifier(object1.pic, "drawable", this.packageName)

        Glide.with(this)
            .load(drawableResourceId)
            .into(picFood)

        titleTxt.text = object1.title
        feeTxt.text = object1.fee as String
        description.text = object1.description
        numberOrderTxt.text = numberOrder as String

        // mỗi lần ấn button để tăng số lượng sản pẩm
        plusBtn.setOnClickListener {
            numberOrder += 1 // số lượng sản phẩm tăng lên 1
            numberOrderTxt.text = numberOrder as String
        }


        // giảm số lượng sản phẩm
        minusBtn.setOnClickListener {
            // Các 1: numberOrder = maxOf(1, numberOrder - 1)
            // Cách 2: if
            if (numberOrder > 1) {
                numberOrder -= 1 // số lượng sản phẩm tăng lên 1
            }
            numberOrderTxt.text = numberOrder as String
        }

    }

    private fun initView() {
        addToCartBtn = findViewById(R.id.addBtn)
        titleTxt = findViewById(R.id.title)
        feeTxt = findViewById(R.id.fee)
        description = findViewById(R.id.description)
        numberOrderTxt = findViewById(R.id.numberOrderTxt)
        plusBtn = findViewById(R.id.plusBtn)
        minusBtn = findViewById(R.id.minusBtn)
        picFood = findViewById(R.id.picFood)
    }
}