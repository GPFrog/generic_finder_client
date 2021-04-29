package com.example.genericfinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Bookmark extends AppCompatActivity {

    RecyclerView mRecyclerView;
    BookmarkAdapter mAdapter;
    Button nearbyBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);

        nearbyBtn = findViewById(R.id.nearbyBtn);
        nearbyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //근처 약국 보기
            }
        });

        mRecyclerView = findViewById(R.id.bookmarkRecyclev);
        mAdapter = new BookmarkAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}