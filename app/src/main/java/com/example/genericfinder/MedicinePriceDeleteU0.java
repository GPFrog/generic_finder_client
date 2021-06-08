package com.example.genericfinder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.genericfinder.httpConnector.RequestTask;

import java.util.ArrayList;

public class MedicinePriceDeleteU0 extends Fragment {

    Button lookupBtn;
    Fragment MedicinePriceDeleteU;
    MedicinePriceDeleteUAdapter medicinePriceDeleteUAdapter;
    ArrayList<MedicinePriceUData> medicinePriceUData;
    MedicinePriceUData data;

    public MedicinePriceDeleteU0() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_medicine_price_delete_u0, container, false);

        MedicinePriceDeleteU = new MedicinePriceDeleteU();

        //값 가져오는 코드
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("email", Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("LogOnEmail","");
        System.out.println("로그인 아이디 받아온거 = "+id);

        medicinePriceDeleteUAdapter = new MedicinePriceDeleteUAdapter();
        medicinePriceUData = new ArrayList<>();
        data = new MedicinePriceUData();

        //조회버튼
        lookupBtn = view.findViewById(R.id.lookupBtn);
        lookupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();

                bundle.putString("id",id);

                Toast.makeText(view.getContext(),"등록 가격 리스트 조회", Toast.LENGTH_SHORT);

                //등록 가격 리스트 화면으로 넘어감
                ((MainActivity)getActivity()).replaceFragment(MedicinePriceDeleteU);

                MedicinePriceDeleteU.setArguments(bundle);
            }
        });
        return view;
    }
}