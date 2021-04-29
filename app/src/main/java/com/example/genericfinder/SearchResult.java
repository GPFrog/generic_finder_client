package com.example.genericfinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SearchResult extends AppCompatActivity {

    RecyclerView mRecyclerView;
    SearchResultAdapter mAdapter;
    Button filterBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        filterBtn = findViewById(R.id.filterBtn);
        filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //필터 버튼
                Intent filterIntent = new Intent(getApplicationContext(), FilterPopup.class);
                startActivity(filterIntent);
            }
        });

        mRecyclerView = findViewById(R.id.resultRecyclev);
        mAdapter = new SearchResultAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}