package com.example.aplicativoavaliacao01;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;
import java.util.Random;

public class NumeroAleatorio extends AppCompatActivity {


    private Button btn_voltar, btn_gerar;
    private Dialog myDialog;
    public Animation atg, atg2,atg3;
    public  static Integer ini, fim_int;



    View.OnClickListener actionVoltar = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            Intent tela_resultado = new Intent(NumeroAleatorio.this,MainActivity.class);
            startActivity(tela_resultado);
        }
    };

    public  int aleatoriar(int minimo, int maximo) {
        Random random = new Random();
        return random.nextInt((maximo - minimo) + 1) + minimo;
    }
    public void animar()
    {
        atg = AnimationUtils.loadAnimation(this, R.anim.atg);
        atg2 = AnimationUtils.loadAnimation(this, R.anim.atg2);
        atg3 = AnimationUtils.loadAnimation(this, R.anim.atg3);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numero_aleatorio);

        myDialog = new Dialog(this);
        btn_voltar = findViewById(R.id.btn_voltar);
        btn_gerar = findViewById(R.id.btn_gerar);
        btn_gerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText inicio, fim;
                inicio = findViewById(R.id.inicio_faixa);
                fim = findViewById(R.id.fim_faixa);
                String str_ini , str_fim;
                str_ini = inicio.getText().toString();
                str_fim = fim.getText().toString();

                if(str_ini.matches("") || str_fim.matches(""))
                {
                    Toast.makeText(getApplicationContext(), "Preencha os campos!", Toast.LENGTH_SHORT).show();
                }
                else {

                        ini = Integer.parseInt(str_ini);
                        fim_int = Integer.parseInt(str_fim);


                        ShowPopup(v);
                }
            }
        });
        btn_voltar.setOnClickListener(actionVoltar);
    }

    public void ShowPopup(View v) {
        TextView txtclose,resultado_aleatorio;
        Button btnFollow;
        myDialog.setContentView(R.layout.custompopup);

        ImageView resultado =myDialog.findViewById(R.id.icone_resultado);
        TextView nome_bixo = myDialog.findViewById(R.id.nome_bixo);
        TextView nick_bixo = myDialog.findViewById(R.id.nick_bixo);
        LinearLayout box_msg = myDialog.findViewById(R.id.box_msg);
        txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
        btnFollow = (Button) myDialog.findViewById(R.id.btnfollow);
        resultado_aleatorio =myDialog.findViewById(R.id.resultado_aleatorio);

        if (ini>=fim_int)
        {
            resultado.setImageResource(R.drawable.falha);
            resultado_aleatorio.setText("Digite um intervalo válido, TROLA NÃO MANO! :(");

        }
        else
        {
            resultado.setImageResource(R.drawable.sucesso);
            resultado_aleatorio.setText("O número aleatório é: " + aleatoriar(ini,fim_int) + "  :)");
        }



        animar();

        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        resultado.startAnimation(atg);
        nome_bixo.startAnimation(atg2);
        nick_bixo.startAnimation(atg2);
        box_msg.startAnimation(atg2);
        myDialog.findViewById(R.id.mensagem).startAnimation(atg2);
        resultado_aleatorio.startAnimation(atg2);
        btnFollow.startAnimation(atg3);
        Objects.requireNonNull(myDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

}

