package com.example.genericfinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.genericfinder.httpConnector.RequestTask;

public class MedicinePriceEnrollActivity extends AppCompatActivity {

    TextView medicineCode;
    EditText pharmacyNumInput, priceInput;
    Button cancelBtn, enrollBtn;
    Fragment MedicineSearch, MedicinePriceDeleteU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_price_enroll);

        medicineCode = findViewById(R.id.medicineCode);
        pharmacyNumInput = findViewById(R.id.pharmacyNumInput);
        priceInput = findViewById(R.id.priceInput);
        MedicineSearch = new MedicineSearch();
        MedicinePriceDeleteU = new MedicinePriceDeleteU();

        cancelBtn = findViewById(R.id.cancelBtn);
        enrollBtn = findViewById(R.id.enrollBtn);

        Intent getIn = getIntent();
        medicineCode.setText(getIn.getStringExtra("enrollName"));

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //의약품 검색 화면으로
                finish();
            }
        });

        Context context = this;

        SharedPreferences sharedPreferences = this.getSharedPreferences("email", Context.MODE_PRIVATE);
        String emailEdit = sharedPreferences.getString("signupEmail","");

        enrollBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(context);
                dlg.setTitle("의약품 가격 등록");
                dlg.setMessage("입력하신 정보를 등록하시겠습니까?");
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        RequestTask requestTask = new RequestTask();
                        String rtResult = null;
                        String url = "http://localhost:4000/";

                        try {
                            rtResult = requestTask.execute(url + "medicinePriceEnroll?email=" + emailEdit + "&medicine_code=" + medicineCode.getText().toString() + "&price=" + priceInput.getText().toString()
                                + "&bussiness_number=" + pharmacyNumInput.getText().toString()).get();
                            rtResult = rtResult.replaceAll("\"", "");
                            String arr[] = rtResult.split("/");
                            System.out.println(arr[0]);

                            if (arr[0].compareTo("true") == 0) {
                                Toast.makeText(getApplicationContext(), "의약품 가격이 등록되었습니다.", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "의약품 가격이 등록되었습니다.", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "의약품 가격이 등록되었습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(), "등록이 취소되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
                dlg.show();

//                    Bundle popBundle = new Bundle();
//                    popBundle.putString("medicineName", mediName);
//                    popBundle.putString("pharmacyNum", pharmacyNumInput.getText().toString());
//                    popBundle.putString("price", priceInput.getText().toString());
//                    priceEnrollPopup.setArguments(popBundle);
//                    ((MainActivity)getActivity()).replaceFragment(priceEnrollPopup);
//                }
            }
        });
    }
}