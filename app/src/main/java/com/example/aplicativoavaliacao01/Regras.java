package com.example.aplicativoavaliacao01;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Regras extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regras);
        Typeface MR = Typeface.createFromAsset(getAssets(),"fonts/MR.ttf");

        TextView textRegras = findViewById(R.id.textRegras);

        textRegras.setTypeface(MR);
        Button btn_voltar = findViewById(R.id.botao_voltar_regras);
        btn_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Regras.this, Jogo_dificil.class);
                startActivity(i);
            }
        });
    }
}
