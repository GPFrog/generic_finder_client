package com.example.genericfinder;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;

public class CurrentPosition extends Fragment {

    EditText currentPEdit;
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
        currentPEdit = view.findViewById(R.id.currentPEdit);

        positionBtn = view.findViewById(R.id.positionBtn);
        positionBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //현재 위치 로컬에 저장
                Context context = getActivity();
                SharedPreferences sharedPreferences = context.getSharedPreferences("Value", MODE_PRIVATE);    // test 이름의 기본모드 설정
                SharedPreferences.Editor editor = sharedPreferences.edit(); //sharedPreferences를 제어할 editor를 선언
                editor.putString("currentPosition", currentPEdit.getText().toString()); // key,value 형식으로 저장
                editor.commit();    //최종 커밋. 커밋을 해야 저장이 된다.

                Toast.makeText(view.getContext(),"의약품 검색 페이지 이동", Toast.LENGTH_LONG);
                ((MainActivity)getActivity()).replaceFragment(MedicineSearch);
            }
        });

        return view;
    }
}