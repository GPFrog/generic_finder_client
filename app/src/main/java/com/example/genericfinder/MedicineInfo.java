package com.example.genericfinder;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MedicineInfo extends Fragment {

    ImageView mediInfoImg;
    ToggleButton bookmarkBtn;
    TextView mediName, mediCompany, mediIngedient, mediTake, mediCaution, avgPrice;

    public MedicineInfo() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_medicine_info, container, false);

        mediInfoImg = view.findViewById(R.id.mediInfoImg);
        mediName = view.findViewById(R.id.mediName);
        mediCompany = view.findViewById(R.id.mediCompany);
        mediIngedient = view.findViewById(R.id.mediIngedient);
        mediTake = view.findViewById(R.id.mediTake);
        mediCaution = view.findViewById(R.id.mediCaution);
        avgPrice = view.findViewById(R.id.avgPrice);

        //즐겨찾기 버튼 토글
        bookmarkBtn = view.findViewById(R.id.bookmarkBtn);
        bookmarkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bookmarkBtn.isChecked()) {

                } else {

                }
            }
        });
        return view;
    }
}