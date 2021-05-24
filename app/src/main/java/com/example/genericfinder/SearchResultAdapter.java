package com.example.genericfinder;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.searchResultViewHolder> {
    ArrayList<SearchResultData> srData = new ArrayList<>();
    LayoutInflater mInflater;
    Context mContext;

    public SearchResultAdapter(Context context) {mInflater = LayoutInflater.from(context);}

    public SearchResultAdapter(ArrayList<SearchResultData> srData) {this.srData = srData;}

    @NonNull
    @Override
    public SearchResultAdapter.searchResultViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View mItemView = mInflater.inflate(R.layout.list_search_result, viewGroup, false);
        return new searchResultViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchResultAdapter.searchResultViewHolder holder, int position) {
        holder.onBind(srData.get(position));

        holder.result_name.setText(srData.get(position).getResultName());
        holder.result_price.setText(srData.get(position).getResultPrice());
        holder.result_img.setImageResource(srData.get(position).getResultImg());
    }

    @Override
    public int getItemCount() {return null != srData ? srData.size() : 0;}

    void addItem(SearchResultData data) {srData.add(data);}

    class searchResultViewHolder extends RecyclerView.ViewHolder {
        String name;
        public TextView result_name, result_price;
        public ImageView result_img;
        public  Button result_info;
        SearchResultAdapter mAdapter;

        public searchResultViewHolder(final View itemView, SearchResultAdapter adapter) {
            super(itemView);

            result_name = itemView.findViewById(R.id.result_name);
            result_price = itemView.findViewById(R.id.result_price);
            result_img = itemView.findViewById(R.id.result_img);
            result_info = itemView.findViewById(R.id.result_info);

            //부가정보 버튼
            result_info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //약 상세 조회로 (약 이름으로?)
//                    Bundle bundle = new Bundle();
//                    bundle.putString("result_name", result_name.toString());
//                    MedicineInfo medicineInfo = new MedicineInfo();
//                    medicineInfo.setArguments(bundle); // 데이터 전달
                }
            });

            this.mAdapter = adapter;
        }

        void onBind(SearchResultData data) {
            result_name.setText(data.getResultName());
            result_price.setText(data.getResultPrice());
            result_img.setImageResource(data.getResultImg());
        }
    }
}
