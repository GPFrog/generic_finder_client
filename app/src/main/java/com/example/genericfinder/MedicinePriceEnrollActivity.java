package com.example.genericfinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MedicinePriceEnrollActivity extends AppCompatActivity{

    //현재 Activity 담아둘 객체
    public static Activity medicinePriceEnrollActivity;

    EditText medicineCodeInput, pharmacyNumInput, priceInput;
    Button cancelBtn, enrollBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_enroll);

        //현재 Activity 정보 담아두기
        medicinePriceEnrollActivity = MedicinePriceEnrollActivity.this;

        medicineCodeInput = (EditText) findViewById(R.id.medicineCodeInput);
        pharmacyNumInput = (EditText) findViewById(R.id.pharmacyNumInput);
        priceInput = (EditText) findViewById(R.id.priceInput);

        cancelBtn = (Button)findViewById(R.id.cancelBtn);
        enrollBtn = (Button)findViewById(R.id.enrollBtn);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //취소버튼 누름
                onBackPressed();//뒤로 가기
                overridePendingTransition(0, 0); //깔끔한 화면전환
            }
        });

        enrollBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //등록버튼 누름
                if ( medicineCodeInput.getText().toString().length() == 0 ||
                pharmacyNumInput.getText().toString().length()==0 ||
                priceInput.getText().toString().length()==0) {
                    //공백일 때 예외처리
                    Toast.makeText(v.getContext(),"값을 입력해주세요",Toast.LENGTH_SHORT).show();
                }else {
                    //값 정상입력 됐을 때
                    String mCode = medicineCodeInput.getText().toString();
                    String pNum = pharmacyNumInput.getText().toString();
                    String price = priceInput.getText().toString();

                    //########################################################################
                    //값 넘겨주는 것 작성
                    //테스트
                    Toast.makeText(v.getContext(),mCode+" / "+pNum+" / "+price,Toast.LENGTH_SHORT).show();

                    //사용자 가격 삭제 화면으로 넘어가게..
                    Intent intent = new Intent(getApplicationContext(),MedicinePriceDeleteUActivity.class);
                    startActivity(intent);
                    //########################################################################
                }
            }
        });
    }
}
