package com.example.genericfinder;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MedicinePriceEnroll extends Fragment {

    EditText medicineCodeInput, pharmacyNumInput, priceInput;
    Button cancelBtn, enrollBtn;

    public MedicinePriceEnroll() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_medicine_price_enroll, container, false);

        medicineCodeInput = view.findViewById(R.id.medicineCodeInput);
        pharmacyNumInput = view.findViewById(R.id.pharmacyNumInput);
        priceInput = view.findViewById(R.id.priceInput);

        cancelBtn = view.findViewById(R.id.cancelBtn);
        enrollBtn = view.findViewById(R.id.enrollBtn);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //취소버튼 누름
            }
        });

        enrollBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //등록버튼 누름
                if (medicineCodeInput.getText().toString().length() == 0 ||
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
                    //########################################################################
                }
            }
        });

        return view;
    }
}