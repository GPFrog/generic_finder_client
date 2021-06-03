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

import com.bumptech.glide.Glide;
import com.example.genericfinder.httpConnector.RequestTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class FilterResultAdapter extends RecyclerView.Adapter<FilterResultAdapter.filterResultViewHolder> {
    ArrayList<FilterResultData> frData = new ArrayList<>();
    LayoutInflater mInflater;
    Context context;

    public FilterResultAdapter(Context context) {mInflater = LayoutInflater.from(context);}

    public FilterResultAdapter(ArrayList<FilterResultData> frData) {this.frData = frData;}

    @NonNull
    @Override
    public FilterResultAdapter.filterResultViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View mItemView = mInflater.inflate(R.layout.list_filter_result, viewGroup, false);
        return new filterResultViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull FilterResultAdapter.filterResultViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() { return null != frData ? frData.size() : 0; }

    void addItem(FilterResultData data) {frData.add(data);}

    class filterResultViewHolder extends RecyclerView.ViewHolder {
        public TextView fr_name, fr_price;
        public ImageView fr_img;
        public Button fr_info;
        FilterResultAdapter mAdapter;

        public filterResultViewHolder(View itemView, FilterResultAdapter adapter) {
            super(itemView);

            fr_name = itemView.findViewById(R.id.fr_name);
            fr_price = itemView.findViewById(R.id.fr_price);
            fr_img = itemView.findViewById(R.id.fr_img);

            //제조사, 증상으로 DB에서 약 정보 들고오기
            FilterPopup filterPopup = new FilterPopup();
            Bundle getBundle = filterPopup.getArguments();
            String[] companyChecked = getBundle.getStringArray("companyChecked");
            String[] symptomChecked = getBundle.getStringArray("symptomChecked");

            RequestTask requestTask = new RequestTask();
            String rtResult = null;
            String ctmp = "";
            String stmp = "";

            try {
                for(int i=0 ; i<companyChecked.length ; i++) {
                    if(i == 0) ctmp += companyChecked[i];
                    else ctmp += ("/" + companyChecked[i]);
                }
                for(int i=0 ; i<symptomChecked.length ; i++) {
                    if(i == 0) stmp += symptomChecked[i];
                    else stmp += ("/" + symptomChecked[i]);
                }

                rtResult = requestTask.execute("", "companyChecked=" + ctmp + "&symptomChecked=" + stmp).get();

                JSONObject jsonObject = new JSONObject(rtResult);
                JSONArray jsonArray = jsonObject.getJSONArray("");

                for(int i=0 ; i<jsonArray.length() ; i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    fr_name.setText(object.getString(""));
                    fr_price.setText(object.getString(""));
                    Glide.with(itemView).load(object.getString("")).into(fr_img);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            fr_info = itemView.findViewById(R.id.fr_info);
            fr_info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //약 상세 조회로
                    Bundle bundle = new Bundle();
                    bundle.putString("fr_name", fr_name.toString());
                    MedicineInfo medicineInfo = new MedicineInfo();
                    medicineInfo.setArguments(bundle);
                }
            });

            this.mAdapter = adapter;
        }
    }
}
