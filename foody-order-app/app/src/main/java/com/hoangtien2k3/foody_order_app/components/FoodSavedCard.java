package com.hoangtien2k3.foody_order_app.components;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hoangtien2k3.foody_order_app.activity.ActivityImpl.HomeActivity;
import com.hoangtien2k3.foody_order_app.R;
import com.hoangtien2k3.foody_order_app.repositoryInit.DatabaseHandler;
import com.hoangtien2k3.foody_order_app.fragments.SavedFragment;
import com.hoangtien2k3.foody_order_app.model.Food;
import com.hoangtien2k3.foody_order_app.model.FoodSize;

@SuppressLint("ViewConstructor")
public class FoodSavedCard extends LinearLayout {
    private final Food food;
    private final String restaurantName;
    private final FoodSize foodSize;

    public FoodSavedCard(Context context, Food food, String restaurantName, FoodSize foodSize) {
        super(context);
        this.food = food;
        this.restaurantName = restaurantName;
        this.foodSize = foodSize;
        initControl(context);
    }

    @SuppressLint("SetTextI18n")
    private void initControl(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_food_saved_card, this);

        ImageView image = findViewById(R.id.imageSavedFood);
        TextView tvName = findViewById(R.id.tvFoodNameSaved);
        TextView tvSize = findViewById(R.id.tvFoodSavedSize);
        TextView tvrestaurantName = findViewById(R.id.tvFoodSavedRestaurantName);
        TextView tvPrice = findViewById(R.id.tvFoodSavedPrice);

        Button btnDelete = findViewById(R.id.btnDeleteSaveCardItem);
        btnDelete.setOnClickListener(view -> {
            AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
            dialog.setMessage("Bạn có muốn xóa món " + food.getName() + " không?");
            dialog.setPositiveButton("Có", (dialogInterface, i) -> {
                HomeActivity.dao.deleteFoodSavedByFoodIdAndSize(foodSize.getFoodId(), foodSize.getSize());
                SavedFragment.saved_container.removeView(this);
            });
            dialog.setNegativeButton("Không", (dialogInterface, i) -> {});
            dialog.show();
        });

        // Set information for cart card
        image.setImageBitmap(DatabaseHandler.convertByteArrayToBitmap(food.getImage()));
        tvName.setText(food.getName());
        switch (foodSize.getSize()){
            case 1:
                tvSize.setText("Size S");
                break;
            case 2:
                tvSize.setText("Size M");
                break;
            case 3:
                tvSize.setText("Size L");
                break;
        }
        tvrestaurantName.setText(restaurantName);
        tvPrice.setText(getRoundPrice(foodSize.getPrice()));
    }

    private String getRoundPrice(Double price){
        return Math.round(price) + " VNĐ";
    }
}
