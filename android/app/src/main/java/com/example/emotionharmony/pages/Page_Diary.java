// Tela de Diário de Emoções ou Atividades
package com.example.emotionharmony.pages;

// Importações necessárias
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.Button;
import android.content.Intent;


import com.example.emotionharmony.R;
import com.example.emotionharmony.components.BottomMenuView;
import com.example.emotionharmony.databinding.ActivityPageDiaryBinding;
import com.example.emotionharmony.databinding.ActivityPageExerciciesBinding;
import com.example.emotionharmony.pages.diary.PageHabit;
import com.example.emotionharmony.pages.diary.PageWater;
import com.example.emotionharmony.pages.meditation.Meditation_Page1;
import com.example.emotionharmony.pages.meditation.Meditation_Page2;
import com.example.emotionharmony.utils.NavigationHelper;

public class Page_Diary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
         * Configura o layout em tela cheia com barras de navegação ocultas
         */
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        );

        // Infla o layout da tela com view binding
        ActivityPageDiaryBinding binding = ActivityPageDiaryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        // Configura o menu inferior para esta tela, ativando a navegação entre seções
        BottomMenuView bottomMenu = findViewById(R.id.bottomMenu);
        bottomMenu.setActivityContext(this);
        Button btnSairDiary = findViewById(R.id.btnLogoutDiary);
        Button btnStart_water = findViewById(R.id.btnStart_Water);
        Button btnStart_habit = findViewById(R.id.btnStart_Habit);
        btnStart_water.setOnClickListener(v -> {
            Intent intent = new Intent(Page_Diary.this, PageWater.class);
            startActivity(intent);
        });
        btnStart_habit.setOnClickListener(v ->
                NavigationHelper.navigateTo(Page_Diary.this, PageHabit.class, true)
        );
    }
}
