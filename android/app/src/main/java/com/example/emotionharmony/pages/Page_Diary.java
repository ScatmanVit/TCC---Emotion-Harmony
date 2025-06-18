package com.example.emotionharmony.pages;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import android.util.TypedValue;

import com.example.emotionharmony.R;
import com.example.emotionharmony.components.BottomMenuView;
import com.example.emotionharmony.databinding.ActivityPageDiaryBinding;
import com.example.emotionharmony.pages.diary.PageHabit;
import com.example.emotionharmony.pages.diary.PageWater;
import com.example.emotionharmony.utils.NavigationHelper;
import com.example.emotionharmony.utils.ServerConnection;
import com.google.type.DateTime;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Page_Diary extends AppCompatActivity {

    private CardView cardStartWater;
    private CardView cardProgressWater;
    private Button btnStartWater;
    private ProgressBar progressWater;
    private TextView textWaterAmount;
    private CardView cardAddHabit;
    private LinearLayout containerLayout;
    private Integer Weight;
    private String token, sleepTimeStart, sleepTimeEnd;

    public static class Habit {
        int id;
        String name;
        Date lastTime;

        public Habit(int id, String name, Date lastTime) {
            this.id = id;
            this.name = name;
            this.lastTime = lastTime;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Configura tela cheia
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        );

        ActivityPageDiaryBinding binding = ActivityPageDiaryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomMenuView bottomMenu = findViewById(R.id.bottomMenu);
        bottomMenu.setActivityContext(this);
        SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);

        token = preferences.getString("authToken", null);

        cardStartWater    = findViewById(R.id.waterCard_start);
        cardProgressWater = findViewById(R.id.waterCard_progress);
        btnStartWater     = findViewById(R.id.btnStart_Water);
        progressWater     = findViewById(R.id.progressWater);
        textWaterAmount   = findViewById(R.id.textWaterAmount);

        // ID CORRIGIDO: waterCard_habit em vez de cardHabit_water
        cardAddHabit = findViewById(R.id.waterCard_habit);
        containerLayout = findViewById(R.id.linearlayout);

        btnStartWater.setOnClickListener(v -> {
            Intent intent = new Intent(Page_Diary.this, PageWater.class);
            startActivity(intent);
            showWaterProgress(0);
        });

        Button btnStartHabit = findViewById(R.id.btnStart_Habit);
        btnStartHabit.setOnClickListener(v ->
                NavigationHelper.navigateTo(Page_Diary.this, PageHabit.class, true)
        );

        fetchDataUser();

        loadAndDisplayHabitData();
    }

    private ArrayList<Habit> fetchHabitsFromDb() {
        ArrayList<Habit> list = new ArrayList<>();
        list.add(new Habit(1, "Fumar", new Date(System.currentTimeMillis() - 85400000)));
        list.add(new Habit(2, "Beber", new Date(System.currentTimeMillis() - 3600000)));
        return list;
    }

    private void fetchDataUser(){
        ServerConnection.getRequestWithAuth("/user", token, new ServerConnection.ServerCallback() {
            @Override
            public void onSuccess(String response) {

            }

            @Override
            public void onError(String error) {

            }
        });
        ServerConnection.getRequestWithAuth("/user", token, new ServerConnection.ServerCallback() {
            @Override
            public void onSuccess(String response) {
                runOnUiThread(() -> {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);

                        Weight = jsonResponse.optInt("weight_user", 0);
                        sleepTimeStart=jsonResponse.optString("sleep_time_start");
                        sleepTimeEnd=jsonResponse.optString("sleep_time_end");

                        if(Weight > 0){
                            loadAndDisplayWaterData();
                        }

                    } catch (JSONException e) {
                        Log.e("Page_perfil", "❌ Erro JSON: " + e.getMessage());
                    }
                });
            }

            @Override
            public void onError(String error) {
                runOnUiThread(() -> Log.e("Page_perfil", "❌ Erro: " + error));
            }
        });
    }

    private int calculateWaterIntake(int weight) {
        return weight*45;
    }

    private void loadAndDisplayHabitData() {
        ArrayList<Habit> habits = fetchHabitsFromDb();

        if (habits.isEmpty()) {
            cardAddHabit.setVisibility(View.VISIBLE);
        } else {
            cardAddHabit.setVisibility(View.GONE);
            for (Habit habit : habits) {
                addHabitCard(habit);
            }
        }
    }

    private void addHabitCard(Habit habit) {
        // Clonar o template
        CardView template = findViewById(R.id.habitCardTemplate);
        CardView card = new CardView(this);

        // Copiar atributos do template
        card.setCardBackgroundColor(template.getCardBackgroundColor().getDefaultColor());
        card.setRadius(template.getRadius());
        card.setCardElevation(template.getCardElevation());

        // Configurar layout params
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) template.getLayoutParams();
        card.setLayoutParams(new LinearLayout.LayoutParams(params));

        // Clonar o conteúdo interno
        LinearLayout templateLayout = (LinearLayout) template.getChildAt(0);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(
                templateLayout.getPaddingLeft(),
                templateLayout.getPaddingTop(),
                templateLayout.getPaddingRight(),
                templateLayout.getPaddingBottom()
        );
        layout.setGravity(templateLayout.getGravity());

        // Clonar TextViews
        TextView title = new TextView(this);
        TextView templateTitle = templateLayout.findViewById(R.id.habitTitle);
        title.setTextSize(TypedValue.COMPLEX_UNIT_PX, templateTitle.getTextSize());
        title.setTextColor(templateTitle.getTextColors());
        title.setTypeface(templateTitle.getTypeface());
        title.setTextAlignment(templateTitle.getTextAlignment());
        title.setText(habit.name);

        TextView timeInfo = new TextView(this);
        TextView templateTime = templateLayout.findViewById(R.id.habitTimeInfo);
        timeInfo.setTextSize(TypedValue.COMPLEX_UNIT_PX, templateTime.getTextSize());
        timeInfo.setTextColor(templateTime.getTextColors());
        timeInfo.setTypeface(templateTime.getTypeface());
        timeInfo.setTextAlignment(templateTime.getTextAlignment());
        timeInfo.setText("Última vez que realizou foi há:\n" + getTimeDiffText(habit.lastTime));

        // Adicionar elementos ao layout
        layout.addView(title);
        layout.addView(timeInfo);
        card.addView(layout);

        // Adicionar ao container
        containerLayout.addView(card);
    }

    private String getTimeDiffText(Date lastTime) {
        long diffMillis = System.currentTimeMillis() - lastTime.getTime();
        long hours = TimeUnit.MILLISECONDS.toHours(diffMillis);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(diffMillis) % 60;
        return String.format(Locale.getDefault(), "%02dh %02dmin", hours, minutes);
    }

    private void loadAndDisplayWaterData() {
        try {
            int totalDiario = calculateWaterIntake(Weight); // Ex: 80kg * 45 = 3600ml
            int horasAcordadoTotal = fetchTotalIngeridoFromDb(); // Ex: 18h

            if (horasAcordadoTotal == 0) {
                Log.e("Page_Diary", "⚠ Não foi possível calcular as horas acordado.");
                return;
            }

            // Extrai hora e minuto da string ISO 8601: "2000-01-01T06:30:00.000Z"
            String[] horaMinuto = sleepTimeStart.substring(11, 16).split(":");
            int hora = Integer.parseInt(horaMinuto[0]);
            int minuto = Integer.parseInt(horaMinuto[1]);

            // Define hora de acordar como hoje, mas com a hora da string
            Calendar agora = Calendar.getInstance();
            Calendar acordou = Calendar.getInstance();
            acordou.set(Calendar.HOUR_OF_DAY, hora);
            acordou.set(Calendar.MINUTE, minuto);
            acordou.set(Calendar.SECOND, 0);
            acordou.set(Calendar.MILLISECOND, 0);

            // Se o horário de acordar estiver no futuro, então acordou ontem
            if (acordou.after(agora)) {
                acordou.add(Calendar.DAY_OF_YEAR, -1);
            }

            long minutosDesdeQueAcordou = TimeUnit.MILLISECONDS.toMinutes(
                    agora.getTimeInMillis() - acordou.getTimeInMillis()
            );

            // Cálculo da ingestão proporcional até o momento
            double aguaPorHora = (double) totalDiario / horasAcordadoTotal;
            double ingeridoPrevisto = aguaPorHora * (minutosDesdeQueAcordou / 60.0);
            int totalIngerido = (int) ingeridoPrevisto;

            Log.d("Page_Diary", "💧 Já deveria ter ingerido: " + totalIngerido + " ml");

            if (totalIngerido > 0) {
                showWaterProgress(totalIngerido);
            } else {
                cardStartWater.setVisibility(View.VISIBLE);
                cardProgressWater.setVisibility(View.GONE);
            }

        } catch (Exception e) {
            Log.e("Page_Diary", "❌ Erro em loadAndDisplayWaterData: " + e.getMessage());
        }
    }

    private void showWaterProgress(int totalIngerido) {
        int setMax = calculateWaterIntake(Weight);
        cardStartWater.setVisibility(View.GONE);
        cardProgressWater.setVisibility(View.VISIBLE);
        progressWater.setMax(setMax);
        progressWater.setProgress(totalIngerido);
        textWaterAmount.setText(totalIngerido + " ml");
    }

    private int fetchTotalIngeridoFromDb() {
        try {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
            sdf.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));

            Date horaQueAcorda = sdf.parse(sleepTimeStart);
            Date horaQueDorme = sdf.parse(sleepTimeEnd);

            long diffMillis;
            if (horaQueDorme.after(horaQueAcorda)) {
                diffMillis = horaQueDorme.getTime() - horaQueAcorda.getTime();
            } else {
                diffMillis = (horaQueDorme.getTime() + TimeUnit.DAYS.toMillis(1)) - horaQueAcorda.getTime();
            }

            long totalMinutosAcordado = TimeUnit.MILLISECONDS.toMinutes(diffMillis);
            long horas = totalMinutosAcordado / 60;

            return (int) horas;

        } catch (Exception e) {
            Log.e("Page_Diary", "❌ Erro ao calcular tempo acordado: " + e.getMessage());
            return 0;
        }
    }



}