package com.example.genericfinder;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.genericfinder.httpConnector.RequestTask;

import org.json.JSONObject;

public class MedicinePriceEnroll extends Fragment {

    TextView medicineCode;
    EditText pharmacyNumInput, priceInput;
    Button cancelBtn, enrollBtn;
    Fragment MedicineSearch, MedicinePriceDeleteU;
    PriceEnrollPopup priceEnrollPopup;

    public MedicinePriceEnroll() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_medicine_price_enroll, container, false);

        medicineCode = view.findViewById(R.id.medicineCode);
        pharmacyNumInput = view.findViewById(R.id.pharmacyNumInput);
        priceInput = view.findViewById(R.id.priceInput);
        MedicineSearch = new MedicineSearch();
        MedicinePriceDeleteU = new MedicinePriceDeleteU();

        cancelBtn = view.findViewById(R.id.cancelBtn);
        enrollBtn = view.findViewById(R.id.enrollBtn);
        priceEnrollPopup = new PriceEnrollPopup();

        Bundle getBundle = getArguments();
        String mediName = getBundle.getString("mediName");

        //의약품 명으로 해뒀는데 혹시 코드로 해야하면 변경 해야함
        medicineCode.setText(mediName);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //의약품 검색 화면으로
                ((MainActivity)getActivity()).replaceFragment(MedicineSearch);
            }
        });

        enrollBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //등록버튼 누름
                if (medicineCode.getText().toString().length() == 0 ||
                        pharmacyNumInput.getText().toString().length()==0 ||
                        priceInput.getText().toString().length()==0) {
                    //공백일 때 예외처리
                    Toast.makeText(v.getContext(),"값을 입력해주세요",Toast.LENGTH_SHORT).show();
                }else {
                    //팝업
                    Bundle bundle = new Bundle();
                    bundle.putString("medicineName", mediName);
                    bundle.putString("pharmacyNum", pharmacyNumInput.toString());
                    bundle.putString("price", priceInput.toString());
                    priceEnrollPopup.setArguments(bundle);
                    ((MainActivity)getActivity()).replaceFragment(priceEnrollPopup);
                }
            }
        });

        return view;
    }
}