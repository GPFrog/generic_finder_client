package com.example.genericfinder;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;

public class FilterPopup extends DialogFragment {

    private Fragment fragment;
    SeekBar price_bar;
    TextView priceText;
    Button filterCheckBtn;
    MediSearchResult mediSearchResult;
    FilterResult filterResult;
    CompanyCheckAdapter companyAdapter;
    SymptomCheckAdapter symptomAdapter;

    public FilterPopup() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filter_popup, container, false);

        Bundle getBundle = getArguments();
        fragment = getActivity().getSupportFragmentManager().findFragmentByTag("FilterPopup");

//        if(fragment != null) {
//            DialogFragment dialogFragment = (DialogFragment) fragment;
//            dialogFragment.dismiss();
//        }

        //가격 seekbar
        priceText = view.findViewById(R.id.priceText);
        price_bar = view.findViewById(R.id.price_bar);
        price_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //bar 이동시키는 경우
                priceText.setText(progress + " 원");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //아무것도 선택 안했을 경우
                priceText.setText("10000 원");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //bar 이동 멈추는 경우
            }
        });

        mediSearchResult = new MediSearchResult();
        filterResult = new FilterResult();
        companyAdapter = new CompanyCheckAdapter();
        symptomAdapter = new SymptomCheckAdapter();

        String[] companyChecked = companyAdapter.getCheckedCompany();
        String[] symptomChecked = symptomAdapter.getCheckedSymptom();

        filterCheckBtn = view.findViewById(R.id.filterCheckBtn);
        filterCheckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putStringArray("companyChecked", companyChecked);
                bundle.putStringArray("symptomChecked", symptomChecked);
                filterResult.setArguments(bundle);

                ((MainActivity)getActivity()).replaceFragment(filterResult);
            }
        });

        return view;
    }
}