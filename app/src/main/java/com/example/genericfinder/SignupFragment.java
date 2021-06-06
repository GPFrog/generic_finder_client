package com.example.genericfinder;

import android.content.Context;
import android.content.SharedPreferences;
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
    Button requestBtn, confirmBtn;
    EnterFragment enterFragment;
    Fragment signupFragment2;

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
        requestBtn = view.findViewById(R.id.requestBtn);
        confirmBtn = view.findViewById(R.id.confirmBtn);
        enterFragment = new EnterFragment();
        signupFragment2 = new SignupFragment2();

        //인증 요청 버튼 클릭 이벤트
        requestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (emailEdit.getText().toString().isEmpty()){
                    Toast.makeText(getContext(),"Email을 입력해주세요.",Toast.LENGTH_SHORT).show();
                }
                else {
                    // ~~~~ 인증 코드 요청받는 코드


                    //성공했으면
                    Toast.makeText(view.getContext(),"인증 코드를 발송했습니다.",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //인증 확인 버튼 클릭 이벤트
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                RequestTask requestTask = new RequestTask();
//                String rtResult = null;
//
//                try {
//                    rtResult = requestTask.execute("", "userEmail=" + emailEdit.toString()).get();
//                    JSONObject jsonObject = new JSONObject(rtResult);
//
//                    if(jsonObject.toString().contains("true")) {
//                        Toast.makeText(view.getContext(), "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
//                        ((MainActivity)getActivity()).replaceFragment(enterFragment);
//                    }
//                    else Toast.makeText(view.getContext(), "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }

                //인증 번호 미입력 시
                if (codeEdit.getText().toString().isEmpty()){
                    Toast.makeText(getContext(),"인증에 실패하였습니다.",Toast.LENGTH_SHORT).show();
                }
                else {
                    //인증 번호가 일치할 때

                    //이메일 넘기기 위해 저장
                    SharedPreferences sharedEmail = getContext().getSharedPreferences("email",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedEmail.edit();
                    editor.putString("signupEmail",emailEdit.getText().toString()); //key, value 형식으로 저장
                    editor.commit();
                    Toast.makeText(view.getContext(),"저장 성공!",Toast.LENGTH_SHORT).show();

                    Toast.makeText(view.getContext(),"인증에 성공하였습니다.",Toast.LENGTH_SHORT).show();

                    //다음 화면으로 전환
                    ((MainActivity)getActivity()).replaceFragment(signupFragment2);
                }
            }
        });

        return view;
    }
}