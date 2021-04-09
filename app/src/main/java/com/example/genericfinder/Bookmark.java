package com.example.genericfinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Bookmark extends AppCompatActivity {

    RecyclerView mRecyclerView;
    BookmarkAdapter mAdapter;
    TextView bookmarkName;
    ImageView bookmarkImg;
    Button bm_priceBtn, bm_infoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);

        bookmarkName = findViewById(R.id.bookmarkName);
        bookmarkImg = findViewById(R.id.bookmarkImg);
        bm_priceBtn = findViewById(R.id.bm_priceBtn);
        bm_infoBtn = findViewById(R.id.bm_infoBtn);

        bm_priceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //가격보기
            }
        });

        bm_infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //상세보기
            }
        });

        mRecyclerView = findViewById(R.id.bookmarkRecyclev);
        mAdapter = new BookmarkAdapter(this);
    }
}