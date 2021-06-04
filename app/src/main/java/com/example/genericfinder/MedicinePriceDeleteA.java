package com.example.genericfinder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MedicinePriceDeleteA extends Fragment {

    private RecyclerView.LayoutManager mLayoutManager;
    RecyclerView mRecyclerView;
    MedicinePriceDeleteAAdapter mAdapter;
    Button cancelBtn;
    ArrayList<MedicinePriceData> mpdata;
    Fragment MedicineSearch;

    public MedicinePriceDeleteA() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_medicine_price_delete, container, false);

        Context context = view.getContext();
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MedicinePriceDeleteAAdapter(mpdata);
        mRecyclerView.setAdapter(mAdapter);
        MedicineSearch = new MedicineSearch();

        cancelBtn = view.findViewById(R.id.cancelBtn);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //이렇게 하면 메뉴 버튼 눌려서 들어갈 때는 돌아갈 페이지 없음
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                fragmentManager.beginTransaction().remove(MedicinePriceDeleteA.this).commit();
//                fragmentManager.popBackStack();

                //검색 화면으로 돌아가게 한담
                ((MainActivity)getActivity()).replaceFragment(MedicineSearch);
            }
        });

        return view;
    }
}