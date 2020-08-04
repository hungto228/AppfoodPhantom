package com.example.appfood_phantom.View;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appfood_phantom.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class ForgotPassword extends AppCompatActivity {
    FirebaseAuth auth;
    EditText mEmailEdt;
    Button mRestorePasswordBtn;
    TextView mResgitertv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Quên mật khẩu");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        auth=FirebaseAuth.getInstance();
        mResgitertv=findViewById(R.id.tv_resgiterAccount);
        mEmailEdt = findViewById(R.id.edt_email);
        mRestorePasswordBtn = findViewById(R.id.btn_restorePassword);
        mRestorePasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmailEdt.getText().toString();
//                android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
                if (TextUtils.isEmpty(email) && checkEmail(email)) {
                    Toast.makeText(ForgotPassword.this, "Hãy điền đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ForgotPassword.this, "Hãy xem email !!", Toast.LENGTH_SHORT).show();
                                Intent intent =new Intent(ForgotPassword.this,LoginActivity.class);
                                startActivity(intent);

                            }else {
                                String error=task.getException().getMessage();
                                Toast.makeText(ForgotPassword.this, error, Toast.LENGTH_SHORT).show();
                            }
                        }

                    });
                }
            }
        });
        mResgitertv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForgotPassword.this,ResgiterActivity.class));
            }
        });

    }
    private  boolean checkEmail(String email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
