package com.example.genericfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUp extends AppCompatActivity {

    EditText emailEdit, codeEdit;
    Button rtBtn, signupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        emailEdit = (EditText)findViewById(R.id.emailEdit);
        codeEdit = (EditText)findViewById(R.id.codeEdit);
        rtBtn = (Button)findViewById(R.id.rtBtn);
        signupBtn = (Button)findViewById(R.id.signupBtn);

        //재전송 버튼 클릭 이벤트
        rtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //회원가입 버튼 클릭 이벤트
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}