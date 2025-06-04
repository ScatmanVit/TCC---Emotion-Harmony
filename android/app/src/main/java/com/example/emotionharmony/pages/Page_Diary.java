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

public class Page_Diary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_page_diary);
        // Vincula a barra de progresso
        progressBar = findViewById(R.id.progressBar);

        // Exibe a barra por 3 segundos
        progressBar.setVisibility(View.VISIBLE);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Esconde a barra após 3 segundos
                progressBar.setVisibility(View.GONE);
            }
        }, 3000); // 3000 milissegundos = 3 segundos
        // Ajusta os paddings com base nas barras do sistema (topo, lateral, inferior)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Configura o menu inferior para esta tela, ativando a navegação entre seções
        BottomMenuView bottomMenu = findViewById(R.id.bottomMenu);
        bottomMenu.setActivityContext(this);
        Button btnSairDiary = findViewById(R.id.btnLogoutDiary);
        Button btnStart = findViewById(R.id.btnStart_Water);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Page_Diary.this, PageWaterActivity.class);
                startActivity(intent);
            }
        });
    }
}
