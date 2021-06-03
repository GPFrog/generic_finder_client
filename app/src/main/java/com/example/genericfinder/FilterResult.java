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

public class FilterResult extends Fragment {
    private RecyclerView.LayoutManager mLayoutManager;
    RecyclerView mRecyclerView;
    FilterResultAdapter mAdapter;
    ArrayList<FilterResultData> filterResultData;
    Button backBtn;

    public Bundle getArgument() {
        Bundle bundle = getArguments();
        return bundle;
    }

    public FilterResult() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filter_result, container, false);

        Context context = view.getContext();
        mRecyclerView = view.findViewById(R.id.filteredRecyclev);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new FilterResultAdapter(filterResultData);
        mRecyclerView.setAdapter(mAdapter);

        backBtn = view.findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //뒤로가기(MediSearchResult로)
                MediSearchResult mediSearchResult = new MediSearchResult();
                ((MainActivity)getActivity()).replaceFragment(mediSearchResult);
            }
        });

        return view;
    }
}