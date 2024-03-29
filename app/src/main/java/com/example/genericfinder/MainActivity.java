package com.example.genericfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.genericfinder.httpConnector.RequestTask;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Toolbar myToolBar;
    Fragment BookmarkFragment, EnterFragment, CurrentPosition, MedicineInfo, MedicineSearch,
            MedicineSearchResult, SignUpFragment, PharmacyInfo, MedicinePriceDeleteU0,
            MedicinePriceDeleteA, MedicinePriceEnroll;
    NavigationView nav_view;

    TextView nav_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //툴바 설정
        myToolBar = (Toolbar) findViewById(R.id.myToolBar);
        setSupportActionBar(myToolBar);

        //메뉴 버튼
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_menu);

        DrawerLayout drawer = findViewById(R.id.main_content);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, myToolBar, R.string.open, R.string.closed);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //프래그먼트 초기화
        EnterFragment = new EnterFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container, EnterFragment).commit();

        MedicineSearch = new MedicineSearch();
        PharmacyInfo = new PharmacyInfo();
        BookmarkFragment = new BookmarkFragment();
        MedicinePriceEnroll = new MedicinePriceEnroll();
        MedicinePriceDeleteU0 = new MedicinePriceDeleteU0();
        MedicinePriceDeleteA = new MedicinePriceDeleteA();

        nav_view = findViewById(R.id.nav_view);

        nav_email = findViewById(R.id.nav_email);

        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                //값 가져오는 코드
                SharedPreferences sharedPreferences = getSharedPreferences("email", Context.MODE_PRIVATE);
                String id = sharedPreferences.getString("LogOnEmail","");
                String authority = sharedPreferences.getString("authority","");

                switch (item.getItemId()) {
                    case R.id.menu_genericSearch:
                        Toast.makeText(getApplicationContext(), "의약품 검색", Toast.LENGTH_SHORT).show();
                        replaceFragment(MedicineSearch);
                        break;
                    case R.id.menu_pharmSearch:
                        Toast.makeText(getApplicationContext(), "내 주변 약국 찾기", Toast.LENGTH_SHORT).show();
                        replaceFragment(PharmacyInfo);
                        break;
                    case R.id.menu_bookmark:
                        Toast.makeText(getApplicationContext(), "즐겨찾기", Toast.LENGTH_SHORT).show();
                        replaceFragment(BookmarkFragment);
                        break;
                    case R.id.menu_priceDelete:
                        Toast.makeText(getApplicationContext(), "약 가격 삭제", Toast.LENGTH_SHORT).show();
                        replaceFragment(MedicinePriceDeleteU0);
                        //비회원이면 안된다는 메시지, 로그인 화면으로 전환
//                        if(nav_email.getText().toString().compareTo("")==0) {
//                            Toast.makeText(getApplicationContext(), "로그인이 필요한 메뉴입니다.", Toast.LENGTH_SHORT).show();
//                            replaceFragment(EnterFragment);
//                        }
//
//                        //관리자면 관리자 페이지
//                        else if(nav_email.getText().toString().compareTo("관리자")==0) {
//                            replaceFragment(MedicinePriceDeleteA);
//                        }
//
//                        //회원이면 회원 페이지
//                        else {
//                            replaceFragment(MedicinePriceDeleteU);
//                        }



//                        if(authority.compareTo("1") == 0) {
//                            replaceFragment(MedicinePriceDeleteU);
//                        }
//
//                        //관리자면 관리자 페이지
//                        else if(authority.compareTo("2") == 0) {
//                            replaceFragment(MedicinePriceDeleteA);
//                        }
//
//                        //비회원이면 안된다는 메시지, 로그인 화면으로 전환
//                        else {
//                            Toast.makeText(getApplicationContext(), "로그인이 필요한 메뉴입니다.", Toast.LENGTH_SHORT).show();
//                            replaceFragment(EnterFragment);
//                        }
                        break;
                    case R.id.menu_withdraw:
                        AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                        dlg.setTitle("회원탈퇴");
                        dlg.setMessage("회원탈퇴를 하시겠습니까?");
                        dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                RequestTask requestTask = new RequestTask();
                                String rtResult = null;
                                String url = "http://152.70.89.118:4321/";

                                try {
                                    rtResult = requestTask.execute(url + "withdraw?id=" + nav_email.getText().toString()).get();
                                    rtResult = rtResult.replaceAll("\"", "");
                                    String arr[] = rtResult.split("/");
                                    
                                    if (arr[0].compareTo("true") == 0) {
                                        Toast.makeText(getApplicationContext(), "회원탈퇴되었습니다.", Toast.LENGTH_SHORT).show();
                                        replaceFragment(EnterFragment);
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(), "회원탈퇴에 실패했습니다.", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Toast.makeText(getApplicationContext(), "회원탈퇴에 실패했습니다.", Toast.LENGTH_SHORT).show();
                                    replaceFragment(EnterFragment);
                                }
                            }
                        });
                        dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Toast.makeText(getApplicationContext(), "취소되었습니다.", Toast.LENGTH_SHORT).show();
                            }
                        });
                        dlg.show();
                        break;
                }
                DrawerLayout drawer = findViewById(R.id.main_content);
                drawer.closeDrawer(GravityCompat.START);

                return true;
            }
        });
    }

    //메뉴 선택
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment).commit();      // Fragment로 사용할 MainActivity내의 layout공간을 선택
    }

}