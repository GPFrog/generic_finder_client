package com.example.genericfinder;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.example.genericfinder.httpConnector.RequestTask;

import org.json.JSONObject;

public class MedicineInfo extends Fragment {

    ImageView mediInfoImg;
    ToggleButton bookmarkBtn;
    TextView mediName, mediCompany, mediIngedient, mediTake, mediCaution, avgPrice;
    BookmarkFragment bookmarkFragment;

    public MedicineInfo() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        //SearchResult에서 약 이름 받아오기
        Bundle bundle = getArguments();
        String medicineName = bundle.getString("result_name");

        //약 이름으로 DB에서 약 정보 들고오기
        RequestTask requestTask = new RequestTask();
        String rtResult = null;

        //BookmarkAdapter에서 약 이름 받아오기
        String bookmarkName = bundle.getString("bookmarkName");
        String bmResult = null;

        //FilterResultAdapter에서 약 이름 받아오기
        String filterName = bundle.getString("fr_name");
        String frResult = null;

        try {
            //검색 결과 -> 상세정보
            rtResult = requestTask.execute("", "medicineName=" + medicineName).get();
            JSONObject jsonObject = new JSONObject(rtResult);

            mediName.setText(jsonObject.getString("medicineName"));
            mediCompany.setText(jsonObject.getString("medicineCompany"));
            mediIngedient.setText(jsonObject.getString("medicineIngredient"));
            mediTake.setText(jsonObject.getString("medicineTake"));
            mediCaution.setText(jsonObject.getString("medicineCaution"));
            avgPrice.setText(jsonObject.getString("avgPrice"));
            Glide.with(view).load(jsonObject.getString("medicineImage")).into(mediInfoImg);

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

        //즐겨찾기 버튼 토글
        bookmarkBtn = view.findViewById(R.id.bookmarkBtn);
        bookmarkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bookmarkBtn.isChecked()) {
                    bookmarkBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_yellow_star));

                    //즐겨찾기 조회 페이지로 약 이름 넘겨줌
                    Bundle bundle = new Bundle();
                    bundle.putString("mediName", mediName.toString());
                    bookmarkFragment.setArguments(bundle);
                } else {
                    bookmarkBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_star));
                }
            }
        });
        return view;
    }
}