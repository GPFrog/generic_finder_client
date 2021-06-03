package com.example.genericfinder;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.genericfinder.httpConnector.RequestTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class CompanyCheckAdapter extends RecyclerView.Adapter<CompanyCheckAdapter.CompanyCheckViewHolder> {
    ArrayList<CompanyCheckData> ccData = new ArrayList<>();
    LayoutInflater mInflater;
    Context mContext;
    MediSearchResult mediSearchResult;
    int size = 0;
    String[] companyChecked;

    public CompanyCheckAdapter(Context context) {mInflater = LayoutInflater.from(context);}

    public CompanyCheckAdapter(ArrayList<CompanyCheckData> ccData) {this.ccData = ccData;}

    public CompanyCheckAdapter() { }

    @NonNull
    @Override
    public CompanyCheckAdapter.CompanyCheckViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View mItemView = mInflater.inflate(R.layout.list_company_check, viewGroup, false);
        return new CompanyCheckViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull CompanyCheckAdapter.CompanyCheckViewHolder holder, int position) {
        holder.onBind(ccData.get(position));

        holder.company.setText(ccData.get(position).getCompany());
    }

    @Override
    public int getItemCount() {return ccData.size();}

    void addItem(CompanyCheckData data) {ccData.add(data);}

    class CompanyCheckViewHolder extends RecyclerView.ViewHolder {
        public CheckBox company;
        CompanyCheckAdapter mAdapter;

        public CompanyCheckViewHolder(@NonNull View itemView, CompanyCheckAdapter adapter) {
            super(itemView);

            company = itemView.findViewById(R.id.company);
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
                JSONArray jsonArray = jsonObject.getJSONArray("");
                for(int i=0 ; i<jsonArray.length() ; i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    company.setText(object.getString(""));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            companyChecked = new String[size];

            //제조사 체크 시
            if(company.isChecked()) {
                String companyName = company.getText().toString();
                companyChecked[++size] = companyName;
            }

            this.mAdapter = adapter;
        }

        void onBind(CompanyCheckData data) {
            company.setText(data.getCompany());
        }
    }

    public String[] getCheckedCompany() {
        return companyChecked;
    }
}
