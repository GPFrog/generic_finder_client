package com.example.genericfinder;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.genericfinder.httpConnector.RequestTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import kotlin.jvm.internal.markers.KMappedMarker;

import static android.content.Context.LOCATION_SERVICE;

public class MediSearchResult extends Fragment {
    private static final String TAG = "MediSearchResult";
    boolean isLoading = false;

    //지오코딩 테스트
    private GpsTracker gpsTracker;

    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int PERMISSIONS_REQUEST_CODE = 100;
    String[] REQUIRED_PERMISSIONS  = {Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION};


    private RecyclerView.LayoutManager mLayoutManager;
    RecyclerView mRecyclerView;
    SearchResultAdapter mAdapter;
    ArrayList<SearchResultData> searchResultData;

    SearchResultData data;
    Button filterBtn;
    Bundle getBundle;
    String searchName;
    String searchIngredient;
    String searchCompany;
    String searchEffect;

    public MediSearchResult() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBundle = getArguments();
        searchName = getBundle.getString("searchName");
        searchIngredient = getBundle.getString("searchIngredient");
        searchCompany = getBundle.getString("searchCompany");
        searchEffect = getBundle.getString("searchEffect");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_medi_search_result, container, false);

        Context context = view.getContext();
        mRecyclerView = view.findViewById(R.id.resultRecyclev);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);

        searchResultData = new ArrayList<>();
//        mAdapter = new SearchResultAdapter(view.getContext(), searchResultData);
//        mAdapter = new SearchResultAdapter(searchResultData);
//        mAdapter = new SearchResultAdapter();
//        mRecyclerView.setAdapter(mAdapter);


        //#####현재 위치 정보########
        gpsTracker = new GpsTracker(getContext());

        double latitude = gpsTracker.getLatitude();
        double longitude = gpsTracker.getLongitude();

        String address = getCurrentAddress(latitude, longitude);
        String[] tmp = address.split(" ");

        String sido; //경상북도
        String sigungu; //구미시

        sido = tmp[1];
        sigungu = tmp[2];

        //테스트
        String sido2,sigungu2;
        sido2 ="경상북도";
        sigungu2 = "구미시";

        System.out.println("\n 시도 : "+sido+"\n 시군구 : "+sigungu);
        //#####현재 위치 정보 끝########


        RequestTask requestTask = new RequestTask();
        String rtResult = null;
        String url = "http://152.70.89.118:4321/";

        try {
            System.out.println("1번");
//            rtResult = requestTask.execute(url + "medicineLookup?name=" + searchName + "&activeingredient=" + searchIngredient
//                    + "&company=" + searchCompany + "&symptom=" + searchEffect).get();
            // 이름 & 도 & 시
            rtResult = requestTask.execute(url + "medicineLookup?name=" + searchName + "&sido=" + sido2 + "&sigungu=" + sigungu2).get();
            rtResult = rtResult.replaceAll("\\[", "");
            rtResult = rtResult.replaceAll("]", "");
            rtResult = rtResult.replaceAll("\"", "");
            String[] arr = rtResult.split(",");
            String medicineCode[] = new String[arr.length / 3];
            int cnt = 0;
            System.out.println("2번");
            mAdapter = new SearchResultAdapter(view.getContext(), searchResultData);
            mRecyclerView.setAdapter(mAdapter);
            System.out.println("3번");
            data = new SearchResultData();
            for (int i = 0; i < arr.length; i++) {
                System.out.println("넣을 값 : " + arr[i]);
                if ((i % 3) == 0) {
                    medicineCode[cnt] = arr[i];
                    cnt++;
                } else if ((i % 3) == 1) {
                    System.out.println("이름 들어가는거 : " + arr[i]);
                    data.setResultName(arr[i]);
                } else if ((i % 3) == 2) {
                    data.setResultPrice(arr[i]);
                    mAdapter.addItem(data);
                    data = new SearchResultData();
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


//        System.out.println("name : " + searchName + ", ingredient : " + searchIngredient + ", company : " + searchCompany + ", effect : " + searchEffect);
//        mAdapter.getString(searchName, searchIngredient, searchCompany, searchEffect);

        filterBtn = view.findViewById(R.id.filterBtn);
        filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //필터 버튼 (필터 팝업 띄우기)
                //검색 결과 보내기
                Bundle bundle = new Bundle();

                FilterPopup fpopup = new FilterPopup();
                fpopup.setArguments(bundle);
                fpopup.show(getActivity().getSupportFragmentManager(), "FilterPopup");
            }
        });

        initScrollListener(mRecyclerView, view);

        return view;
    }

    //########현재 주소 구하는 메서드###########
    /*
     * ActivityCompat.requestPermissions를 사용한 퍼미션 요청의 결과를 리턴받는 메소드입니다.
     */
    @Override
    public void onRequestPermissionsResult(int permsRequestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grandResults) {

        if ( permsRequestCode == PERMISSIONS_REQUEST_CODE && grandResults.length == REQUIRED_PERMISSIONS.length) {

            // 요청 코드가 PERMISSIONS_REQUEST_CODE 이고, 요청한 퍼미션 개수만큼 수신되었다면
            boolean check_result = true;


            // 모든 퍼미션을 허용했는지 체크합니다.
            for (int result : grandResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    check_result = false;
                    break;
                }
            }

            if ( check_result ) {
                //위치 값을 가져올 수 있음;
            }
            else {
                // 거부한 퍼미션이 있다면 앱을 사용할 수 없는 이유를 설명해주고 앱을 종료합니다.2 가지 경우가 있습니다.
                if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), REQUIRED_PERMISSIONS[0])
                        || ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), REQUIRED_PERMISSIONS[1])) {

                    Toast.makeText(getContext(), "퍼미션이 거부되었습니다. 앱을 다시 실행하여 퍼미션을 허용해주세요.", Toast.LENGTH_LONG).show();
                    getActivity().finish();

                }else {
                    Toast.makeText(getContext(), "퍼미션이 거부되었습니다. 설정(앱 정보)에서 퍼미션을 허용해야 합니다. ", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    void checkRunTimePermission(){
        //런타임 퍼미션 처리
        // 1. 위치 퍼미션을 가지고 있는지 체크합니다.
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION);

        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED) {
            // 2. 이미 퍼미션을 가지고 있다면
            // ( 안드로이드 6.0 이하 버전은 런타임 퍼미션이 필요없기 때문에 이미 허용된 걸로 인식합니다.)

            // 3.  위치 값을 가져올 수 있음

        } else {  //2. 퍼미션 요청을 허용한 적이 없다면 퍼미션 요청이 필요합니다. 2가지 경우(3-1, 4-1)가 있습니다.

            // 3-1. 사용자가 퍼미션 거부를 한 적이 있는 경우에는
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), REQUIRED_PERMISSIONS[0])) {

                // 3-2. 요청을 진행하기 전에 사용자가에게 퍼미션이 필요한 이유를 설명해줄 필요가 있습니다.
                Toast.makeText(getContext(), "이 앱을 실행하려면 위치 접근 권한이 필요합니다.", Toast.LENGTH_LONG).show();
                // 3-3. 사용자게에 퍼미션 요청을 합니다. 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(getActivity(), REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);

            } else {
                // 4-1. 사용자가 퍼미션 거부를 한 적이 없는 경우에는 퍼미션 요청을 바로 합니다.
                // 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(getActivity(), REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);
            }
        }
    }

    public String getCurrentAddress( double latitude, double longitude) {

        //지오코더... GPS를 주소로 변환
        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());

        List<Address> addresses;

        try {
            addresses = geocoder.getFromLocation(
                    latitude,
                    longitude,
                    7);
        } catch (IOException ioException) {
            //네트워크 문제
            Toast.makeText(getContext(), "지오코더 서비스 사용불가", Toast.LENGTH_LONG).show();
            return "지오코더 서비스 사용불가";
        } catch (IllegalArgumentException illegalArgumentException) {
            Toast.makeText(getContext(), "잘못된 GPS 좌표", Toast.LENGTH_LONG).show();
            return "잘못된 GPS 좌표";
        }

        if (addresses == null || addresses.size() == 0) {
            Toast.makeText(getContext(), "주소 미발견", Toast.LENGTH_LONG).show();
            return "주소 미발견";

        }

        Address address = addresses.get(0);
        return address.getAddressLine(0).toString()+"\n";

    }


    //여기부터는 GPS 활성화를 위한 메소드들
    private void showDialogForLocationServiceSetting() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("위치 서비스 비활성화");
        builder.setMessage("앱을 사용하기 위해서는 위치 서비스가 필요합니다.\n"
                + "위치 설정을 수정하실래요?");
        builder.setCancelable(true);
        builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent callGPSSettingIntent
                        = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case GPS_ENABLE_REQUEST_CODE:

                //사용자가 GPS 활성 시켰는지 검사
                if (checkLocationServicesStatus()) {
                    if (checkLocationServicesStatus()) {

                        Log.d("@@@", "onActivityResult : GPS 활성화 되있음");
                        checkRunTimePermission();
                        return;
                    }
                }
                break;
        }
    }

    public boolean checkLocationServicesStatus() {
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }
    //########현재 주소 구하는 메서드 끝#########


    //무한 스크롤
    void dataMore() {
        Log.d(TAG, "dataMore: ");
        searchResultData.add(null);
        mAdapter.notifyItemInserted(searchResultData.size() - 1);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                searchResultData.remove(searchResultData.size() - 1);
                int scrollPosition = searchResultData.size();
                mAdapter.notifyItemRemoved(scrollPosition);
                int currentSize = scrollPosition;

                String[] arrayMedi = null;
                for(String medicine : arrayMedi) {
                    searchResultData.add(new SearchResultData(medicine));
                }

                mAdapter.notifyDataSetChanged();
                isLoading = false;
            }
        }, 2000);
    }

    public void initScrollListener(RecyclerView recyclerView, View view) {
//        if(searchResultData.size() == 0) {
//            isLoading = true;
//            dataMore();
//        }

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
//                Log.d(TAG, "onScrollStateChanged: ");
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//                Log.d(TAG, "onScrolled: ");

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager)recyclerView.getLayoutManager();

                if(isLoading && (searchResultData.size() % 20) == 0 && searchResultData.size() != 0) {
                    if(linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == searchResultData.size() - 1) {
                        isLoading = true;
//                        dataMore();
                        loadMore();
                        Toast.makeText(view.getContext(), "스크롤감지", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void loadMore() {
        searchResultData.add(null);
        mAdapter.notifyItemInserted(searchResultData.size() - 1);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                searchResultData.remove(searchResultData.size() - 1);
                int scrollPosition = searchResultData.size();
                mAdapter.notifyItemRemoved(scrollPosition);
                int currentSize = scrollPosition;
                int nextLimit = currentSize + 10;
                SearchResultData sd = new SearchResultData();

                while(currentSize - 1 < nextLimit) {
                    searchResultData.add(sd);
                    currentSize++;
                }

                mAdapter.notifyDataSetChanged();
                isLoading = false;
            }
        }, 2000);
    }
}