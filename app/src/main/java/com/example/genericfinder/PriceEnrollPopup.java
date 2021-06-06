package com.example.genericfinder;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.genericfinder.httpConnector.RequestTask;

import org.json.JSONObject;

public class PriceEnrollPopup extends DialogFragment {

    private Fragment fragment;
    Button enrollBtn, enrollCancleBtn;

    public PriceEnrollPopup() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_price_enroll_popup, container, false);

        enrollBtn = view.findViewById(R.id.enrollBtn);
        enrollCancleBtn = view.findViewById(R.id.enrollCancleBtn);

        Bundle getBundle = getArguments();
        String medicineName = getBundle.getString("medicineName");
        String pharmacyNum= getBundle.getString("pharmacyNum");
        String price = getBundle.getString("price");

        enrollBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestTask requestTask = new RequestTask();
                String rtResult = null;
                
                try {
                    rtResult = requestTask.execute("", "medicineName=" + medicineName + "&pharmacyNum=" + pharmacyNum + "&price=" + price).get();
                    JSONObject jsonObject = new JSONObject(rtResult);

                    if(jsonObject.toString().contains("true")) Toast.makeText(view.getContext(), "의약품 가격이 등록되었습니다.", Toast.LENGTH_SHORT).show();
                    else Toast.makeText(view.getContext(), "약 가격 등록에 실패했습니다.", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        enrollCancleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        return view;
    }
}