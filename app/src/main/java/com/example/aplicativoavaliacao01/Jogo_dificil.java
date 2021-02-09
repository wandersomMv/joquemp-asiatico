package com.example.aplicativoavaliacao01;

import android.content.Intent;
import android.graphics.Typeface;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Jogo_dificil extends AppCompatActivity {

    private TextView tituloPag,subTitulo;
    private Button btn_agua, btn_ar, btn_humano,btn_arvore,btn_cobra;
    private Button btn_demonio, btn_dragao, btn_esponja,btn_arma,btn_fogo;
    private Button btn_lobo, btn_papel, btn_pedra, btn_tesoura, btn_trovao , btn_regras,btn_jogar,btn_voltar;
    private View holderbg, dynamicbg;
    public  int posbackground;


    View.OnClickListener actionJogar = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            String [] jogada = new String[]{"agua","ar","humano","arvore","cobra","demonio","dragao",
                    "esponja","arma","fogo","lobo","papel","pedra","tesoura","trovao"};
            //Toast.makeText(getApplicationContext(), "Posicao: " + posbackground, Toast.LENGTH_SHORT).show();
            Intent tela_resultado = new Intent(Jogo_dificil.this,TelaResultado.class);
            tela_resultado.putExtra("simbolo",jogada[posbackground]);
            startActivity(tela_resultado);


        }
    };


    // função para voltar o tamanho dos botões padrão

    public Button [] vetor_botoes()
    {
        return new Button[]{btn_agua, btn_ar, btn_humano,btn_arvore,btn_cobra,btn_demonio, btn_dragao, btn_esponja,btn_arma,btn_fogo
                ,btn_lobo, btn_papel, btn_pedra, btn_tesoura, btn_trovao};
    }
    public Integer[] planos_de_fundo()
    {
        return  new Integer[]{R.drawable.bgazul,R.drawable.bgverde,R.drawable.bglaranja,R.drawable.bgverdeescuro,
        R.drawable.bgamarelo, R.drawable.bgroxo,R.drawable.laranjaescuro,R.drawable.bglaranja,
        R.drawable.cinza,R.drawable.bgamarelo,R.drawable.bgmarron,R.drawable.bgroxo,
        R.drawable.bgverdeescuro,R.drawable.laranjaescuro,R.drawable.bgroxo};
    }
    public void animacao_padrao(int id)
    {


        final Integer [] backgounds = planos_de_fundo();
        int cont  = 0;


        Button []botoes = vetor_botoes();
        // passar por todos os botoes
        for (Button botao:botoes)
        {
            if(botao.getId() == id) {
                botao.animate().translationY(20).scaleX(1.7f).scaleY(1.7f).setDuration(800).start();

                break;
            }
        }
        for (Button botoe : botoes) {
            if (botoe.getId() != id) {
                botoe.animate().scaleX(1).scaleY(1).setDuration(350).start(); // voltar o bottão do tamanho original
                cont++;
            }else
            {
                posbackground = cont;
            }
        }
        dynamicbg.animate().scaleX(3).scaleY(3).setDuration(800).start();
        dynamicbg.setBackgroundResource(backgounds[posbackground]); // setar a cor correta do background

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                holderbg.setScaleX(3);
                holderbg.setScaleY(3);
                holderbg.setBackgroundResource(backgounds[posbackground]);
                dynamicbg.setScaleX(0);
                dynamicbg.setScaleY(0);

            }
        },850);

    }

    public void setar_on_click_listener()
    {
        btn_agua.setOnClickListener(actionHandle);
        btn_ar.setOnClickListener(actionHandle);
        btn_humano.setOnClickListener(actionHandle);
        btn_arvore.setOnClickListener(actionHandle);
        btn_cobra.setOnClickListener(actionHandle);
        btn_demonio.setOnClickListener(actionHandle);
        btn_dragao.setOnClickListener(actionHandle);
        btn_esponja.setOnClickListener(actionHandle);
        btn_arma.setOnClickListener(actionHandle);
        btn_fogo.setOnClickListener(actionHandle);
        btn_lobo.setOnClickListener(actionHandle);
        btn_papel.setOnClickListener(actionHandle);
        btn_pedra.setOnClickListener(actionHandle);
        btn_tesoura.setOnClickListener(actionHandle);
        btn_trovao.setOnClickListener(actionHandle);
        btn_jogar.setOnClickListener(actionJogar);
        btn_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent i = new Intent(Jogo_dificil.this, MainActivity.class);
                 startActivity(i);
            }
        });
    }

    // Função para na hora de fazer o click
    View.OnClickListener actionHandle = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            animacao_padrao(v.getId());
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jogo_dificil);

        pegar_botoes_jogo(); // pegar elementos da tela
        setar_fontes(); // setar as fontes do aplicativo

        holderbg = findViewById(R.id.holderbg);
        dynamicbg = findViewById(R.id.dynamicbg);
        posbackground = 2;
        holderbg.setBackgroundResource(planos_de_fundo()[posbackground]);
        holderbg.setScaleX(3);
        holderbg.setScaleY(3);

        btn_humano.setScaleX(1.7f);
        btn_humano.setScaleY(1.7f);
        
        setar_on_click_listener();
        btn_regras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Jogo_dificil.this, Regras.class);
                startActivity(i);
            }
        });



    }


    public void setar_fontes()
    {
        //IMPORTAR AS FONTES DOS TEXTOS QUE IRÃO SER USADAS NA TELA MAIN
        Typeface ML = Typeface.createFromAsset(getAssets(),"fonts/ML.ttf");
        Typeface MM = Typeface.createFromAsset(getAssets(),"fonts/MM.ttf");
        Typeface MR = Typeface.createFromAsset(getAssets(),"fonts/MR.ttf");

        tituloPag.setTypeface(MR);
        subTitulo.setTypeface(ML);

        btn_agua.setTypeface(MM);
        btn_ar.setTypeface(MM);
        btn_humano.setTypeface(MM);
        btn_arvore.setTypeface(MM);
        btn_cobra.setTypeface(MM);
        btn_demonio.setTypeface(MM);
        btn_dragao.setTypeface(MM);
        btn_esponja.setTypeface(MM);
        btn_fogo.setTypeface(MM);
        btn_lobo.setTypeface(MM);
        btn_papel.setTypeface(MM);
        btn_tesoura.setTypeface(MM);
        btn_pedra.setTypeface(MM);
        btn_trovao.setTypeface(MM);
        btn_regras.setTypeface(MM);
        btn_arma.setTypeface(MM);



    }
    // Pegar todos os elemtos da tela
    public void pegar_botoes_jogo()
    {
        // pegar todos botoes da tela do jogo

        tituloPag = findViewById(R.id.textView);
        subTitulo = findViewById(R.id.textView2);

        btn_agua = findViewById(R.id.btn_agua);
        btn_ar = findViewById(R.id.btn_ar);
        btn_humano = findViewById(R.id.btn_humano);
        btn_arvore = findViewById(R.id.btn_arvore);
        btn_cobra = findViewById(R.id.btn_cobra);
        btn_demonio = findViewById(R.id.btn_demonio);
        btn_dragao = findViewById(R.id.btn_dragao);
        btn_esponja = findViewById(R.id.btn_esponja);
        btn_arma = findViewById(R.id.btn_arma);
        btn_fogo = findViewById(R.id.btn_fogo);
        btn_lobo = findViewById(R.id.btn_lobo);
        btn_papel = findViewById(R.id.btn_papel);
        btn_tesoura = findViewById(R.id.btn_tesoura);
        btn_pedra = findViewById(R.id.btn_pedra);
        btn_trovao = findViewById(R.id.btn_trovao);
        btn_regras = findViewById(R.id.btn_regras);
        btn_jogar = findViewById(R.id.btn_jogar);
        btn_voltar = findViewById(R.id.btn_voltar);
    }

}
