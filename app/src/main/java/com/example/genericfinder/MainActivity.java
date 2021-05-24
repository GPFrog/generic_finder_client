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
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Toolbar myToolBar;
    Fragment BookmarkFragment, EnterFragment, CurrentPosition, MedicineInfo, MedicineSearch,
            MedicineSearchResult, SignUpFragment, PharmacyInfo, MedicinePriceDeleteU,
            MedicinePriceDeleteA, MedicinePriceEnroll;
    NavigationView nav_view;

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
//        PharmacyInfo = new PharmacyInfo();
        BookmarkFragment = new BookmarkFragment();
        MedicinePriceEnroll = new MedicinePriceEnroll();
        MedicinePriceDeleteU = new MedicinePriceDeleteU();
        MedicinePriceDeleteA = new MedicinePriceDeleteA();

        nav_view = findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_genericSearch:
                        Toast.makeText(getApplicationContext(), "의약품 검색", Toast.LENGTH_LONG).show();
                        replaceFragment(MedicineSearch);
                        break;
                    case R.id.menu_pharmSearch:
                        Toast.makeText(getApplicationContext(), "내 주변 약국 찾기", Toast.LENGTH_LONG).show();
//                        replaceFragment(PharmacyInfo);
//                        Intent intent = new Intent(getApplicationContext(), PharmacyInfoActivity.class);
//                        startActivity(intent);
                        break;
                    case R.id.menu_bookmark:
                        Toast.makeText(getApplicationContext(), "즐겨찾기", Toast.LENGTH_LONG).show();
                        replaceFragment(BookmarkFragment);
                        break;
                    case R.id.menu_priceDelete:
                        Toast.makeText(getApplicationContext(), "약 가격 삭제", Toast.LENGTH_LONG).show();
                        //비회원이면 안된다는 메시지
                        //회원이면 회원 페이지
                        //관리자면 관리자 페이지
                        replaceFragment(MedicinePriceDeleteU);
                        break;
                    case R.id.menu_withdraw:
                        AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                        dlg.setTitle("회원탈퇴");
                        dlg.setMessage("회원탈퇴를 하시겠습니까?");
                        dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //회원탈퇴 코드 추가해야함
                                Toast.makeText(getApplicationContext(), "회원탈퇴되었습니다.", Toast.LENGTH_LONG).show();
                            }
                        });
                        dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Toast.makeText(getApplicationContext(), "취소되었습니다.", Toast.LENGTH_LONG).show();
                            }
                        });

//                        AlertDialog alertDialog = dlg.create();
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

    //뒤로가기
//    public interface onKeyBackPressedListener { void onBackKey(); }
//
//    private onKeyBackPressedListener mOnKeyBackPressedListener;
//
//    public void setOnKeyBackPressedListener(onKeyBackPressedListener listener) {
//        mOnKeyBackPressedListener = listener;
//    }
//
//    @Override
//    public void onBackPressed() {
//        if(mOnKeyBackPressedListener != null) mOnKeyBackPressedListener.onBackKey();
//        else super.onBackPressed();
//    }
}