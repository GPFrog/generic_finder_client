package com.example.genericfinder;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.genericfinder.httpConnector.RequestTask;

import org.json.JSONObject;

public class MedicineSearch extends Fragment {

    EditText searchName, searchIngredient, searchCompany, searchEffect;
    ImageButton searchBtn;
    Fragment MediSearchResult, FilterPopup;
    MainActivity mainActivity;

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

        searchName = view.findViewById(R.id.searchName);
        searchIngredient = view.findViewById(R.id.searchIngredient);
        searchCompany = view.findViewById(R.id.searchCompany);
        searchEffect = view.findViewById(R.id.searchEffect);
        searchBtn = view.findViewById(R.id.searchBtn);

        MediSearchResult = new MediSearchResult();
        FilterPopup = new FilterPopup();

        searchBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("serchName", searchName.toString());
                bundle.putString("searchIngredient", searchIngredient.toString());
                bundle.putString("searchCompany", searchCompany.toString());
                bundle.putString("searchEffect", searchEffect.toString());
                MediSearchResult.setArguments(bundle);
                FilterPopup.setArguments(bundle);

                Toast.makeText(view.getContext(),"검색 완료 후 페이지 이동", Toast.LENGTH_LONG);
                ((MainActivity)getActivity()).replaceFragment(MediSearchResult);
            }
        });

        return view;
    }
}