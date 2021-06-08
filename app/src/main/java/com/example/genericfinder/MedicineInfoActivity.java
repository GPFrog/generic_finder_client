package com.example.genericfinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.example.genericfinder.httpConnector.RequestTask;

import java.util.ArrayList;

public class MedicineInfoActivity extends AppCompatActivity {

    ImageView mediInfoImg;
    ToggleButton bookmarkBtn;
    Button priceEnrollBtn;
    TextView mediName, mediCompany, mediShape, mediEffect, mediPeriod, mediPackaging, mediIngedient, mediTake, mediCaution, avgPrice;
    BookmarkFragment bookmarkFragment;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView mRecyclerView;
//    InfoUserPriceAdapter mAdapter;
//    ArrayList<InfoUserPriceData> userData;
//    InfoUserPriceData data;
//    Bundle getBundle;
//    String name;
    MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_info);

        mediInfoImg = findViewById(R.id.mediInfoImg);
        mediName = findViewById(R.id.mediName);
        mediCompany = findViewById(R.id.mediCompany);
        mediIngedient = findViewById(R.id.mediIngedient);
        mediTake = findViewById(R.id.mediTake);
        mediCaution = findViewById(R.id.mediCaution);
        avgPrice = findViewById(R.id.avgPrice);
        bookmarkFragment = new BookmarkFragment();
        mainActivity = new MainActivity();

        mediShape = findViewById(R.id.mediShape);
        mediEffect = findViewById(R.id.mediEffect);
        mediPeriod = findViewById(R.id.mediPeriod);
        mediPackaging = findViewById(R.id.mediPackaging);

//        userData = new ArrayList<>();

        String url = "http://152.70.89.118:4321/medicineDetailLookup?code=";

//        mRecyclerView = findViewById(R.id.mediUseRecyclerv);
//        mRecyclerView.setHasFixedSize(true);
//
//        mLayoutManager = new LinearLayoutManager(this);
//        mRecyclerView.setLayoutManager(mLayoutManager);
//
//        mAdapter = new InfoUserPriceAdapter(ipData);
//        mRecyclerView.setAdapter(mAdapter);



        //약 이름으로 DB에서 약 정보 들고오기
        RequestTask requestTask = new RequestTask();
        String rtResult;

//        String result;
//        mAdapter = new InfoUserPriceAdapter(userData);
//        mRecyclerView.setAdapter(mAdapter);
//        System.out.println("3번");
//        data = new InfoUserPriceData();

        try {
            rtResult = requestTask.execute(url + "195700020").get();
            rtResult = rtResult.replaceAll("\"", "");
            String[] arr = rtResult.split("\\^\\^");

            mediName.setText(arr[0]);
            mediCompany.setText(arr[1]);
            mediShape.setText(arr[2] + "장축: " + arr[3] + "단축: " + arr[4]);
            mediIngedient.setText(arr[5]);
            mediEffect.setText(arr[6]);
            mediTake.setText(arr[7]);
            mediCaution.setText(arr[8]);
            mediPeriod.setText(arr[9]);
            mediPackaging.setText(arr[10]);
            avgPrice.setText(arr[11]);
            Glide.with(this).load(arr[12]).into(mediInfoImg);


//            result = requestTask.execute(url + "").get();
//            result = result.replaceAll("\\[", "");
//            result = result.replaceAll("]", "");
//            result = result.replaceAll("\"", "");
//            String[] ar = result.split(",");
//            data = new InfoUserPriceData();
//
//            for (int i = 0; i < arr.length-1 ; i++) {
//                if ((i % 4) == 0) {
//                    data.setDate(arr[i]);
//                } else if ((i % 4) == 1) {
//                    data.setPharmacyName(arr[i]);
//                } else if ((i % 4) == 2) {
//                    data.setUserEmail(arr[i]);
//                } else if ((i % 4) == 3) {
//                    data.setUsersPrice(arr[i]);
//                    mAdapter.addItem(data);
//                    data = new InfoUserPriceData();
//                }
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //즐겨찾기 버튼 토글
        bookmarkBtn = findViewById(R.id.bookmarkBtn);
        bookmarkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("bookmark", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                if (bookmarkBtn.isChecked()) {
                    bookmarkBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_yellow_star));
                    if(sharedPreferences.getString("bookmark", "").compareTo("") == 0) {
                        editor.putString("bookmark", mediName.getText().toString() + ",");
                    }else {
                        String value = sharedPreferences.getString("bookmark", "");
                        value += mediName.getText().toString() + ",";
                        editor.putString("bookmark", value);
                    }
                    editor.commit();
                    System.out.println(sharedPreferences.getAll());
//                    Bundle bundle = new Bundle();
//                    bundle.putString("mediName", mediName.toString());

                    //즐겨찾기 조회 페이지로 약 이름 넘겨줌
//                    bookmarkFragment.setArguments(bundle);
                } else {
//                    bookmarkBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_star));
//                    if(sharedPreferences.getString("bookmark", "").compareTo("") != 0) {
//                        String value = sharedPreferences.getString("bookmark", "");
//                        value = value.replaceAll(mediName.getText().toString() + ",", "");
//                        editor.putString("bookmark", value);
//                    }else {
//
//                    }
//                    editor.commit();
//                    System.out.println(sharedPreferences.getAll());
                }
            }
        });

        Context context = this;

        //약 가격 등록 버튼
        priceEnrollBtn = findViewById(R.id.priceEnrollBtn);
        priceEnrollBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //약 이름 enroll에 보내기
                Intent intent = new Intent(context, MedicinePriceEnrollActivity.class);
                intent.putExtra("enrollName", mediName.getText().toString());
                startActivity(intent);

//                medicinePriceEnroll = new MedicinePriceEnroll();
//                System.out.println(medicinePriceEnroll.getActivity());
//                Bundle enrollBundle = new Bundle();
//                enrollBundle.putString("enrollName", "enrollName");
//                medicinePriceEnroll.setArguments(enrollBundle);
//                mainActivity.replaceFragment(medicinePriceEnroll);
//                getSupportFragmentManager().beginTransaction().replace(R.id.container, medicinePriceEnroll).commit();
            }
        });

    }
}