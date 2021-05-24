package com.example.genericfinder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CompanyCheckAdapter extends RecyclerView.Adapter<CompanyCheckAdapter.CompanyCheckViewHolder> {
    ArrayList<CompanyCheckData> ccData = new ArrayList<>();
    LayoutInflater mInflater;
    Context mContext;

    public CompanyCheckAdapter(Context context) {mInflater = LayoutInflater.from(context);}

    public CompanyCheckAdapter(ArrayList<CompanyCheckData> ccData) {this.ccData = ccData;}

    @NonNull
    @Override
    public CompanyCheckAdapter.CompanyCheckViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View mItemView = mInflater.inflate(R.layout.list_company_check, viewGroup, false);
        return new CompanyCheckViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull CompanyCheckAdapter.CompanyCheckViewHolder holder, int position) {
        holder.onBind(ccData.get(position));

        holder.company1.setText(ccData.get(position).getCompany1());
        holder.company2.setText(ccData.get(position).getCompany2());
    }

    @Override
    public int getItemCount() {return ccData.size();}

    void addItem(CompanyCheckData data) {ccData.add(data);}

    class CompanyCheckViewHolder extends RecyclerView.ViewHolder {
        public CheckBox company1, company2;
        CompanyCheckAdapter mAdapter;

        public CompanyCheckViewHolder(@NonNull View itemView, CompanyCheckAdapter adapter) {
            super(itemView);

            company1 = itemView.findViewById(R.id.company1);
            company2 = itemView.findViewById(R.id.company2);
            
            //제조사 체크 시
            if(company1.isChecked()) {

            }

            if(company2.isChecked()) {

            }

            this.mAdapter = adapter;
        }

        void onBind(CompanyCheckData data) {
            company1.setText(data.getCompany1());
            company2.setText(data.getCompany2());
        }
    }
}
