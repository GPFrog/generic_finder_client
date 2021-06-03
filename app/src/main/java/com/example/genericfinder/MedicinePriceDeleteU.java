package com.example.genericfinder;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class MedicinePriceDeleteU extends Fragment {

    Button cancelBtn;
    Fragment MedicineSearch;

    public MedicinePriceDeleteU() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_medicine_price_delete_u, container, false);

        MedicineSearch = new MedicineSearch();

        cancelBtn = view.findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //이렇게 하면 메뉴 버튼 눌려서 들어갈 때는 돌아갈 페이지 없음
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                fragmentManager.beginTransaction().remove(MedicinePriceDeleteU.this).commit();
//                fragmentManager.popBackStack();
                //검색 화면으로 돌아가야함
                ((MainActivity)getActivity()).replaceFragment(MedicineSearch);
            }
        });
        return view;
    }
}