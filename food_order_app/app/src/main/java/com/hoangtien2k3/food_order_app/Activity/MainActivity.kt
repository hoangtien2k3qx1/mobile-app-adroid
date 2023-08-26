package com.hoangtien2k3.food_order_app.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hoangtien2k3.food_order_app.Adapter.CategoryAdapter
import com.hoangtien2k3.food_order_app.Adapter.PopularAdapter
import com.hoangtien2k3.food_order_app.Domain.CategoryDomain
import com.hoangtien2k3.food_order_app.Domain.FoodDomain
import com.hoangtien2k3.food_order_app.R

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: RecyclerView.Adapter<*>
    private lateinit var adapter2: RecyclerView.Adapter<*>
    private lateinit var recyclerViewCategoryList: RecyclerView
    private lateinit var recyclerViewPopularList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerViewCategory();
        recyclerViewPopular()
    }

    private fun recyclerViewCategory() {
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewCategoryList = findViewById(R.id.recyclerView1)
        recyclerViewCategoryList.layoutManager = linearLayoutManager

        var category: ArrayList<CategoryDomain> = ArrayList<CategoryDomain>()
        category.add(CategoryDomain("Pizza", "cat_1"))
        category.add(CategoryDomain("Bánh", "cat_2"))
        category.add(CategoryDomain("Trái", "cat_3"))
        category.add(CategoryDomain("Qủa", "cat_4"))
        category.add(CategoryDomain("Mỳ", "cat_5"))

        adapter = CategoryAdapter(category)
        recyclerViewCategoryList.adapter = adapter
    }

    private fun recyclerViewPopular() {
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewPopularList = findViewById(R.id.recyclerView2)
        recyclerViewPopularList.layoutManager = linearLayoutManager

        var foodList: ArrayList<FoodDomain>  = ArrayList<FoodDomain>()
        foodList.add(FoodDomain("Bánh Pizza", "pizza1", "bánh pizza là loại bánh cực kỳ thơm ngon và hấp dẫn.", 975.0));
        foodList.add(FoodDomain("Ngũ cốc", "pizza1", "bánh pizza là loại bánh cực kỳ thơm ngon và hấp dẫn.", 15.0));
        foodList.add(FoodDomain("Nước uống", "cat_2", "bánh pizza là loại bánh cực kỳ thơm ngon và hấp dẫn.", 46.0));
        foodList.add(FoodDomain("Trái cây", "pop_1", "bánh pizza là loại bánh cực kỳ thơm ngon và hấp dẫn.", 27.0));
        foodList.add(FoodDomain("CoCa-Cola", "pop_2", "bánh pizza là loại bánh cực kỳ thơm ngon và hấp dẫn.", 75.0));

        adapter2 = PopularAdapter(foodList)
        recyclerViewPopularList.adapter = adapter2

    }

}