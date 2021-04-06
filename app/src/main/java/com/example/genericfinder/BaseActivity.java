package com.example.genericfinder;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

public class BaseActivity extends AppCompatActivity {

    private String[] navItems = {"의약품 검색", "내 주변 약국 찾기", "검색 기록", "설정"};

    private ListView nav_list;
    private FrameLayout activityContainer;
    androidx.appcompat.widget.Toolbar toolbar;

    private DrawerLayout drawer_layout;
    private ActionBarDrawerToggle dtToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        nav_list = (ListView) findViewById(R.id.nav_list);
        nav_list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, navItems));
        nav_list.setOnItemClickListener(new BaseActivity.DrawerItemClickListener());

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        dtToggle = new ActionBarDrawerToggle(this, drawer_layout, R.string.app_name, R.string.app_name);
        drawer_layout.addDrawerListener(dtToggle);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_menu);
    }

    @Override
    public void setContentView(int layoutResId) {
        LinearLayout fullView = (LinearLayout) getLayoutInflater().inflate(R.layout.activity_base, null);
        getLayoutInflater().inflate(layoutResId, activityContainer, true);
        super.setContentView(fullView);

        //툴바 사용여부 결정(기본은 사용)
        if(useToolbar()) {
            setSupportActionBar(toolbar);
            setTitle("Generic Finder");
        } else {
            toolbar.setVisibility(View.GONE);
        }
    }

    //툴바 사용여부
    protected boolean useToolbar() {
        return true;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        dtToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        dtToggle.onConfigurationChanged(newConfig);
    }

    //메뉴 클릭 이벤트
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(dtToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);

//        switch (item.getItemId()) {
//            case R.id.menu_genericSearch:
//                return true;
//
//            case R.id.menu_menu_pharmSearch:
//                return true;
//
//            case R.id.menu_searchHistory:
//                return true;
//
//            case R.id.menu_setting:
//                return true;
//
//            default:
//                return super.onOptionsItemSelected(item);
//        }
    }

    private class DrawerItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            switch (position) {
                case 0:
                    break;

                case 1:
                    break;

                case 2:
                    break;

                case 3:
                    break;
            }
            drawer_layout.closeDrawer(nav_list);
        }
    }
}