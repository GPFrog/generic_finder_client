package com.example.genericfinder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MediSearchResult extends Fragment {

    private RecyclerView.LayoutManager mLayoutManager;
    RecyclerView mRecyclerView;
    SearchResultAdapter mAdapter;
    ArrayList<SearchResultData> searchResultData;
    Button filterBtn;

    public MediSearchResult() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_medi_search_result, container, false);

        Context context = view.getContext();
        mRecyclerView = view.findViewById(R.id.resultRecyclev);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new SearchResultAdapter(searchResultData);
        mRecyclerView.setAdapter(mAdapter);

        filterBtn = view.findViewById(R.id.filterBtn);
        filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //필터 버튼 (필터 팝업 띄우기)
                Bundle bundle = new Bundle();

                FilterPopup fpopup =  new FilterPopup();
                fpopup.setArguments(bundle);
                fpopup.show(getActivity().getSupportFragmentManager(), "FilterPopup");
            }
        });

        return view;
    }
}