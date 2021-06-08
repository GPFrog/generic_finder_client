package com.example.genericfinder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.genericfinder.httpConnector.RequestTask;

import java.util.ArrayList;

public class MedicinePriceDeleteU extends Fragment {
    Button cancelBtn;
    Fragment MedicinePriceDeleteU0;

    Bundle getBundle;
    String id;

    private RecyclerView.LayoutManager mLayoutManager;
    RecyclerView mRecyclerView;
    MedicinePriceDeleteUAdapter mAdapter;
    ArrayList<MedicinePriceUData> medicinePriceUData = null;

    MedicinePriceUData data;

    public MedicinePriceDeleteU() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBundle = getArguments();
        id = getBundle.getString("id");

        //될까
        System.out.println("id 잘 받았냐? : "+id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_medicine_price_delete_u, container, false);

        Context context = view.getContext();

        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        MedicinePriceDeleteU0 = new MedicinePriceDeleteU0();

        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);

        medicinePriceUData = new ArrayList<>();

        RequestTask requestTask = new RequestTask();
        String rtResult = null;
        String url = "http://152.70.89.118:4321/";

        try {
            rtResult = requestTask.execute(url + "medicinePriceSelfLookup?email="+id).get();

            rtResult = rtResult.replaceAll("\\[", "");
            rtResult = rtResult.replaceAll("]", "");
            rtResult = rtResult.replaceAll("\"", "");
            String[] arr = rtResult.split(",");

            data = new MedicinePriceUData();
            for (int i = 0; i < arr.length-1; i++) {
                System.out.println("넣을 값 : " + arr[i]);
                if ((i % 4) == 0) {
                    data.setEnrollDate(arr[i]);
                } else if ((i % 4) == 1) {
                    System.out.println("이름 들어가는거 : " + arr[i]);
                    data.setpName(arr[i]);
                } else if ((i % 4) == 2) {
                    data.setmName(arr[i]);
                } else if ((i % 4) == 3) {
                    data.setmPrice(arr[i]);
                    mAdapter.addItem(data);
                    data = new MedicinePriceUData();
                }

            mAdapter = new MedicinePriceDeleteUAdapter(view.getContext(), medicinePriceUData);
            mRecyclerView.setAdapter(mAdapter);


            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        cancelBtn = view.findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //이전 화면으로 돌아감
                ((MainActivity)getActivity()).replaceFragment(MedicinePriceDeleteU0);
            }
        });
        return view;
    }
}