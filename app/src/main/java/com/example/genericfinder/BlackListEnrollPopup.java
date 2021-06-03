package com.example.genericfinder;

import android.content.Context;
import android.content.SharedPreferences;
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

public class BlackListEnrollPopup extends DialogFragment {

    private Fragment fragment;
    Button blistCancleBtn, bListOkBtn;

    public BlackListEnrollPopup() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_black_list_enroll_popup, container, false);

        blistCancleBtn = view.findViewById(R.id.blistCancleBtn);
        bListOkBtn = view.findViewById(R.id.bListOkBtn);

        Bundle bundle = getArguments();
        //고객 이메일 받기
        String value = bundle.getString("userEmail");

        fragment = getActivity().getSupportFragmentManager().findFragmentByTag("tag");

        if(fragment != null) {
            DialogFragment dialogFragment = (DialogFragment) fragment;
            dialogFragment.dismiss();
        }

        blistCancleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        bListOkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestTask requestTask = new RequestTask();
                String rtResult = null;

                try {
                    //이메일로 블랙리스트 등록
                    rtResult = requestTask.execute("", "userEmail=" + value).get();
                    JSONObject jsonObject = new JSONObject(rtResult);

                    if(jsonObject.toString().contains("true")) Toast.makeText(view.getContext(), "블랙리스트로 등록되었습니다.", Toast.LENGTH_LONG).show();
                    else Toast.makeText(view.getContext(), "블랙리스트 등록에 실패했습니다.", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                getActivity().finish();
            }
        });

        return view;
    }
}