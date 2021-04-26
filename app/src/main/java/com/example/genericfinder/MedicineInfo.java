package com.example.genericfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.io.ByteArrayOutputStream;

public class MedicineInfo extends AppCompatActivity {

    ImageView mediInfoImg;
    ToggleButton bookmarkBtn;
    TextView mediName, mediCompany, mediIngedient, mediTake, mediCaution, avgPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_info);

        mediInfoImg = (ImageView)findViewById(R.id.mediInfoImg);
        mediName = (TextView)findViewById(R.id.mediName);
        mediCompany = (TextView)findViewById(R.id.mediCompany);
        mediIngedient = (TextView)findViewById(R.id.mediIngedient);
        mediTake = (TextView)findViewById(R.id.mediTake);
        mediCaution = (TextView)findViewById(R.id.mediCaution);
        avgPrice = (TextView)findViewById(R.id.avgPrice);

        //즐겨찾기 버튼 토글
        bookmarkBtn = (ToggleButton)findViewById(R.id.bookmarkBtn);
        bookmarkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bookmarkBtn.isChecked()) {
                    bookmarkBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_yellow_star));
                    //즐겨찾기 화면에 약 추가
                    Intent goBookmarkIntent = new Intent(getApplicationContext(), BookmarkAdapter.class);

                    //약 이미지 intent
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.id.mediInfoImg);
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                    byte[] byteArray = outputStream.toByteArray();
                    goBookmarkIntent.putExtra("mediInfoImg", byteArray);

                    goBookmarkIntent.putExtra("mediName", mediName.toString());

                    startActivity(goBookmarkIntent);
                }
                else {
                    bookmarkBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_empty_star));
                    //즐겨찾기 화면에서 약 삭제
                    //의약품 명 intent 해서 이름으로 삭제
                    Intent deleteBmIntent = new Intent(getApplicationContext(), BookmarkAdapter.class);

                    deleteBmIntent.putExtra("deleteMedi", mediName.toString());
                    startActivity(deleteBmIntent);
                }
            }
        });
    }
}