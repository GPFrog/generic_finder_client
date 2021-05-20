package com.example.genericfinder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;

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
                //id DB에 있는지 확인

                //로그인 시 id 로컬에 저장
                SharedPreferences sharedPreferences = view.getContext().getSharedPreferences("Value", MODE_PRIVATE);    // test 이름의 기본모드 설정
                SharedPreferences.Editor editor = sharedPreferences.edit(); //sharedPreferences를 제어할 editor를 선언
                editor.putString("id", idInput.getText().toString()); // key,value 형식으로 저장
                editor.commit();    //최종 커밋. 커밋을 해야 저장이 된다.

                Toast.makeText(view.getContext(),"로그인 완료 후 페이지 이동", Toast.LENGTH_LONG);
                ((MainActivity)getActivity()).replaceFragment(CurrentPosition);
            }
        });

        gosignUpBtn = view.findViewById(R.id.gosignUpBtn);
        gosignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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