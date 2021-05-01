package com.example.genericfinder;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

public class BookmarkFragment extends Fragment {

    private RecyclerView.LayoutManager mLayoutManager;
    RecyclerView mRecyclerView;
    BookmarkAdapter mAdapter;
    Button nearbyBtn;
    ArrayList<BookmarkData> bookmarkData;

    public BookmarkFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bookmark, container, false);

        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new BookmarkAdapter(bookmarkData);
        mRecyclerView.setAdapter(mAdapter);

        //근처 약국 보기
        nearbyBtn = view.findViewById(R.id.nearbyBtn);
        nearbyBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }
}