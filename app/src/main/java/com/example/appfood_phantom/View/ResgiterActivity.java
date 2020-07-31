package com.example.appfood_phantom.View;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appfood_phantom.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class ResgiterActivity extends AppCompatActivity {
    Button mResgiter;
    EditText mEmailEdt, mPasswordEdt, mEnterThePassword;
    FirebaseAuth auth;
ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Đăng ký");
        auth = FirebaseAuth.getInstance();
        mResgiter = findViewById(R.id.btn_resgiter);
        mEmailEdt = findViewById(R.id.edt_email);
        mPasswordEdt = findViewById(R.id.edt_password);
        mEnterThePassword = findViewById(R.id.edt_enterThePassword);
        pd=new ProgressDialog(this);
        mResgiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.setMessage("Đang đăng ký");
                pd.show();
                String email = mEmailEdt.getText().toString();
                String password = mPasswordEdt.getText().toString();
                String enterThePassword = mEnterThePassword.getText().toString();
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(enterThePassword)) {
                    Toast.makeText(ResgiterActivity.this, "Không được bỏ trống thông tin", Toast.LENGTH_SHORT).show();
                } else if (password.trim().length() <= 6) {
                    Toast.makeText(ResgiterActivity.this, "Mật khẩu ít nhất 6 ký tự !", Toast.LENGTH_SHORT).show();
                } else if (!enterThePassword.equals(password)) {
                    Toast.makeText(ResgiterActivity.this, "Mật khẩu nhập lại khác với ban đầu?", Toast.LENGTH_SHORT).show();
                } else {
                    auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ResgiterActivity.this, "Đăng ký thàng công!", Toast.LENGTH_SHORT).show();
                                pd.dismiss();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ResgiterActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
