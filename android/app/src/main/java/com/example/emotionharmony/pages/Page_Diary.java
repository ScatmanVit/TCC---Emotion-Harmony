package com.example.emotionharmony.pages;

// Importações necessárias
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;

import com.example.emotionharmony.R;
import com.example.emotionharmony.components.BottomMenuView;
import com.example.emotionharmony.databinding.ActivityPageDiaryBinding;
import com.example.emotionharmony.pages.diary.PageHabit;
import com.example.emotionharmony.pages.diary.PageWater;
import com.example.emotionharmony.utils.NavigationHelper;

public class Page_Diary extends AppCompatActivity {

    private CardView cardStartWater;
    private CardView cardProgressWater;
    private Button btnStartWater;
    private ProgressBar progressWater;
    private TextView textWaterAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Configura tela cheia sem barra de navegação
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        );

        // Infla o layout com ViewBinding
        ActivityPageDiaryBinding binding = ActivityPageDiaryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Configura menu inferior
        BottomMenuView bottomMenu = findViewById(R.id.bottomMenu);
        bottomMenu.setActivityContext(this);

        // Inicializa views de água
        cardStartWater    = findViewById(R.id.waterCard_start);
        cardProgressWater = findViewById(R.id.waterCard_progress);
        btnStartWater     = findViewById(R.id.btnStart_Water);
        progressWater     = findViewById(R.id.progressWater);
        textWaterAmount   = findViewById(R.id.textWaterAmount);

        // Botão de hábito
        Button btnStartHabit = findViewById(R.id.btnStart_Habit);

        // Clique em iniciar água
        btnStartWater.setOnClickListener(v -> {
            // Navega para PageWater
            Intent intent = new Intent(Page_Diary.this, PageWater.class);
            startActivity(intent);

            // Após iniciar, já mostra o progresso (exemplo simples)
            showWaterProgress(0);
        });

        // Clique em hábito
        btnStartHabit.setOnClickListener(v ->
                NavigationHelper.navigateTo(Page_Diary.this, PageHabit.class, true)
        );

        // Ao iniciar Activity, carrega dados do banco e atualiza UI
        loadAndDisplayWaterData();
    }

    /**
     * Carrega total ingerido do banco e atualiza visibilidade
     */
    private void loadAndDisplayWaterData() {
        int totalIngerido = fetchTotalIngeridoFromDb(); // implemente método de acesso ao DB
        if (totalIngerido > 0) {
            showWaterProgress(totalIngerido);
        } else {
            // mantém botão de iniciar visível
            cardStartWater.setVisibility(View.VISIBLE);
            cardProgressWater.setVisibility(View.GONE);
        }
    }

    /**
     * Exibe o card de progresso e atualiza valores
     */
    private void showWaterProgress(int totalIngerido) {
        cardStartWater.setVisibility(View.GONE);
        cardProgressWater.setVisibility(View.VISIBLE);
        progressWater.setMax(2000); // ou seu valor máximo
        progressWater.setProgress(totalIngerido);
        textWaterAmount.setText(totalIngerido + " ml");
    }

    /**
     * Simula busca no banco de dados (substitua pela implementação real)
     */
    private int fetchTotalIngeridoFromDb() {
        // TODO: buscar do banco. Exemplo estático para teste:
        return 0;
    }
}
