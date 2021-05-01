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

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Toolbar myToolBar;
    Fragment BookmarkFragment, EnterFragment, CurrentPosition, MedicineInfo, MedicineSearch, MedicineSearchResult, SignUpFragment;
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
                this, drawer, myToolBar, R.string.nav_app_bar_open_drawer_description, R.string.nav_app_bar_navigate_up_description);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //프래그먼트 초기화
        EnterFragment = new EnterFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container, EnterFragment).commit();

        MedicineSearch = new MedicineSearch();

        nav_view = findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_genericSearch:
                        Toast.makeText(getApplicationContext(), "약 검색", Toast.LENGTH_LONG).show();
                        replaceFragment(MedicineSearch);
                        break;
                    case R.id.menu_pharmSearch:
                        Toast.makeText(getApplicationContext(), "약국 검색", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.menu_searchHistory:
                        Toast.makeText(getApplicationContext(), "검색 기록", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.menu_setting:
                        Toast.makeText(getApplicationContext(), "설정", Toast.LENGTH_LONG).show();
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

//    public void replaceFragment(int fragmentIndex){
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//
//        switch (fragmentIndex){
//            case 0:
//                // Enter 프래그먼트 호출
//                EnterFragment = new EnterFragment();
//                transaction.replace(R.id.container, EnterFragment);
//                transaction.commit();
//                break;
//
//            case 1:
//                // 즐겨찾기 프래그먼트 호출
//                BookmarkFragment = new BookmarkFragment();
//                transaction.replace(R.id.container, BookmarkFragment);
//                transaction.commit();
//                break;
//
//            case 2:
//                // 현재 위치 프래그먼트 호출
//                CurrentPosition = new CurrentPosition();
//                transaction.replace(R.id.container, CurrentPosition);
//                transaction.commit();
//                break;
//
//            case 3:
//                // 약 상세 정보 프래그먼트 호출
//                MedicineInfo = new MedicineInfo();
//                transaction.replace(R.id.container, MedicineInfo);
//                transaction.commit();
//                break;
//
//            case 4:
//                //의약품 검색 프래그먼트 호출
//                MedicineSearch = new MedicineSearch();
//                transaction.replace(R.id.container, MedicineSearch);
//                transaction.commit();
//                break;
//
//            case 5:
//                //의약품 검색 결과 프래그먼트 호출
//                MedicineSearchResult = new MediSearchResult();
//                transaction.replace(R.id.container, MedicineSearchResult);
//                transaction.commit();
//                break;
//
//            case 6:
//                //회원가입 프래그먼트 호출
//                SignUpFragment = new SignupFragment();
//                transaction.replace(R.id.container, SignUpFragment);
//                transaction.commit();
//                break;
//        }
//
//    }
}