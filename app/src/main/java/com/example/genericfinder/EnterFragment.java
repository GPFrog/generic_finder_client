package com.example.genericfinder;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.genericfinder.httpConnector.RequestTask;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static android.content.Context.MODE_PRIVATE;

public class EnterFragment extends Fragment {

    MainActivity mainActivity;
    EditText emailInput, pwInput;
    Button loginBtn, gosignUpBtn, nonmemberBtn;
    Fragment SignupFragment, MedicineSearch;
    Fragment FilterPopup; //테스트

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

        MedicineSearch = new MedicineSearch();
        SignupFragment = new SignupFragment();

        emailInput = view.findViewById(R.id.emailInput);
        pwInput = view.findViewById(R.id.pwInput);
        String url = "http://152.70.89.118:4321/";

        //로그인
        loginBtn = view.findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //id DB에 있는지 확인
                RequestTask requestTask = new RequestTask();
                String rtResult = null;

                try {
                    rtResult = requestTask.execute(url + "signin?id=" + emailInput.getText().toString() + "&password=" + pwInput.getText().toString()).get();
                    rtResult = rtResult.replaceAll("\"", "");
                    String arr[] = rtResult.split("/");

                    System.out.println(arr[0]);
                    System.out.println(arr[1]);

                    if(arr[0].compareTo("ok") == 0) {
                        //이메일, 권한 넘기기 위해 저장
                        SharedPreferences sharedEmail = getContext().getSharedPreferences("email",Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedEmail.edit();
                        editor.putString("LogOnEmail", emailInput.getText().toString()); //key, value 형식으로 저장

                        if(arr[1].compareTo("1") == 0) {
                            //사용자
                            Toast.makeText(view.getContext(),"로그인 되었습니다.", Toast.LENGTH_SHORT).show();
                            editor.putString("authority", "1");
                            editor.commit();
                            ((MainActivity)getActivity()).replaceFragment(MedicineSearch);
                        }
                        else if(arr[1].compareTo("2") == 0) {
                            //관리자
                            Toast.makeText(view.getContext(),"관리자 로그인 되었습니다.", Toast.LENGTH_SHORT).show();
                            editor.putString("authority", "2");
                            editor.commit();
                            ((MainActivity)getActivity()).replaceFragment(MedicineSearch);
                        }
                        else if(arr[1].compareTo("3") == 0) {
                            //블랙리스트
                            Toast.makeText(view.getContext(),"로그인에 실패했습니다.", Toast.LENGTH_SHORT).show();
                        }
                        else System.out.println("equals 아님");
                    }
                    else {

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(view.getContext(),"id 또는 pw가 잘못되었습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //회원가입
        gosignUpBtn = view.findViewById(R.id.gosignUpBtn);
        gosignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(SignupFragment);
            }
        });

        //비회원
        nonmemberBtn = view.findViewById(R.id.nonmemberBtn);
        nonmemberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"비회원 페이지 이동", Toast.LENGTH_SHORT);
                ((MainActivity)getActivity()).replaceFragment(MedicineSearch);
            }
        });

        return view;
    }
}