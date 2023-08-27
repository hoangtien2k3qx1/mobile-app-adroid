package com.hoangtien2k3.food_order_app.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.hoangtien2k3.food_order_app.Domain.FoodDomain
import com.hoangtien2k3.food_order_app.Helper.ManagementCart
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
    private lateinit var managementCart: ManagementCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_detail)

        managementCart = ManagementCart(this)

        initView()
        getBundle()
    }

    private fun getBundle() {
        object1 = intent.getSerializableExtra("object1") as FoodDomain

        val drawableResourceId: Int =
            resources.getIdentifier(object1.pic, "drawable", packageName)

        Glide.with(this)
            .load(drawableResourceId)
            .into(picFood)

        titleTxt.text = object1.title
        feeTxt.text = String.format("%.3f", object1.fee).replace(",", ".")
        description.text = object1.description
        numberOrderTxt.text = numberOrder.toString()

        plusBtn.setOnClickListener {
            numberOrder += 1
            numberOrderTxt.text = numberOrder.toString()
        }

        minusBtn.setOnClickListener {
            if (numberOrder > 1) {
                numberOrder -= 1
                numberOrderTxt.text = numberOrder.toString()
            }
        }

        addToCartBtn.setOnClickListener {
            object1.numberInCart = numberOrder
            managementCart.insertFood(object1)
        }
    }

    private fun initView() {
        addToCartBtn = findViewById(R.id.addToCartBtn)
        titleTxt = findViewById(R.id.titleTxt)
        feeTxt = findViewById(R.id.priceTxt)
        description = findViewById(R.id.descriptionTxt)
        numberOrderTxt = findViewById(R.id.numberOrderTxt)
        plusBtn = findViewById(R.id.plusBtn)
        minusBtn = findViewById(R.id.minusBtn)
        picFood = findViewById(R.id.picFood)
    }
}