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

public class inversor extends AppCompatActivity {

    TextView fechar_inversor;
    ImageView icone_inv;
    TextView nome_bixo_inv,nick_bixo_inv,texto_orientacao;
    EditText input_inversor;
    Button fechar_inv,btn_inveter;
    public Animation atg, atg2,atg3;
    private Dialog myDialog;
    String str_inverter;


    public void pegar_elementos_tela()
    {
        fechar_inversor = findViewById(R.id.fechar_inversor);
        icone_inv = findViewById(R.id.icone_inv);
        nome_bixo_inv = findViewById(R.id.nome_bixo_inv);
        nick_bixo_inv= findViewById(R.id.nick_bixo_inv);
        texto_orientacao = findViewById(R.id.texto_orientacao);
        input_inversor = findViewById(R.id.input_inversor);
        fechar_inv = findViewById(R.id.fechar_inv);
        btn_inveter = findViewById(R.id.btn_inveter);
    }
    public void animar()
    {
        atg = AnimationUtils.loadAnimation(this, R.anim.atg);
        atg2 = AnimationUtils.loadAnimation(this, R.anim.atg2);
        atg3 = AnimationUtils.loadAnimation(this, R.anim.atg3);
        fechar_inversor.startAnimation(atg);
        icone_inv .startAnimation(atg2);
        nome_bixo_inv .startAnimation(atg2);
        nick_bixo_inv.startAnimation(atg2);
        texto_orientacao .startAnimation(atg2);
        input_inversor .startAnimation(atg3);
        fechar_inv .startAnimation(atg3);
        btn_inveter.startAnimation(atg3);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inversor);

        myDialog = new Dialog(this);
        pegar_elementos_tela();
        animar();
        btn_inveter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String str_inv = input_inversor.getText().toString();
                if(str_inv.matches(""))
                {
                    Toast.makeText(getApplicationContext(), "Preencha os campos!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    str_inverter = str_inv;
                    ShowPopup(v);
                }
            }
        });
        fechar_inv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(inversor.this,MainActivity.class);
                startActivity(main);
            }
        });
        fechar_inversor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(inversor.this,MainActivity.class);
                startActivity(main);
            }
        });

    }
    public void ShowPopup(View v) {
        TextView txtclose,nome_bixo,nick_bixo,mensagem,resultado_aleatorio;
        Button btnFollow;
        ImageView icone_resultado;
        LinearLayout box_msg;

        myDialog.setContentView(R.layout.popupinversor);
        txtclose =(TextView) myDialog.findViewById(R.id.txtfechar);
        icone_resultado = (ImageView) myDialog.findViewById(R.id.icone_resultado);
        nome_bixo = (TextView) myDialog.findViewById(R.id.nome_bixo);
        nick_bixo =(TextView) myDialog.findViewById(R.id.nick_bixo);
        box_msg = (LinearLayout) myDialog.findViewById(R.id.box_msg);
        mensagem = (TextView)myDialog.findViewById(R.id.mensagem);
        resultado_aleatorio = (TextView)myDialog.findViewById(R.id.resultado_aleatorio);
        btnFollow = (Button) myDialog.findViewById(R.id.btnfollow);

        String fraseInvertida = new StringBuilder(str_inverter).reverse().toString();
        resultado_aleatorio.setText(fraseInvertida);
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


        txtclose.startAnimation(atg2);
        icone_resultado .startAnimation(atg2);
        nome_bixo.startAnimation(atg2);
        nick_bixo .startAnimation(atg2);
        box_msg.startAnimation(atg2);
        mensagem .startAnimation(atg3);
        resultado_aleatorio .startAnimation(atg3);
        btnFollow .startAnimation(atg3);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }
}
