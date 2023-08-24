package com.hoangtien2k3.food_order.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import com.hoangtien2k3.food_order.Adapter.CategoryAdapter;
import com.hoangtien2k3.food_order.Adapter.RecommentedAdapter;
import com.hoangtien2k3.food_order.Domain.CategoryDomain;
import com.hoangtien2k3.food_order.Domain.FoodDomain;
import com.hoangtien2k3.food_order.R;

import java.util.ArrayList;

import kotlin.io.LineReader;

public class MainActivity extends Activity {
    private RecyclerView.Adapter adapter, adapter2;
    private RecyclerView recyclerViewCategoryList, recyclerViewPopularList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewCategory();
        recyclerViewPopular();

    }

    private void recyclerViewPopular() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recyclerViewPopularList = findViewById(R.id.view2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        ArrayList<FoodDomain> footlist = new ArrayList<>();
        footlist.add(new FoodDomain("Bánh mỳ", "cat_3", "Bánh pizza hương vị rất ngon, lôi cuốn, được là bằng công thức đặc biệt", 13.0, 1,20, 123 ));
        footlist.add(new FoodDomain("Bánh", "pizza1", "Bánh pizza hương vị rất ngon, lôi cuốn, được là bằng công thức đặc biệt", 15.0, 2, 20, 132 ));
        footlist.add(new FoodDomain("CoCa", "cat_1", "Bánh pizza hương vị rất ngon, lôi cuốn, được là bằng công thức đặc biệt", 10.0, 3, 20, 54 ));
        footlist.add(new FoodDomain("Đồ uống", "pizza3", "Bánh pizza hương vị rất ngon, lôi cuốn, được là bằng công thức đặc biệt", 25.0, 4, 20, 65 ));
        footlist.add(new FoodDomain("Bánh mỳ", "cat_3", "Bánh pizza hương vị rất ngon, lôi cuốn, được là bằng công thức đặc biệt", 13.0, 1,20, 1000 ));
        footlist.add(new FoodDomain("Bánh", "pizza1", "Bánh pizza hương vị rất ngon, lôi cuốn, được là bằng công thức đặc biệt", 15.0, 2, 20, 1000 ));
        footlist.add(new FoodDomain("CoCa", "cat_1", "Bánh pizza hương vị rất ngon, lôi cuốn, được là bằng công thức đặc biệt", 10.0, 3, 20, 1000 ));
        footlist.add(new FoodDomain("Đồ uống", "pizza3", "Bánh pizza hương vị rất ngon, lôi cuốn, được là bằng công thức đặc biệt", 25.0, 4, 20, 1000 ));

        adapter2 = new RecommentedAdapter(footlist);
        recyclerViewPopularList.setAdapter(adapter2);
    }

    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList = findViewById(R.id.view1);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> categoryList = new ArrayList<>();
        categoryList.add(new CategoryDomain("Pizza", "pizza1"));
        categoryList.add(new CategoryDomain("Bánh", "cat_2"));
        categoryList.add(new CategoryDomain("CoCa", "pizza3"));
        categoryList.add(new CategoryDomain("Đồ uống", "cat_4"));
        categoryList.add(new CategoryDomain("Ngon", "cat_5"));

        adapter = new CategoryAdapter(categoryList);
        recyclerViewCategoryList.setAdapter(adapter);
    }
}