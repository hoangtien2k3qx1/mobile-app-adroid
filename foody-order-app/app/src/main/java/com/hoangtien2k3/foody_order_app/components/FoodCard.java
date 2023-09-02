package com.hoangtien2k3.foody_order_app.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hoangtien2k3.foody_order_app.R;
import com.hoangtien2k3.foody_order_app.repositoryInit.DatabaseHandler;
import com.hoangtien2k3.foody_order_app.model.Food;


public class FoodCard extends LinearLayout {
    private Food food;
    private Double defaultPrice;
    private String restaurantName;

    public FoodCard(Context context, Food food, Double defaultPrice, String restaurantName){
        super(context);
        this.food = food;
        this.defaultPrice = defaultPrice;
        this.restaurantName = restaurantName;
        initControl(context);
    }

    public FoodCard(Context context) {
        super(context);
        initControl(context);
    }

    private void initControl(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_food_card, this);

        ImageView image = findViewById(R.id.imageFood);
        TextView tvName = findViewById(R.id.tvNameFood);
        TextView tvPrice = findViewById(R.id.tvPriceFood);
        TextView tvRestaurantName = findViewById(R.id.tvFoodRestaurantName);

        // Set information for food cart
        image.setImageBitmap(DatabaseHandler.convertByteArrayToBitmap(food.getImage()));
        tvName.setText(food.getName());
        tvPrice.setText(String.format("%s VNĐ", Math.round(defaultPrice)));
        tvRestaurantName.setText(restaurantName);
    }
}