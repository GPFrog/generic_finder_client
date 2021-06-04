package com.example.genericfinder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.genericfinder.httpConnector.RequestTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.bookmarkViewHolder> {
    ArrayList<BookmarkData> bookmarkData = new ArrayList<>();
    LayoutInflater mInflater;
    Context mContext;
    private static final String TAG_NAME = "name";
    private static final String TAG_LOCATION = "location";
    private static final String TAG_PRICE = "price";

    List<Map<String, Object>> dialogItemList;

    public BookmarkAdapter(Context context) {mInflater = LayoutInflater.from(context);}

    public BookmarkAdapter(ArrayList<BookmarkData> bookmarkData) {this.bookmarkData = bookmarkData;}

    @NonNull
    @Override
    public BookmarkAdapter.bookmarkViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View mItemView = mInflater.inflate(R.layout.list_bookmark, viewGroup, false);
        return new bookmarkViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarkAdapter.bookmarkViewHolder holder, int position) {
        holder.onBind(bookmarkData.get(position));

//        holder.bookmarkImg.setImageResource(bookmarkData.get(position).getBookmarkImg());
//        holder.bookmarkName.setText(bookmarkData.get(position).getBookmarkName());
    }

    @Override
    public int getItemCount() {return bookmarkData.size();}

    void addItem(BookmarkData data) {bookmarkData.add(data);}

    class bookmarkViewHolder extends RecyclerView.ViewHolder {
        public TextView bookmarkName;
        public ImageView bookmarkImg;
        public Button bm_priceBtn, bm_infoBtn;
        BookmarkAdapter mAdapter;

        public bookmarkViewHolder(View itemView, BookmarkAdapter adapter) {
            super(itemView);
            
            bookmarkName = itemView.findViewById(R.id.bookmarkName);
            bookmarkImg = itemView.findViewById(R.id.bookmarkImg);
            bm_priceBtn = itemView.findViewById(R.id.bm_priceBtn);
            bm_infoBtn = itemView.findViewById(R.id.bm_infoBtn);

            //상세보기에서 약 이름 받아옴
            MedicineInfo medicineInfo = new MedicineInfo();
            Bundle getBundle = medicineInfo.getArguments();
            String mediName = getBundle.getString("mediName");
            
            //약 이름으로 DB에서 정보 받아와서 띄워줌
            RequestTask requestTask = new RequestTask();
            String rtResult = null;

            try {
                rtResult = requestTask.execute("", "mediName=" + mediName).get();
                JSONObject jsonObject = new JSONObject(rtResult);

                bookmarkName.setText(jsonObject.getString("medicineName"));
                Glide.with(itemView).load(jsonObject.getString("medicineImage")).into(bookmarkImg);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //가격정보 버튼클릭이벤트
            bm_priceBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    //약 이름으로 DB에서 약 코드 가지고 해당 약 등록되어있는 주변 약국 띄워주기
                    String rqResult = null;

                    try {
                        rqResult = requestTask.execute("", "mediName=" + mediName).get();
                        JSONObject jObject = new JSONObject(rqResult);
                        JSONArray jsonArray = jObject.getJSONArray("medicineInformation");
                        int size = jsonArray.length();

                        String[] pName = new String[size];
                        String[] pLocation = new String[size];
                        String[] price = new String[size];

                        for(int i=0 ; i<size ; i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            pName[i] = object.getString("pharmacyName");
                            pLocation[i] = object.getString("pharmacyLocation");
                            price[i] = object.getString("price");
                        }

                        AlertDialog.Builder dlg = new AlertDialog.Builder(itemView.getContext());
                        View popView = mInflater.inflate(R.layout.priceinfo_plist_popup, null);
                        dlg.setView(popView);

                        ListView listView = (ListView)itemView.findViewById(R.id.plist);
                        AlertDialog dialog = dlg.create();

                        SimpleAdapter simpleAdapter = new SimpleAdapter(itemView.getContext(), dialogItemList, R.layout.list_priceinfo,
                                new String[]{TAG_NAME, TAG_LOCATION, TAG_PRICE},
                                new int[]{R.id.plistName, R.id.plistLocation, R.id.plistPrice});

                        listView.setAdapter(simpleAdapter);
                        //시간 나면 클릭 이벤트로 지도
//                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                            @Override
//                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                            }
//                        });

                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialog.setTitle(mediName + " 가격 정보");
                        dialog.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            //상세보기 버튼클릭이벤트
            bm_infoBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    //MedicineInfo 페이지에 약 이름 넘겨주기(?) -> 약 이름으로 해당 약 정보 띄우기
                    Bundle bundle = new Bundle();
                    bundle.putString("bookmarkName", bookmarkName.toString());
                    medicineInfo.setArguments(bundle);
                }
            });

            this.mAdapter = adapter;
        }

        void onBind(BookmarkData data) {
            Glide.with(itemView).load(data.getBookmarkImg()).into(bookmarkImg);
            bookmarkName.setText(data.getBookmarkName());
        }
    }
}
