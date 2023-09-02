package com.hoangtien2k3.foody_order_app.activity.ActivityImpl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.hoangtien2k3.foody_order_app.R;
import com.hoangtien2k3.foody_order_app.activity.ViewPagerAdapter.ViewPagerAdapter;

// dùng để thiết lập hiển thị các Fragment lên Activity
public class CartActivity extends AppCompatActivity {
    private TabLayout tableLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        initializaUITest();

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapter);
        tableLayout.setupWithViewPager(viewPager);
    }

    private void initializaUITest() {
        tableLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
    }

}