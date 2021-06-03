package com.example.genericfinder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MediSearchResult extends Fragment {
    private static final String TAG = "MediSearchResult";
    boolean isLoading = false;

    private RecyclerView.LayoutManager mLayoutManager;
    RecyclerView mRecyclerView;
    SearchResultAdapter mAdapter;
    ArrayList<SearchResultData> searchResultData;
    Button filterBtn;

    public Bundle getArgument() {
        Bundle bundle = getArguments();
        return bundle;
    }

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
                //검색 결과 보내기
                Bundle bundle = new Bundle();

                FilterPopup fpopup =  new FilterPopup();
                fpopup.setArguments(bundle);
                fpopup.show(getActivity().getSupportFragmentManager(), "FilterPopup");
            }
        });

        initScrollListener(mRecyclerView, view);

        return view;
    }

    //무한 스크롤
    void dataMore() {
        Log.d(TAG, "dataMore: ");
        searchResultData.add(null);
        mAdapter.notifyItemInserted(searchResultData.size() - 1);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                searchResultData.remove(searchResultData.size() - 1);
                int scrollPosition = searchResultData.size();
                mAdapter.notifyItemRemoved(scrollPosition);
                int currentSize = scrollPosition;

                String[] arrayMedi = null;
                for(String medicine : arrayMedi) {
                    searchResultData.add(new SearchResultData(medicine));
                }

                mAdapter.notifyDataSetChanged();
                isLoading = false;
            }
        }, 2000);
    }

    public void initScrollListener(RecyclerView recyclerView, View view) {
        if(searchResultData.size() == 0) {
            isLoading = true;
            dataMore();
        }

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.d(TAG, "onScrollStateChanged: ");
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.d(TAG, "onScrolled: ");

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager)recyclerView.getLayoutManager();

                if(isLoading && (searchResultData.size() % 20) == 0 && searchResultData.size() != 0) {
                    if(linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == searchResultData.size() - 1) {
                        isLoading = true;
                        dataMore();
                        Toast.makeText(view.getContext(), "스크롤감지", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}