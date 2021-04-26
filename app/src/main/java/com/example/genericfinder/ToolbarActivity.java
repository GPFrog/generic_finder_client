package com.example.genericfinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class ToolbarActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);

        //툴바 설정
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_menu);
        getSupportActionBar().setTitle("Generic Finder");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_genericSearch:
                Toast.makeText(getApplicationContext(), "약 검색", Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu_pharmSearch:
                Toast.makeText(getApplicationContext(), "약국 검색", Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu_searchHistory:
                Toast.makeText(getApplicationContext(), "검색 기록", Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu_setting:
                Toast.makeText(getApplicationContext(), "설정", Toast.LENGTH_LONG).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}