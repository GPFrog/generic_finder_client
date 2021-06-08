package com.example.genericfinder;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bookmark, container, false);
        bookmarkData = new ArrayList<>();

        SharedPreferences getSh = getContext().getSharedPreferences("bookmark", Context.MODE_PRIVATE);
        String markEdit = getSh.getString("bookmark","");
//        email.setText(markEdit); //SharedPreferences 에서 값 받아오기
//        SharedPreferences sharedPreferences = view.getContext().getSharedPreferences("bookmark", MODE_PRIVATE);
        String[] arr = markEdit.split(",");

        for(int i=0;i<arr.length - 1;i++) {
            System.out.println(arr[i]);
            BookmarkData db = new BookmarkData(arr[i]);
            bookmarkData.add(db);
        }
        for(int i=0;i<bookmarkData.size();i++) {
            System.out.println(bookmarkData.get(i));
        }

        Context context = view.getContext();
        mRecyclerView = view.findViewById(R.id.bookmarkRecyclev);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new BookmarkAdapter(view.getContext(), bookmarkData);
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }
}