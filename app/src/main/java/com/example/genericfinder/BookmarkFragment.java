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
    ArrayList<BookmarkData> bookmarkData;

    public BookmarkFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bookmark, container, false);

        Context context = view.getContext();
        mRecyclerView = view.findViewById(R.id.bookmarkRecyclev);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new BookmarkAdapter(bookmarkData);
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }
}