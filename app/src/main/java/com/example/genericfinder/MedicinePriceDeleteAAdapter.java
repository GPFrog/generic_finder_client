package com.example.genericfinder;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.genericfinder.httpConnector.RequestTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MedicinePriceDeleteAAdapter extends RecyclerView.Adapter<MedicinePriceDeleteAAdapter.MediPriceDeleteViewHolder> {
    ArrayList<MedicinePriceData> mpData = new ArrayList<>();
    ArrayList<MedicinePriceData> filterlist;
    LayoutInflater mInflater;
    Context mContext;
    Fragment MedicinePriceDeleteA;

    public MedicinePriceDeleteAAdapter(Context context) {mInflater = LayoutInflater.from(context);}

    public MedicinePriceDeleteAAdapter(ArrayList<MedicinePriceData> mpData) {this.mpData = mpData;}

    public MedicinePriceDeleteAAdapter(Context context, ArrayList<MedicinePriceData> mpData) {
        this.mpData = mpData;
        this.filterlist = mpData;
    }

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

//    @Override
//    public Filter getFilter() {
//        return exampleFilter;
//    }

//    private Filter exampleFilter = new Filter() {
//        @Override
//        protected FilterResults performFiltering(CharSequence constraint) {
//            List<MedicinePriceData> filtering = new ArrayList<>();
//
//            if(constraint == null || constraint.length() == 0) filtering.addAll(mpData);
//            else {
//                String filterPattern = constraint.toString().toLowerCase().trim();
//
//                for(MedicinePriceData item : mpData) {
//                    if(item.getmName().toLowerCase().contains(filterPattern)) filterlist.add(item);
//                }
//            }
//
//            FilterResults results = new FilterResults();
//            results.values = filterlist;
//            return results;
//        }
//
//        @Override
//        protected void publishResults(CharSequence constraint, FilterResults results) {
//            mpData.clear();
//            mpData.addAll((List)results.values);
//            notifyDataSetChanged();
//        }
//    };

    class MediPriceDeleteViewHolder extends RecyclerView.ViewHolder {
        TextView enrollDate, pName, mName, mPrice, userEmail;
        Button deleteBtn;
        MedicinePriceDeleteAAdapter mAdapter;
        Fragment BlackListEnrollPopup;
        MainActivity mainActivity;

        public MediPriceDeleteViewHolder(View itemView, MedicinePriceDeleteAAdapter adapter) {
            super(itemView);

            enrollDate = itemView.findViewById(R.id.enrollDate);
            pName = itemView.findViewById(R.id.pName);
            mName = itemView.findViewById(R.id.mName);
            mPrice = itemView.findViewById(R.id.mPrice);
            userEmail = itemView.findViewById(R.id.userEmail);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);
            BlackListEnrollPopup = new BlackListEnrollPopup();
            mainActivity = new MainActivity();

            //전체 약 가격 list 가져오기
            try {
                JSONObject jsonObject = new JSONObject();
                JSONArray jsonArray = jsonObject.getJSONArray("priceInfoArray");

                for(int i=0 ; i<jsonArray.length() ; i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    enrollDate.setText(object.getString("enrollDate"));
                    pName.setText(object.getString("pharmacyName"));
                    mName.setText(object.getString("medicineName"));
                    userEmail.setText(object.getString("userEmail"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            //삭제 버튼
            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //날짜, 약 이름, 사용자 이메일으로 삭제
                    RequestTask requestTask = new RequestTask();
                    String rtResult = null;

                    try {
                        rtResult = requestTask.execute("", "enrollDate=" + enrollDate.toString(), "&mName=" + mName.toString() + "&userEmail=" + userEmail.toString()).get();
                        JSONObject jObject = new JSONObject(rtResult);

                        if(jObject.toString().contains("true")) Toast.makeText(itemView.getContext(), "삭제되었습니다.", Toast.LENGTH_SHORT).show();
                        else Toast.makeText(itemView.getContext(), "삭제에 실패했습니다.", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            //블랙리스트 등록 팝업
            Bundle bundle = new Bundle();
            bundle.putString("userEmail", userEmail.toString());
            mainActivity.replaceFragment(BlackListEnrollPopup);
//            ((MainActivity).getActivity()).replaceFragment(BlackListEnrollPopup);
            BlackListEnrollPopup.setArguments(bundle);

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
