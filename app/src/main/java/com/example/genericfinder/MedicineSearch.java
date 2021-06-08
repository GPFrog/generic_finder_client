package com.example.genericfinder;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.genericfinder.httpConnector.RequestTask;

import java.util.ArrayList;

public class MedicineSearch extends Fragment {

    EditText searchName, searchIngredient, searchCompany, searchEffect;
    ImageButton searchBtn;
    Fragment MediSearchResult, FilterPopup;
    SearchResultAdapter searchResultAdapter;
    ArrayList<SearchResultData> searchResultData;
    SearchResultData data;
    MainActivity mainActivity;
    String name, ingredient, company, effect;

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

//        name = searchName.getText().toString();
//        ingredient = searchIngredient.getText().toString();
//        company = searchCompany.getText().toString();
//        effect = searchEffect.getText().toString();

        MediSearchResult = new MediSearchResult();
        searchResultAdapter = new SearchResultAdapter();
        searchResultData = new ArrayList<>();
        data = new SearchResultData();

        searchBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();

                if(searchName.getText().toString() == "" && searchIngredient.getText().toString() == "" &&
                        searchCompany.getText().toString() == "" && searchEffect.getText().toString() == "" ) Toast.makeText(view.getContext(), "하나 이상의 값을 입력하세요.", Toast.LENGTH_SHORT).show();
                else {
                    bundle.putString("searchName", searchName.getText().toString());
                    bundle.putString("searchIngredient", searchIngredient.getText().toString());
                    bundle.putString("searchCompany", searchCompany.getText().toString());
                    bundle.putString("searchEffect", searchEffect.getText().toString());

                    Toast.makeText(view.getContext(),"검색 완료 후 페이지 이동", Toast.LENGTH_SHORT);
                    ((MainActivity)getActivity()).replaceFragment(MediSearchResult);
                }
                MediSearchResult.setArguments(bundle);
                FilterPopup.setArguments(bundle);
            }
        });

        return view;
    }
}