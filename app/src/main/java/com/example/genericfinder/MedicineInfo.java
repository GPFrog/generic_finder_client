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

            mediName.setText(jsonObject.getString(""));
            mediCompany.setText(jsonObject.getString(""));
            mediIngedient.setText(jsonObject.getString(""));
            mediTake.setText(jsonObject.getString(""));
            mediCaution.setText(jsonObject.getString(""));
            mediCaution.setText(jsonObject.getString(""));
            avgPrice.setText(jsonObject.getString(""));
            Glide.with(view).load(jsonObject.getString("")).into(mediInfoImg);

            //즐겨찾기 -> 상세정보
            bmResult = requestTask.execute("", "medicineName=" + bookmarkName).get();
            JSONObject jObject = new JSONObject(bmResult);

            mediName.setText(jObject.getString(""));
            mediCompany.setText(jObject.getString(""));
            mediIngedient.setText(jObject.getString(""));
            mediTake.setText(jObject.getString(""));
            mediCaution.setText(jObject.getString(""));
            mediCaution.setText(jObject.getString(""));
            avgPrice.setText(jObject.getString(""));
            Glide.with(view).load(jObject.getString("")).into(mediInfoImg);

            //필터결과 -> 상세정보
            frResult = requestTask.execute("", "medicineName=" + filterName).get();
            JSONObject object = new JSONObject(frResult);

            mediName.setText(object.getString(""));
            mediCompany.setText(object.getString(""));
            mediIngedient.setText(object.getString(""));
            mediTake.setText(object.getString(""));
            mediCaution.setText(object.getString(""));
            mediCaution.setText(object.getString(""));
            avgPrice.setText(object.getString(""));
            Glide.with(view).load(object.getString("")).into(mediInfoImg);
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