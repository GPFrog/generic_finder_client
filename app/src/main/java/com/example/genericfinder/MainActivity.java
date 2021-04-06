package com.example.genericfinder;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends BaseActivity {

    EditText idInput, pwInput;
    Button loginBtn, singUpBtn, nonmemberBtn;

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

        singUpBtn = (Button)findViewById(R.id.singUpBtn);
//        singUpBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //회원가입 화면으로 이동
//            }
//        });

        nonmemberBtn = (Button)findViewById(R.id.nonmemberBtn);
//        nonmemberBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //비회원
//            }
//        });
    }
}