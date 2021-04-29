package com.example.genericfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class SearchMedicine extends AppCompatActivity {

    EditText serchName, serchIngredient, searchCompany, searchEffect;
    ImageButton searchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_medicine);

        serchName = (EditText)findViewById(R.id.serchName);
        serchIngredient = (EditText)findViewById(R.id.serchIngredient);
        searchCompany = (EditText)findViewById(R.id.searchCompany);
        searchEffect = (EditText)findViewById(R.id.searchEffect);
        searchBtn = (ImageButton)findViewById(R.id.searchBtn);

        searchBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //search 정보들로 intent해서 찾음
            }
        });
    }
}