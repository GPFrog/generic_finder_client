package com.example.genericfinder;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class MedicinePriceDeleteU extends Fragment {

    Button deleteBtn, cancelBtn;

    public MedicinePriceDeleteU() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_medicine_price_delete_u, container, false);

        deleteBtn = view.findViewById(R.id.deleteBtn);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(view.getContext());
                dlg.setTitle("약 가격 삭제");
                dlg.setMessage("등록된 약 가격을 삭제하시겠습니까?");
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //약 가격 삭제 코드 추가해야함
                        Toast.makeText(view.getContext(), "삭제되었습니다.", Toast.LENGTH_LONG).show();
                    }
                });
                dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Toast.makeText(view.getContext(), "취소되었습니다.", Toast.LENGTH_LONG).show();
                    }
                });
                dlg.show();
            }
        });

        cancelBtn = view.findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //이렇게 하면 메뉴 버튼 눌려서 들어갈 때는 돌아갈 페이지 없음
                //검색 화면으로 돌아가야하낭
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().remove(MedicinePriceDeleteU.this).commit();
                fragmentManager.popBackStack();
            }
        });
        return view;
    }
}