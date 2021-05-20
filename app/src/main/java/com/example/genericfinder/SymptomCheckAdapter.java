package com.example.genericfinder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SymptomCheckAdapter extends RecyclerView.Adapter<SymptomCheckAdapter.SymptomCheckViewHolder> {
    ArrayList<SymptomCheckData> scData = new ArrayList<>();
    LayoutInflater mInflater;
    Context mContext;

    public SymptomCheckAdapter(Context context) {mInflater = LayoutInflater.from(context);}

    public SymptomCheckAdapter(ArrayList<SymptomCheckData> scData) {this.scData = scData;}

    @NonNull
    @Override
    public SymptomCheckAdapter.SymptomCheckViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View mItemView = mInflater.inflate(R.layout.list_symptom_check, viewGroup, false);
        return new SymptomCheckViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull SymptomCheckAdapter.SymptomCheckViewHolder holder, int position) {
        holder.onBind(scData.get(position));

        holder.symptop1.setText(scData.get(position).getSymptom1());
        holder.symptop2.setText(scData.get(position).getSymptom2());
    }

    @Override
    public int getItemCount() {return scData.size();}

    void addItem(SymptomCheckData data) {scData.add(data);}

    class SymptomCheckViewHolder extends RecyclerView.ViewHolder {
        public CheckBox symptop1, symptop2;
        SymptomCheckAdapter mAdapter;
        
        public SymptomCheckViewHolder(View itemView, SymptomCheckAdapter adapter) {
            super(itemView);

            symptop1 = itemView.findViewById(R.id.symptop1);
            symptop2 = itemView.findViewById(R.id.symptop2);

            //증상 체크 시
            if(symptop1.isChecked()) {
                
            }
            
            if(symptop2.isChecked()) {

            }

            this.mAdapter = adapter;
        }

        void onBind(SymptomCheckData data) {
            symptop1.setText(data.getSymptom1());
            symptop2.setText(data.getSymptom2());
        }
    }
}
