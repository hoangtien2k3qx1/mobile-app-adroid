package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class view_information extends AppCompatActivity {

    private TextView txtInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_information);
        txtInfo = (TextView) findViewById(R.id.txtInfo);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String email = extras.getString("email");
            txtInfo.setText("Your email: " + email);
        }
    }
}