package com.example.emotionharmony.pages.diary;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.Button;
import android.content.Intent;
import android.widget.ImageView;
import android.app.TimePickerDialog;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Calendar;
import java.util.Locale;



import com.example.emotionharmony.R;
import com.example.emotionharmony.classes.UserData;
import com.example.emotionharmony.components.BottomMenuView;
import com.example.emotionharmony.databinding.ActivityPageDiaryBinding;
import com.example.emotionharmony.databinding.ActivityPageExerciciesBinding;
import com.example.emotionharmony.pages.Page_Diary;

public class PageWater extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_water);
        ImageView btnBack11 = findViewById(R.id.btnBack11);
        EditText txtHoraInicio = findViewById(R.id.txtHoraInicio);
        EditText txtHoraFim = findViewById(R.id.txtHoraFim);
        Button btnPronto = findViewById(R.id.btnPronto);
        EditText txtPeso = findViewById(R.id.txtPeso);

        txtHoraInicio.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(
                    PageWater.this,
                    (view, hourOfDay, minute1) -> {
                        String time = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute1);
                        txtHoraInicio.setText(time);
                    }, hour, minute, true
            );
            timePickerDialog.show();
        });

        txtHoraFim.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(
                    PageWater.this,
                    (view, hourOfDay, minute1) -> {
                        String time = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute1);
                        txtHoraFim.setText(time);
                    }, hour, minute, true
            );
            timePickerDialog.show();
        });
        btnBack11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PageWater.this, Page_Diary.class);
                startActivity(intent);
            }
        });

        btnPronto.setOnClickListener(v -> {
            String peso = txtPeso.getText().toString().trim();
            String horaInicio = txtHoraInicio.getText().toString().trim();
            String horaFim = txtHoraFim.getText().toString().trim();

            if (peso.isEmpty()) {
                Toast.makeText(PageWater.this, "O campo Peso não pode estar vazio", Toast.LENGTH_SHORT).show();
                return;
            }
            else if (horaInicio.isEmpty()) {
                Toast.makeText(PageWater.this, "Informe a hora de início", Toast.LENGTH_SHORT).show();
                return;
            }
            else if (horaFim.isEmpty()) {
                Toast.makeText(PageWater.this, "Informe a hora de fim", Toast.LENGTH_SHORT).show();
                return;
            }
            else {
                // Salva os dados no singleton temporário
                UserData.setPeso(peso);
                UserData.setHoraInicio(horaInicio);
                UserData.setHoraFim(horaFim);

                Intent intent = new Intent(PageWater.this, Page_Diary.class);
                startActivity(intent);
            }
        });


        // Voltar
        btnBack11.setOnClickListener(v -> {
            Intent intent = new Intent(PageWater.this, Page_Diary.class);
            startActivity(intent);
        });
    }
}
