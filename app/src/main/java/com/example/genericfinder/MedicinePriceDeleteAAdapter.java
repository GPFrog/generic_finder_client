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

public class MedicinePriceDeleteAAdapter extends RecyclerView.Adapter<MedicinePriceDeleteAAdapter.MediPriceDeleteViewHolder> {
    ArrayList<MedicinePriceData> mpData = new ArrayList<>();
    LayoutInflater mInflater;
    Context mContext;

    public MedicinePriceDeleteAAdapter(Context context) {mInflater = LayoutInflater.from(context);}

    public MedicinePriceDeleteAAdapter(ArrayList<MedicinePriceData> mpData) {this.mpData = mpData;}

    @NonNull
    @Override
    public MedicinePriceDeleteAAdapter.MediPriceDeleteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View mItemView = mInflater.inflate(R.layout.list_price_a, viewGroup, false);
        return new MediPriceDeleteViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicinePriceDeleteAAdapter.MediPriceDeleteViewHolder holder, int position) {
        holder.onBind(mpData.get(position));

        holder.enrollDate.setText(mpData.get(position).getEnrollDate());
        holder.pName.setText(mpData.get(position).getpName());
        holder.mName.setText(mpData.get(position).getmName());
        holder.mPrice.setText(mpData.get(position).getmPrice());
        holder.userEmail.setText(mpData.get(position).getUserEmail());
    }

    @Override
    public int getItemCount() {return mpData.size();}

    void addItem(MedicinePriceData data) {mpData.add(data);}

    class MediPriceDeleteViewHolder extends RecyclerView.ViewHolder {
        TextView enrollDate, pName, mName, mPrice, userEmail;
        Button deleteBtn;
        MedicinePriceDeleteAAdapter mAdapter;

        public MediPriceDeleteViewHolder(View itemView, MedicinePriceDeleteAAdapter adapter) {
            super(itemView);

            enrollDate = itemView.findViewById(R.id.enrollDate);
            pName = itemView.findViewById(R.id.pName);
            mName = itemView.findViewById(R.id.mName);
            mPrice = itemView.findViewById(R.id.mPrice);
            userEmail = itemView.findViewById(R.id.userEmail);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);

            //삭제 버튼
            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    
                }
            });

            this.mAdapter = adapter;
        }

        void onBind(MedicinePriceData data) {
            enrollDate.setText(data.getEnrollDate());
            pName.setText(data.getpName());
            mName.setText(data.getmName());
            mPrice.setText(data.getmPrice());
            userEmail.setText(data.getUserEmail());
        }
    }
}
