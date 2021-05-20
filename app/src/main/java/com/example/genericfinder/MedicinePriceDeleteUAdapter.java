package com.example.genericfinder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MedicinePriceDeleteUAdapter extends RecyclerView.Adapter<MedicinePriceDeleteUAdapter.MedicineDeleteUViewHolder> {
    ArrayList<MedicinePriceUData> mpUData = new ArrayList<>();
    LayoutInflater mInflater;
    Context context;

    public MedicinePriceDeleteUAdapter(Context context) {mInflater = LayoutInflater.from(context);}

    public MedicinePriceDeleteUAdapter(ArrayList<MedicinePriceUData> mpUData) {this.mpUData = mpUData;}

    @NonNull
    @Override
    public MedicinePriceDeleteUAdapter.MedicineDeleteUViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View mItemView = mInflater.inflate(R.layout.list_price_u, viewGroup, false);
        return new MedicineDeleteUViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicinePriceDeleteUAdapter.MedicineDeleteUViewHolder holder, int position) {
        holder.onBind(mpUData.get(position));

        holder.enrollDate.setText(mpUData.get(position).getEnrollDate());
        holder.pName.setText(mpUData.get(position).getpName());
        holder.mName.setText(mpUData.get(position).getmName());
        holder.mPrice.setText(mpUData.get(position).getmPrice());
    }

    @Override
    public int getItemCount() {return mpUData.size();}

    void addItem(MedicinePriceUData data) {mpUData.add(data);}

    class MedicineDeleteUViewHolder extends RecyclerView.ViewHolder {
        TextView enrollDate, pName, mName, mPrice;
        Button deleteBtn;
        MedicinePriceDeleteUAdapter mAdapter;


        public MedicineDeleteUViewHolder(View itemView, MedicinePriceDeleteUAdapter adapter) {
            super(itemView);

            enrollDate = itemView.findViewById(R.id.enrollDate);
            pName = itemView.findViewById(R.id.pName);
            mName = itemView.findViewById(R.id.mName);
            mPrice = itemView.findViewById(R.id.mPrice);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);

            //삭제 버튼
            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            this.mAdapter = adapter;
        }

        void onBind(MedicinePriceUData data) {
            enrollDate.setText(data.getEnrollDate());
            pName.setText(data.getpName());
            mName.setText(data.getmName());
            mPrice.setText(data.getmPrice());
        }
    }
}
