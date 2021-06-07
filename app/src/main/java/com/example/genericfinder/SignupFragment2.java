package com.example.genericfinder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.genericfinder.httpConnector.RequestTask;

import org.json.JSONObject;

public class SignupFragment2 extends Fragment {
    TextView email;
    EditText pwEdit;
    Button cancelBtn, confirmBtn;
    EnterFragment enterFragment;
    Fragment signupFragment, medicineSearch;

    public SignupFragment2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup2, container, false);

        RequestTask requestTask = new RequestTask();
        String url = "http://152.70.89.118:4321/";

        email = view.findViewById(R.id.email);
        pwEdit = view.findViewById(R.id.pwEdit);
        cancelBtn = view.findViewById(R.id.cancelBtn);
        confirmBtn = view.findViewById(R.id.confirmBtn);
        enterFragment = new EnterFragment();
        signupFragment = new SignupFragment();
        medicineSearch = new MedicineSearch();

        //SharedPreferences 로 이메일 값 고정
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("email", Context.MODE_PRIVATE);
        String emailEdit = sharedPreferences.getString("signupEmail","");
        email.setText(emailEdit); //SharedPreferences 에서 값 받아오기

        //취소 버튼 클릭 이벤트
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
<<<<<<< HEAD
=======

>>>>>>> main
                //인증 화면으로 다시 가게.
                Toast.makeText(view.getContext(),"이전 화면으로 돌아갑니다.",Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).replaceFragment(signupFragment);
            }
        });

        //확인 버튼 클릭 이벤트
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pwEdit.getText().toString().isEmpty()){
                    Toast.makeText(getContext(),"비밀번호를 입력해주세요.",Toast.LENGTH_SHORT).show();
                }
                else {
                    //회원 가입 완료
                    AlertDialog.Builder dlg = new AlertDialog.Builder(getContext());
                    dlg.setTitle("회원가입");
                    dlg.setMessage("해당 정보로 회원가입하시겠습니까?");
                    dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //서버로 정보 넘겨야 함 ( 이메일, 패스워드 )
                            String rtResult = null;

                            try {
                                rtResult = requestTask.execute(url + "signup?id=" + email.getText().toString()+"&password="+pwEdit.getText().toString()).get();
                                rtResult = rtResult.replaceAll("\"", "");
                                String arr[] = rtResult.split("/");

                                //가입 성공했을 때
                                if (arr[0].compareTo("ok")==0){
                                    Toast.makeText(view.getContext(), "회원 가입 성공!\n가입한 정보로 로그인해주세요.", Toast.LENGTH_SHORT).show();
                                    ((MainActivity) getActivity()).replaceFragment(enterFragment); //로그인
                                }
                                else{
                                    //실패했으면
                                    Toast.makeText(view.getContext(),"회원 가입 실패",Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    });
                    dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    dlg.show();
                }
            }
        });

        return view;
    }
}
