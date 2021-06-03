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
//        holder.onBind(srData.get(position));

//        holder.result_name.setText(srData.get(position).getResultName());
//        holder.result_price.setText(srData.get(position).getResultPrice());
//        holder.result_img.setImageResource(Glide.with().load(srData.get(position).getResultImg()));
    }

    @Override
    public int getItemCount() {return null != srData ? srData.size() : 0;}

    void addItem(SearchResultData data) {srData.add(data);}

    class searchResultViewHolder extends RecyclerView.ViewHolder {
        public TextView result_name, result_price;
        public ImageView result_img;
        public Button result_info;
        SearchResultAdapter mAdapter;

        public searchResultViewHolder(final View itemView, SearchResultAdapter adapter) {
            super(itemView);

            result_name = itemView.findViewById(R.id.result_name);
            result_price = itemView.findViewById(R.id.result_price);
            result_img = itemView.findViewById(R.id.result_img);
            result_info = itemView.findViewById(R.id.result_info);

            MedicineSearch medicineSearch = new MedicineSearch();
            Bundle getBundle = medicineSearch.getArguments();
            String searchName = getBundle.getString("serchName");
            String searchIngredient = getBundle.getString("searchIngredient");
            String searchCompany = getBundle.getString("searchCompany");
            String searchEffect = getBundle.getString("searchEffect");

            //검색 정보로 DB에서 정보 불러와서 SearchResult에 띄움
            RequestTask requestTask = new RequestTask();
            String rtResult = null;
            String tmp = "";

            if(searchName == "" && searchIngredient == "" && searchCompany == "" && searchEffect == "") {
                Toast.makeText(itemView.getContext(), "하나 이상의 값을 입력하세요.", Toast.LENGTH_LONG).show();
            }
            else {
                try {
                    if(searchName != "") tmp += ("medicineName=" + searchName);
                    if(searchIngredient != "") {
                        if(tmp == "") tmp += ("medicineIngredient=" + searchIngredient);
                        else tmp += ("&medicineIngredient=" + searchIngredient);
                    }
                    if(searchCompany != "") {
                        if(tmp == "") tmp += ("company=" + searchCompany);
                        else tmp += ("&company=" + searchCompany);
                    }
                    if (searchEffect != "") {
                        if(tmp == "") tmp += ("effect=" + searchEffect);
                        else tmp += ("&effect=" + searchEffect);
                    }

                    rtResult = requestTask.execute("", tmp).get();

                    JSONObject jsonObject = new JSONObject(rtResult);
                    JSONArray jsonArray = jsonObject.getJSONArray("");
                    for(int i=0 ; i<jsonArray.length() ; i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        result_name.setText(object.getString(""));
                        result_price.setText(object.getString(""));
                        Glide.with(itemView).load(object.getString("")).into(result_img);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            //부가정보 버튼
            result_info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //약 상세 조회로 (약 이름 정보 넘겨줌)
                    Bundle bundle = new Bundle();
                    bundle.putString("result_name", result_name.toString());
                    MedicineInfo medicineInfo = new MedicineInfo();
                    medicineInfo.setArguments(bundle); // 데이터 전달
                }
            });

            this.mAdapter = adapter;
        }

//        void onBind(SearchResultData data) {
//            result_name.setText(data.getResultName());
//            result_price.setText(data.getResultPrice());
//            Glide.with(itemView).load(data.getResultImg()).into(result_img);
//        }
    }
}
