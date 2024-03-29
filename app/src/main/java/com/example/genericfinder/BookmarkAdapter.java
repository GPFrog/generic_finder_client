package com.example.genericfinder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import static android.content.Context.MODE_PRIVATE;

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.bookmarkViewHolder> {
    ArrayList<BookmarkData> bookmarkData = new ArrayList<>();
    LayoutInflater mInflater;
    Context mContext;

    String bName;

    private static final String TAG_NAME = "name";
//    private static final String TAG_LOCATION = "location";
//    private static final String TAG_PRICE = "price";

    List<Map<String, Object>> dialogItemList;

    public BookmarkAdapter(Context context) {mInflater = LayoutInflater.from(context);}

    public BookmarkAdapter(Context context, ArrayList<BookmarkData> bookmarkData) {
        mInflater = LayoutInflater.from(context);
        this.bookmarkData = bookmarkData;
        SharedPreferences sharedPreferences = context.getSharedPreferences("bookmark", context.MODE_PRIVATE);
        bName = sharedPreferences.getString("bookmark", "");


//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        System.out.println(sharedPreferences.getAll());
    }

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
        public Button bm_infoBtn;
        BookmarkAdapter mAdapter;


        public bookmarkViewHolder(View itemView, BookmarkAdapter adapter) {
            super(itemView);
            
            bookmarkName = itemView.findViewById(R.id.bookmarkName);
            bm_infoBtn = itemView.findViewById(R.id.bm_infoBtn);



            //상세보기에서 약 이름 받아옴
//            MedicineInfoActivity medicineInfo = new MedicineInfoActivity();
//            Bundle getBundle = ;
//            String mediName = getBundle.getString("mediName");
            
            //약 이름으로 DB에서 정보 받아와서 띄워줌
//            RequestTask requestTask = new RequestTask();
//            String rtResult = null;
//
//            try {
////                rtResult = requestTask.execute("", "mediName=" + mediName).get();
//                JSONObject jsonObject = new JSONObject(rtResult);
//
//                bookmarkName.setText(jsonObject.getString("medicineName"));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

            //상세보기 버튼클릭이벤트
            bm_infoBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    //MedicineInfo 페이지에 약 이름 넘겨주기(?) -> 약 이름으로 해당 약 정보 띄우기
//                    Bundle bundle = new Bundle();
//                    bundle.putString("bookmarkName", bookmarkName.toString());
//                    medicineInfo.setArguments(bundle);
                }
            });

            this.mAdapter = adapter;
        }

        void onBind(BookmarkData data) {
            bookmarkName.setText(data.getBookmarkName());
        }
    }
}
