package com.hoangtien2k3.foody_order_app.activity.ActivityImpl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hoangtien2k3.foody_order_app.R;
import com.hoangtien2k3.foody_order_app.repository.DAO;
import com.hoangtien2k3.foody_order_app.model.Notify;
import com.hoangtien2k3.foody_order_app.model.NotifyToUser;
import com.hoangtien2k3.foody_order_app.model.User;

public class SignUpActivity extends AppCompatActivity {
    private EditText txtEmail, txtUsername, txtPassword, txtConfirmPassword;
    public DAO dao;
    private ImageView btn;
    private TextView txtSignInApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        init();

        referenceSignUpAccount();
        dao = new DAO(this);

        signInForm();
    }

    private void init() {
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtConfirmPassword = (EditText) findViewById(R.id.txtConfirmPassword);
        btn = (ImageView) findViewById(R.id.btnSignUp);
        txtSignInApp = (TextView) findViewById(R.id.txtSignInApp);
    }

    private void referenceSignUpAccount(){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = txtUsername.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();
                String confirm = txtConfirmPassword.getText().toString().trim();

                if(username.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Vui lòng điền đủ thông tin!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!confirm.equals(password)){
                    Toast.makeText(getApplicationContext(), "Mật khẩu xác nhận không hợp lệ!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(dao.UserExited(username)){
                    Toast.makeText(getApplicationContext(), "Người dùng đã tồn tại!", Toast.LENGTH_SHORT).show();
                } else {
                    dao.addUser(new User(null, "", "Male", "12/04/2003", "", username, password));
                    Toast.makeText(getApplicationContext(), "Tạo tài khoản thành công!", Toast.LENGTH_SHORT).show();

                    // Make notify
                    dao.addNotify(new Notify(1, "Chào mừng bạn mới!", "Cảm ơn bạn đã sử dụng Foody! \n" +
                            "Vui lòng điểu chỉnh thông tin cá nhân bằng cách click vào icon người dùng trong mục profile!",
                            dao.getDate()));
                    dao.addNotifyToUser(new NotifyToUser(dao.getNewestNotifyId(), dao.getNewestUserId()));

                    // Return Login
                    Intent intent = new Intent();
                    intent.putExtra("username", username);
                    intent.putExtra("password", password);
                    setResult(0, intent);
                    // finish();
                    startActivity(new Intent(getApplicationContext(), SignInActivity.class));
                }
            }
        });

    }

    private void signInForm() {
        txtSignInApp.setOnClickListener(view -> {
            startActivity(new Intent(this, SignInActivity.class));
        });
    }
}
