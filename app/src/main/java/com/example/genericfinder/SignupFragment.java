package com.example.genericfinder;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class SignupFragment extends Fragment {

    EditText emailEdit, codeEdit;
    Button rtBtn, signupBtn;

    public SignupFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup, container, false);

        emailEdit = view.findViewById(R.id.emailEdit);
        codeEdit = view.findViewById(R.id.codeEdit);
        rtBtn = view.findViewById(R.id.rtBtn);
        signupBtn = view.findViewById(R.id.signupBtn);

        //재전송 버튼 클릭 이벤트
        rtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //회원가입 버튼 클릭 이벤트
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }
}