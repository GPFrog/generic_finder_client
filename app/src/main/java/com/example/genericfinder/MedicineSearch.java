package com.example.genericfinder;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MedicineSearch extends Fragment {

    EditText serchName, serchIngredient, searchCompany, searchEffect;
    ImageButton searchBtn;
    Fragment MediSearchResult;

    public MedicineSearch() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_medicine_search, container, false);

        serchName = view.findViewById(R.id.serchName);
        serchIngredient = view.findViewById(R.id.serchIngredient);
        searchCompany = view.findViewById(R.id.searchCompany);
        searchEffect = view.findViewById(R.id.searchEffect);
        searchBtn = view.findViewById(R.id.searchBtn);

        MediSearchResult = new MediSearchResult();

        searchBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //검색 정보 가지고 이동
//                Bundle bundle = new Bundle();
//                bundle.putString("serchName", serchName.toString());
//                MediSearchResult.setArguments(bundle);

                Toast.makeText(view.getContext(),"검색 완료 후 페이지 이동", Toast.LENGTH_LONG);
                ((MainActivity)getActivity()).replaceFragment(MediSearchResult);
            }
        });

        return view;
    }
}