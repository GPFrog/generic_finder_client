package com.example.genericfinder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;



public class SearchResultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //무한스크롤
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    ArrayList<SearchResultData> srData = new ArrayList<>();

    LayoutInflater mInflater;
    Context mContext;
    String searchName, searchIngredient, searchCompany, searchEffect;
    MainActivity mainActivity;

    public SearchResultAdapter(Context context) {mInflater = LayoutInflater.from(context);}

    public SearchResultAdapter(ArrayList<SearchResultData> items) {
        srData = items;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_search_result, parent, false);
            return new searchResultViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) { // searchResultViewHolder
        if (holder instanceof searchResultViewHolder) {
            populateItemRows((searchResultViewHolder) holder, position);
        } else if (holder instanceof LoadingViewHolder) {
            showLoadingView((LoadingViewHolder) holder, position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return srData.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return srData.size();
    }

    private void showLoadingView(LoadingViewHolder holder, int position) {

    }

    private void populateItemRows(searchResultViewHolder holder, int position) {
        SearchResultData item = srData.get(position);
        holder.onBind(item);
    }

    void addItem(SearchResultData data) {
        System.out.println("데이터 이름 : " + data.getResultName());
        srData.add(data);
    }

    class LoadingViewHolder extends RecyclerView.ViewHolder {
        private ProgressBar progressBar;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
//            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }

    class searchResultViewHolder extends RecyclerView.ViewHolder {
        public TextView result_name, result_price;
        public Button result_info;
        SearchResultAdapter mAdapter;

        public searchResultViewHolder(@NonNull View itemView) { // , SearchResultAdapter adapter
            super(itemView);

            result_name = itemView.findViewById(R.id.result_name);
            result_price = itemView.findViewById(R.id.result_price);
            result_info = itemView.findViewById(R.id.result_info);

//            medicineInfo = new MedicineInfo();
            mainActivity = new MainActivity();

            //상세정보 버튼
            result_info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //약 상세 조회로 (약 이름 정보 넘겨줌)
//                    Bundle bundle = new Bundle();
//                    bundle.putString("result_name", result_name.getText().toString());
//                    medicineInfo.setArguments(bundle); // 데이터 전달

                    Intent intent = new Intent(itemView.getContext(), MedicineInfoActivity.class);
                    intent.putExtra("result_name", result_name.getText().toString());
                    view.getContext().startActivity(intent);


//                    mainActivity.replaceFragment(medicineInfo);
//                    MediSearchResult ms = new MediSearchResult();
//                    ms.replaceFragment(medicineInfo);
//                    FragmentManager fragmentManager = mainActivity.getSupportFragmentManager();
//                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                    fragmentTransaction.replace(R.id.container, medicineInfo).commit();
                }
            });

//            this.mAdapter = adapter;
        }

        void onBind(SearchResultData data) {
            result_name.setText(data.getResultName());
            result_price.setText(data.getResultPrice());
        }
    }
}
