package com.example.genericfinder;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.example.genericfinder.httpConnector.RequestTask;

import org.json.JSONObject;

import java.util.ArrayList;

public class MedicineInfo extends Fragment {

    ImageView mediInfoImg;
    ToggleButton bookmarkBtn;
    Button priceEnrollBtn;
    TextView mediName, mediCompany, mediShape, mediEffect, mediPeriod, mediPackaging, mediIngedient, mediTake, mediCaution, avgPrice;
    BookmarkFragment bookmarkFragment;
    MedicinePriceEnroll medicinePriceEnroll;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView mRecyclerView;
    InfoUserPriceAdapter mAdapter;
    ArrayList<InfoUserPriceData> ipData;
    Bundle getBundle;
    String name;

    public MedicineInfo() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //SearchResult에서 약 이름 받아오기
        getBundle = getArguments();
        name = getBundle.getString("result_name");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_medicine_info, container, false);

        mediInfoImg = view.findViewById(R.id.mediInfoImg);
        mediName = view.findViewById(R.id.mediName);
        mediCompany = view.findViewById(R.id.mediCompany);
        mediIngedient = view.findViewById(R.id.mediIngedient);
        mediTake = view.findViewById(R.id.mediTake);
        mediCaution = view.findViewById(R.id.mediCaution);
        avgPrice = view.findViewById(R.id.avgPrice);
        bookmarkFragment = new BookmarkFragment();

        mediShape = view.findViewById(R.id.mediShape);
        mediEffect = view.findViewById(R.id.mediEffect);
        mediPeriod = view.findViewById(R.id.mediPeriod);
        mediPackaging = view.findViewById(R.id.mediPackaging);

        String url = "http://ip:4321/";

        Context context = view.getContext();
        mRecyclerView = view.findViewById(R.id.mediUserRecyclerv);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new InfoUserPriceAdapter(ipData);
        mRecyclerView.setAdapter(mAdapter);



        //약 이름으로 DB에서 약 정보 들고오기
        RequestTask requestTask = new RequestTask();
        String rtResult = null;

        //BookmarkAdapter에서 약 이름 받아오기
        String bookmarkName = getBundle.getString("bookmarkName");
        String bmResult = null;

        //FilterResultAdapter에서 약 이름 받아오기
        String filterName = getBundle.getString("fr_name");
        String frResult = null;

        try {
            //검색 결과 -> 상세정보
//            rtResult = requestTask.execute("", "medicineName=" + name).get();
//            JSONObject jsonObject = new JSONObject(rtResult);
            rtResult = requestTask.execute(url + "medicineDetailLookup?code=" + "195700020").get();
            rtResult = rtResult.replaceAll("\"", "");
            String[] arr = rtResult.split("/");

            mediName.setText(arr[0]);
            mediCompany.setText(arr[1]);
            mediShape.setText(arr[2] + "장축: " + arr[3] + "단축: " + arr[4]);
            mediIngedient.setText(arr[5]);
            mediEffect.setText(arr[6]);
            mediTake.setText("복용법");
            mediCaution.setText("유의사항");
            avgPrice.setText("지역 평균가");
//            Glide.with(view).load(jsonObject.getString("medicineImage")).into(mediInfoImg);

            //즐겨찾기 -> 상세정보
            bmResult = requestTask.execute("", "medicineName=" + bookmarkName).get();
            JSONObject jObject = new JSONObject(bmResult);

            mediName.setText(jObject.getString("medicineName"));
            mediCompany.setText(jObject.getString("medicineCompany"));
            mediIngedient.setText(jObject.getString("medicineIngredient"));
            mediTake.setText(jObject.getString("medicineTake"));
            mediCaution.setText(jObject.getString("medicineCaution"));
            avgPrice.setText(jObject.getString("avgPrice"));
            Glide.with(view).load(jObject.getString("medicineImage")).into(mediInfoImg);

            //필터결과 -> 상세정보
            frResult = requestTask.execute("", "medicineName=" + filterName).get();
            JSONObject object = new JSONObject(frResult);

            mediName.setText(object.getString("medicineName"));
            mediCompany.setText(object.getString("medicineCompany"));
            mediIngedient.setText(object.getString("medicineIngredient"));
            mediTake.setText(object.getString("medicineTake"));
            mediCaution.setText(object.getString("medicineCaution"));
            avgPrice.setText(object.getString("avgPrice"));
            Glide.with(view).load(object.getString("medicineImage")).into(mediInfoImg);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Bundle bundle = new Bundle();
        bundle.putString("mediName", mediName.toString());

        //즐겨찾기 버튼 토글
        bookmarkBtn = view.findViewById(R.id.bookmarkBtn);
        bookmarkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bookmarkBtn.isChecked()) {
                    bookmarkBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_yellow_star));

                    //즐겨찾기 조회 페이지로 약 이름 넘겨줌
                    bookmarkFragment.setArguments(bundle);
                } else {
                    bookmarkBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_star));
                }
            }
        });

        //약 가격 등록 버튼
        priceEnrollBtn = view.findViewById(R.id.priceEnrollBtn);
        priceEnrollBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //약 이름 enroll에 보내기
                medicinePriceEnroll.setArguments(bundle);
                ((MainActivity)getActivity()).replaceFragment(medicinePriceEnroll);
            }
        });

        return view;
    }
}