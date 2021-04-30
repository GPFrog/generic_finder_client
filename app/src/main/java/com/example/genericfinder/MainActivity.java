package com.example.genericfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText idInput, pwInput;
    Button loginBtn, gosignUpBtn, nonmemberBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idInput = (EditText)findViewById(R.id.idInput);
        pwInput = (EditText)findViewById(R.id.pwInput);

        loginBtn = (Button)findViewById(R.id.loginBtn);
//        loginBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //로그인 후 화면으로 이동
//            }
//        });

        gosignUpBtn = (Button)findViewById(R.id.gosignUpBtn);
//        gosignUpBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //회원가입 화면으로 이동
//                Intent intent = new Intent(getApplicationContext(), SignUp.class);
//                startActivity(intent);
//            }
//        });

        nonmemberBtn = (Button)findViewById(R.id.nonmemberBtn);
        nonmemberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                //비회원
//                //임시로 넘어가게 해놨음 0410 승환
//                Intent intent = new Intent(getApplicationContext(),MedicinePriceEnrollActivity.class);
//                startActivity(intent);

                //지도 API 테스트, 약국조회화면 넘어가게
                //0429 승환
                Intent intent = new Intent(getApplicationContext(),PharmacyInfoActivity.class);
                startActivity(intent);
            }
        });
    }
}