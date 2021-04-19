package com.example.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class logInActivity extends AppCompatActivity {
    Button btn_login ;
    TextView register;
    EditText et_email, et_password;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);                                //XML코드를 java에 붙이기



        firebaseAuth = FirebaseAuth.getInstance();
        btn_login = findViewById(R.id.logIn);
        register = findViewById(R.id.register);
        et_email = findViewById(R.id.editTextTextPersonName);
        et_password = findViewById(R.id.Edit_password);

        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(logInActivity.this, signUpActivity.class);       //회원가입버튼 누를 시

                startActivity(intent);

            }
        });


        btn_login.setOnClickListener(new View.OnClickListener() {               //버튼을 눌렀을 때 감지하는 옵저버?...
            @Override
            public void onClick(View v) {
                String email = et_email.getText().toString().trim();
                String pwd = et_password.getText().toString().trim();
                firebaseAuth.signInWithEmailAndPassword(email, pwd)
                        .addOnCompleteListener(logInActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // TODO: 로그인 성공시 페이지 이동
                                    Toast.makeText(logInActivity.this,"로그인 성공",Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(logInActivity.this, "로그인 오류", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });
    }
}
