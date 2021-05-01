package com.example.genericfinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MedicinePriceDeleteUActivity extends AppCompatActivity {
    //현재 Activity 담아둘 객체
    public static Activity medicinePriceDeleteUActivity;

    Button cancelBtn, deleteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_u_delete);

        //현재 Activity 정보 담아두기
        medicinePriceDeleteUActivity = MedicinePriceDeleteUActivity.this;

        //이전 Activity(사용자 가격 등록) 종료
        MedicinePriceEnrollActivity medicinePriceEnrollActivity = (MedicinePriceEnrollActivity) MedicinePriceEnrollActivity.medicinePriceEnrollActivity;
        medicinePriceEnrollActivity.finish();

//        medicineCodeInput = (EditText) findViewById(R.id.medicineCodeInput);
//        pharmacyNumInput = (EditText) findViewById(R.id.pharmacyNumInput);
//        priceInput = (EditText) findViewById(R.id.priceInput);

        cancelBtn = (Button)findViewById(R.id.cancelBtn);
        deleteBtn = (Button)findViewById(R.id.deleteBtn);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //취소버튼 누름
                onBackPressed();//뒤로 가기
                overridePendingTransition(0, 0); //깔끔한 화면전환
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //삭제버튼 누름

                //########################################################################
                //테스트
                Toast.makeText(v.getContext(),"정상적으로 누름",Toast.LENGTH_SHORT).show();

                //관리자 가격 삭제 화면으로 넘어가게..
                Intent intent = new Intent(getApplicationContext(),MedicinePriceDeleteAActivity.class);
                startActivity(intent);
                //########################################################################
            }
        });
    }
}
