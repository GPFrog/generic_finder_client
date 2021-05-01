package com.example.genericfinder;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class CurrentPosition extends Fragment {

    Button positionBtn;
    Fragment MedicineSearch;

    public CurrentPosition() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_position, container, false);

        MedicineSearch = new MedicineSearch();

        positionBtn = view.findViewById(R.id.positionBtn);
        positionBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //현재 위치 정보 추가해야함
                Toast.makeText(view.getContext(),"의약품 검색 페이지 이동", Toast.LENGTH_LONG);
                ((MainActivity)getActivity()).replaceFragment(MedicineSearch);
            }
        });

        return view;
    }
}