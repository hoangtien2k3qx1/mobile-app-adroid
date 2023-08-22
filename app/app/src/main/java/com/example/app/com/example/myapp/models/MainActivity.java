package com.example.app.com.example.myapp.models;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app.PlacesActivity;
import com.example.app.R;

public class MainActivity extends Activity {
    private EditText txtEmail;
    private EditText txtPassword;
    private Button btnLogin;
    private TextView txtValidate;
    // validation
    private boolean isValidEmail = false;
    private final String emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z]+\\.+[a-z]{2,}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUI();
        checkLogin();
    }

    // setupUI
    public void setupUI() {
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        txtValidate = findViewById(R.id.txtValidate);
    }

    private void checkLogin() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (!isValidEmail) {
//                    Toast.makeText(getApplicationContext(), "Validate email failed", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                Intent intent = new Intent(MainActivity.this, PlacesActivity.class);
                intent.putExtra("email", txtEmail.getText().toString().trim());
                startActivity(intent);
            }
        });

        // validation email
        txtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // validate here
                txtValidate.setText("");
                String email = txtEmail.getText().toString().trim();
                isValidEmail = email.matches(emailPattern) && (s != null ? s.length() : 0) > 0;
                if (!isValidEmail) {
                    txtValidate.setTextColor(Color.rgb(255, 0, 0));
                    txtValidate.setText("Invalid email address");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
}
