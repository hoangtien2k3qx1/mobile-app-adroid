package com.hoangtien2k3.foody_order_app.activity.ActivityImpl;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hoangtien2k3.foody_order_app.R;
import com.hoangtien2k3.foody_order_app.repository.DAO;
import com.hoangtien2k3.foody_order_app.model.User;

public class SignInActivity extends AppCompatActivity {
    private ImageView btnSignInApp;
    private EditText txtUsername, txtPassword;
    private TextView validate, textSignUpApp;
    private boolean isValidEmail = false;
    private final String emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z]+\\.+[a-z]{2,}";

    public static final String PREFERENCES = "store_info";
    private SharedPreferences sharedPreferences;
    public static DAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        initializeUI();
        dao = new DAO(this);

        checkLogin();
        sharedPreferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        txtUsername.setText(sharedPreferences.getString("username", ""));
        txtPassword.setText(sharedPreferences.getString("password", ""));

        setNextActivityListener();
    }

    private void initializeUI() {
        btnSignInApp = findViewById(R.id.btnSignInApp);
        txtUsername = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        validate = findViewById(R.id.validate);
        textSignUpApp = (TextView) findViewById(R.id.textSignUpApp);
    }

    private void checkLogin() {
        btnSignInApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = txtUsername.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();

                User userExist = dao.getUserByUsernameAndPassword(username, password);

                /*if (!isValidEmail) {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.email_and_password_failed), Toast.LENGTH_SHORT).show();
                    return;
                }*/

                if (!username.isEmpty() && !password.isEmpty()) {
                    boolean isRightAuthentication = false;
                    if (userExist != null) {
                        isRightAuthentication = dao.signIn(userExist);
                    }
                    if (isRightAuthentication) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username", userExist.getUsername());
                        editor.putString("password", userExist.getPassword());
                        editor.apply();

                        Intent intent = new Intent(SignInActivity.this, HomeActivity.class);
                        HomeActivity.user = userExist; // truyền cái User đã đăng ký tài khoản vào HomeActivity
                        startActivity(intent); // start nó lên

                    } else {
                        Toast.makeText(getApplicationContext(), "Sai thông tin đăng nhập", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SignInActivity.this, getResources().getString(R.string.input_full_username_and_password), Toast.LENGTH_SHORT).show();
                }
            }
        });

        txtUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validate.setText("");
                isValidEmail = s.length() > 0 && s.toString().trim().matches(emailPattern);
                if (!isValidEmail) {
                    validate.setText(getResources().getString(R.string.email_failed));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null) {
            txtUsername.setText(data.getStringExtra("username"));
            txtPassword.setText(data.getStringExtra("password"));
        }
    }


    private void setNextActivityListener() {
        textSignUpApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
}
