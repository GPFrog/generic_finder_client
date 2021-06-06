package com.example.genericfinder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.genericfinder.httpConnector.RequestTask;

import org.json.JSONObject;

import java.util.ArrayList;

public class InfoUserPriceAdapter extends RecyclerView.Adapter<InfoUserPriceAdapter.userPriceViewHolder> {
    ArrayList<InfoUserPriceData> ipData = new ArrayList<>();
    LayoutInflater mInflater;
    Context context;

    public InfoUserPriceAdapter(Context context) {mInflater = LayoutInflater.from(context);}

    public InfoUserPriceAdapter(ArrayList<InfoUserPriceData> data) {this.ipData = data;}

    @NonNull
    @Override
    public InfoUserPriceAdapter.userPriceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View mItemVIew = mInflater.inflate(R.layout.list_info_users_price, viewGroup, false);
        return new userPriceViewHolder(mItemVIew, this);
    }

    @Override
    public void onBindViewHolder(@NonNull InfoUserPriceAdapter.userPriceViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {return ipData.size();}

    void addItem(InfoUserPriceData data) {ipData.add(data);}

    class userPriceViewHolder extends RecyclerView.ViewHolder {
        public TextView pharmacyName, usersPrice, userEmail;
        InfoUserPriceAdapter mAdapter;

        public userPriceViewHolder(View itemView, InfoUserPriceAdapter adapter) {
            super(itemView);

            pharmacyName = itemView.findViewById(R.id.pharmacyName);
            usersPrice = itemView.findViewById(R.id.usersPrice);
            userEmail = itemView.findViewById(R.id.userEmail);

            //약 이름으로 사용자가 등록한 가격들 받아오기
            MedicineInfo medicineInfo = new MedicineInfo();
            String medicineName = medicineInfo.mediName.toString();

            RequestTask requestTask = new RequestTask();
            String rtResult = null;

            try {
                rtResult = requestTask.execute("", "medicineName=" + medicineName).get();
                JSONObject jsonObject = new JSONObject(rtResult);

                pharmacyName.setText(jsonObject.getString("pharmacyName"));
                usersPrice.setText(jsonObject.getString("usersPrice"));
                userEmail.setText(jsonObject.getString("userEmail"));
            } catch (Exception e) {
                e.printStackTrace();
            }

            this.mAdapter = adapter;
        }

        void onBind(InfoUserPriceData data) {
            pharmacyName.setText(data.getPharmacyName());
            usersPrice.setText(data.getUsersPrice());
            userEmail.setText(data.getUserEmail());
        }
    }
}
