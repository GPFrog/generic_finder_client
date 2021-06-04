package com.example.genericfinder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.genericfinder.httpConnector.RequestTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

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
//        holder.onBind(mpUData.get(position));

//        holder.enrollDate.setText(mpUData.get(position).getEnrollDate());
//        holder.pName.setText(mpUData.get(position).getpName());
//        holder.mName.setText(mpUData.get(position).getmName());
//        holder.mPrice.setText(mpUData.get(position).getmPrice());
    }

    @Override
    public int getItemCount() {return mpUData.size();}

    void addItem(MedicinePriceUData data) {mpUData.add(data);}

    class MedicineDeleteUViewHolder extends RecyclerView.ViewHolder {
        TextView enrollDate, pName, mName, mPrice;
        Button deleteBtn;
        MedicinePriceDeleteUAdapter mAdapter;
        SharedPreferences sharedPreferences;

        public MedicineDeleteUViewHolder(View itemView, MedicinePriceDeleteUAdapter adapter) {
            super(itemView);

            enrollDate = itemView.findViewById(R.id.enrollDate);
            pName = itemView.findViewById(R.id.pName);
            mName = itemView.findViewById(R.id.mName);
            mPrice = itemView.findViewById(R.id.mPrice);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);

            RequestTask requestTask = new RequestTask();
            sharedPreferences = itemView.getContext().getSharedPreferences("id", MODE_PRIVATE);
            String id = sharedPreferences.toString();
            
            //아이디로 이메일 들고와서 약 등록 list 띄우기
            String rt = null;

            try {
                rt = requestTask.execute("", "id=" + id).get();
                JSONObject json = new JSONObject(rt);
                JSONArray jsonArray = json.getJSONArray("priceInfoArray");

                for(int i=0 ; i<jsonArray.length() ; i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    enrollDate.setText(object.getString("enrollDate"));
                    pName.setText(object.getString("pharmacyName"));
                    mName.setText(object.getString("medicineName"));
                    mPrice.setText(object.getString("medicinePrice"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            //삭제 버튼
            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder dlg = new AlertDialog.Builder(itemView.getContext());
                    dlg.setTitle("약 가격 삭제");
                    dlg.setMessage("등록된 약 가격을 삭제하시겠습니까?");
                    dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //날짜, 약 이름, 사용자 이메일로 약 가격 삭제
                            //사용자 아이디 보내면 서버에서 이메일 찾아서 해야할듯
                            String rtResult = null;

                            try {
                                rtResult = requestTask.execute("", "enrollDate=" + enrollDate.toString(), "&mName=" + mName.toString(), "&id=" + id).get();
                                JSONObject jsonObject = new JSONObject();
                                
                                if(jsonObject.toString().contains("true")) Toast.makeText(itemView.getContext(), "삭제되었습니다.", Toast.LENGTH_SHORT).show();
                                else Toast.makeText(itemView.getContext(), "삭제에 실패했습니다.", Toast.LENGTH_LONG).show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

                    dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            Toast.makeText(itemView.getContext(), "취소되었습니다.", Toast.LENGTH_SHORT).show();
                        }
                    });
                    dlg.show();
                }
            });

            this.mAdapter = adapter;
        }

//        void onBind(MedicinePriceUData data) {
//            enrollDate.setText(data.getEnrollDate());
//            pName.setText(data.getpName());
//            mName.setText(data.getmName());
//            mPrice.setText(data.getmPrice());
//        }
    }
}
