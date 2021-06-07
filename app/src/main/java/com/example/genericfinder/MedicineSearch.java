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
        searchResultAdapter = new SearchResultAdapter(searchResultData);
        FilterPopup = new FilterPopup();
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

                    RequestTask requestTask = new RequestTask();
                    String rtResult = null;
                    String url = "http://152.70.89.118:4321/";
                    String tmp = "";

//                    try {
//                        if(name != "") tmp += ("name=" + name);
//                        if(ingredient != "") {
//                            if(tmp == "") tmp += ("medicineIngredient=" + ingredient);
//                            else tmp += ("&activeingredient=" + ingredient);
//                        }
//                        if(company != "") {
//                            if(tmp == "") tmp += ("company=" + company);
//                            else tmp += ("&company=" + company);
//                        }
//                        if (effect != "") {
//                            if(tmp == "") tmp += ("effect=" + effect);
//                            else tmp += ("&symptom=" + effect);
//                        }
//                        System.out.println(tmp);

//                        rtResult = requestTask.execute(url + "medicineLookup?name=" + searchName.getText().toString() + "&activeingredient=" + searchIngredient.getText().toString()
//                                + "&company=" + searchCompany.getText().toString() + "&symptom=" + searchEffect.getText().toString()).get();
//                        rtResult = rtResult.replaceAll("\"", "");
//                        String arr[] = rtResult.split("/");
//                        String medicineCode[] = new String[rtResult.length() % 3];
//                        int cnt = 0;
//
//                        System.out.println("길이 : " + arr.length);
//
//                        for(int i=0 ; i<arr.length ; i++) {
//                            System.out.println("넣을 값 : " + arr[i]);
//                            if((i % 3) == 0) {
//                                medicineCode[cnt] = arr[i];
//                                cnt++;
//                            }
//                            else if((i % 3) == 1) {
//                                data.setResultName(arr[i]);
//                            }
//                            else if((i % 3) == 2) {
//                                data.setResultPrice(arr[i]);
//                                searchResultAdapter.addItem(data);
//                            }
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }

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