package com.example.genericfinder;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.genericfinder.httpConnector.RequestTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class SymptomCheckAdapter extends RecyclerView.Adapter<SymptomCheckAdapter.SymptomCheckViewHolder> {
    ArrayList<SymptomCheckData> scData = new ArrayList<>();
    LayoutInflater mInflater;
    Context mContext;
    MediSearchResult mediSearchResult;
    int size = 0;
    String[] symptomChecked;

    public SymptomCheckAdapter(Context context) {mInflater = LayoutInflater.from(context);}

    public SymptomCheckAdapter(ArrayList<SymptomCheckData> scData) {this.scData = scData;}

    public SymptomCheckAdapter() { }

    @NonNull
    @Override
    public SymptomCheckAdapter.SymptomCheckViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View mItemView = mInflater.inflate(R.layout.list_symptom_check, viewGroup, false);
        return new SymptomCheckViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull SymptomCheckAdapter.SymptomCheckViewHolder holder, int position) {
        holder.onBind(scData.get(position));

        holder.symptom.setText(scData.get(position).getSymptom());
    }

    @Override
    public int getItemCount() {return scData.size();}

    void addItem(SymptomCheckData data) {scData.add(data);}

    class SymptomCheckViewHolder extends RecyclerView.ViewHolder {
        public CheckBox symptom;
        SymptomCheckAdapter mAdapter;
        
        public SymptomCheckViewHolder(View itemView, SymptomCheckAdapter adapter) {
            super(itemView);

            symptom = itemView.findViewById(R.id.symptom);
            mediSearchResult = new MediSearchResult();

            //검색 정보 받아와서 정보에 따라 체크리스트 띄워주기
            Bundle getBundle = mediSearchResult.getArguments();
            String searchName = getBundle.getString("serchName");
            String searchIngredient = getBundle.getString("searchIngredient");
            String searchCompany = getBundle.getString("searchCompany");
            String searchEffect = getBundle.getString("searchEffect");

            RequestTask requestTask = new RequestTask();
            String rtResult = null;
            String tmp = "";

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
                JSONArray jsonArray = jsonObject.getJSONArray("symptomArray");
                for(int i=0 ; i<jsonArray.length() ; i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    symptom.setText(object.getString("symptom"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            //증상 체크 시
            if(symptom.isChecked()) {
                String symptomName = symptom.getText().toString();
                symptomChecked[++size] = symptomName;
            }

            this.mAdapter = adapter;
        }

        void onBind(SymptomCheckData data) {
            symptom.setText(data.getSymptom());
        }
    }

    public String[] getCheckedSymptom() {
        return symptomChecked;
    }
}
