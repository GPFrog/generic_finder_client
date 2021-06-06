package com.example.genericfinder;

import android.content.Context;
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

import com.example.genericfinder.httpConnector.RequestTask;

import org.json.JSONObject;

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
        idInput = view.findViewById(R.id.idInput);
        String url = "http://119.56.228.77:39283/";

        loginBtn = view.findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //id DB에 있는지 확인
                RequestTask requestTask = new RequestTask();
                String rtResult = null;

                Context context = getActivity();
                SharedPreferences sharedPreferences = context.getSharedPreferences("Value", MODE_PRIVATE);    // test 이름의 기본모드 설정
                SharedPreferences.Editor editor = sharedPreferences.edit(); //sharedPreferences를 제어할 editor를 선언

                try {
                    rtResult = requestTask.execute("", "id=" + idInput.toString(), "&pw=" + pwInput.toString()).get();
                    JSONObject jsonObject = new JSONObject(rtResult);

                    if(jsonObject.toString().contains("true")) {
                        if(jsonObject.toString().contains("1")) {
                            //사용자
                            editor.putString("id", idInput.getText().toString()); // key,value 형식으로 저장
                            editor.putString("authority", "1");
                            Toast.makeText(view.getContext(),"로그인 되었습니다.", Toast.LENGTH_SHORT);
                            ((MainActivity)getActivity()).replaceFragment(CurrentPosition);
                        }
                        else if(jsonObject.toString().contains("2")) {
                            //관리자
                            editor.putString("id", idInput.getText().toString()); // key,value 형식으로 저장
                            editor.putString("authority", "2");
                            Toast.makeText(view.getContext(),"로그인 되었습니다.", Toast.LENGTH_SHORT);
                            ((MainActivity)getActivity()).replaceFragment(CurrentPosition);
                        }
                        else if(jsonObject.toString().contains("3")) {
                            //블랙리스트
                            Toast.makeText(view.getContext(),"로그인에 실패했습니다.", Toast.LENGTH_SHORT);
                        }
                    }
                    else {
                        Toast.makeText(view.getContext(),"id 또는 pw가 잘못되었습니다.", Toast.LENGTH_SHORT);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                editor.commit();    //최종 커밋. 커밋을 해야 저장이 된다.
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
                Toast.makeText(view.getContext(),"비회원 페이지 이동", Toast.LENGTH_SHORT);

                //서버 연결 테스트
//                RequestTask request = new RequestTask();
//                String result = null;
//
//                try {
//                    result = request.execute(url + "medicineDetail?medicineCode=198600441").get();
//                    System.out.println(result);
//                    JSONObject jsonObject = new JSONObject(result);
//                    System.out.println(jsonObject.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }

                ((MainActivity)getActivity()).replaceFragment(CurrentPosition);
            }
        });

        return view;
    }
}