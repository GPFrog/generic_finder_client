package com.example.genericfinder;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.genericfinder.httpConnector.RequestTask;

import org.json.JSONObject;

public class SignupFragment extends Fragment {

    EditText emailEdit, codeEdit;
    Button rtBtn, signupBtn;
    EnterFragment enterFragment;

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
        enterFragment = new EnterFragment();

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
                RequestTask requestTask = new RequestTask();
                String rtResult = null;
                
                try {
                    rtResult = requestTask.execute("", "userEmail=" + emailEdit.toString()).get();
                    JSONObject jsonObject = new JSONObject(rtResult);
                    
                    if(jsonObject.toString().contains("true")) {
                        Toast.makeText(view.getContext(), "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                        ((MainActivity)getActivity()).replaceFragment(enterFragment);
                    }
                    else Toast.makeText(view.getContext(), "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return view;
    }
}