package com.example.genericfinder;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EnterFragment extends Fragment {

    MainActivity mainActivity;
    EditText idInput, pwInput;
    Button loginBtn, gosignUpBtn, nonmemberBtn;
    Fragment CurrentPosition, SignupFragment;

    public EnterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_enter, container, false);

        CurrentPosition = new CurrentPosition();
        SignupFragment = new SignupFragment();

        loginBtn = view.findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //로그인 정보 들고 이동하는 기능 추가해야함
                Toast.makeText(view.getContext(),"로그인 완료 후 페이지 이동", Toast.LENGTH_LONG);
                ((MainActivity)getActivity()).replaceFragment(CurrentPosition);
            }
        });

        gosignUpBtn = view.findViewById(R.id.gosignUpBtn);
        gosignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"회원가입 페이지 이동", Toast.LENGTH_LONG);
                ((MainActivity)getActivity()).replaceFragment(SignupFragment);
            }
        });

        nonmemberBtn = view.findViewById(R.id.nonmemberBtn);
        nonmemberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"비회원 페이지 이동", Toast.LENGTH_LONG);
                ((MainActivity)getActivity()).replaceFragment(CurrentPosition);
            }
        });

        return view;
    }
}