package com.example.genericfinder;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

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
        //고객 아이디 받기
        String value = bundle.getString("");

        fragment = getActivity().getSupportFragmentManager().findFragmentByTag("tag");

        if(fragment != null) {
            DialogFragment dialogFragment = (DialogFragment) fragment;
            dialogFragment.dismiss();
        }

        blistCancleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //약 가격 삭제만 진행
                getActivity().finish();
            }
        });

        bListOkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //약 가격 삭제 + 블랙리스트 등록 코드 추가해야함
                Toast.makeText(view.getContext(), "블랙리스트로 등록되었습니다.", Toast.LENGTH_LONG);
                getActivity().finish();
            }
        });

        return view;
    }
}