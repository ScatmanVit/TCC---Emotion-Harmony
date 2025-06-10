package com.example.emotionharmony.pages.diary;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageView;


import com.example.emotionharmony.R;
import com.example.emotionharmony.pages.Page_Diary;

public class PageHabit extends AppCompatActivity
{
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_habits);
        ImageView btnBack12 = findViewById(R.id.btnBack12);
        btnBack12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PageHabit.this, Page_Diary.class);
                startActivity(intent);
            }
        });
    }
}
