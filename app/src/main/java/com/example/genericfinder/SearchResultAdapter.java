package com.example.genericfinder;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.genericfinder.httpConnector.RequestTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.searchResultViewHolder> {
    ArrayList<SearchResultData> srData = new ArrayList<>();
    LayoutInflater mInflater;
    Context mContext;
    String searchName, searchIngredient, searchCompany, searchEffect;

    public SearchResultAdapter() {}

    public SearchResultAdapter(Context context) {mInflater = LayoutInflater.from(context);}

    public SearchResultAdapter( ArrayList<SearchResultData> srData) {
        this.srData = srData;
    }
    public SearchResultAdapter(Context context, ArrayList<SearchResultData> srData) {
        mInflater = LayoutInflater.from(context);
        this.srData = srData;
    }

//    public SearchResultAdapter(Context context, ArrayList<SearchResultData> searchResultData) {
//        this.mContext = context;
//        this.srData = searchResultData;
//    }

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
//        holder.result_img.setImageResource(Glide.with().load(srData.get(position).getResultImg()));
    }

    @Override
    public int getItemCount() {return srData.size();}

    void addItem(SearchResultData data) {
        System.out.println("데이터 이름 : " + data.getResultName());
        srData.add(data);
    }

    class searchResultViewHolder extends RecyclerView.ViewHolder {
        public TextView result_name, result_price;
        public Button result_info;
        SearchResultAdapter mAdapter;

        public searchResultViewHolder(View itemView, SearchResultAdapter adapter) {
            super(itemView);

            result_name = itemView.findViewById(R.id.result_name);
            result_price = itemView.findViewById(R.id.result_price);
            result_info = itemView.findViewById(R.id.result_info);

            //부가정보 버튼
            result_info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //약 상세 조회로 (약 이름 정보 넘겨줌)
                    Bundle bundle = new Bundle();
                    bundle.putString("result_name", result_name.getText().toString());
                    MedicineInfo medicineInfo = new MedicineInfo();
                    medicineInfo.setArguments(bundle); // 데이터 전달
                }
            });

            this.mAdapter = adapter;
        }

        void onBind(SearchResultData data) {
            result_name.setText(data.getResultName());
            result_price.setText(data.getResultPrice());
//            Glide.with(itemView).load(data.getResultImg()).into(result_img);
        }
    }
}
