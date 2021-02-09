package com.example.aplicativoavaliacao01;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class TelaResultado extends AppCompatActivity {

    public Map<String, List<String>> regrasJogo =new HashMap<String, List<String>>();
    public Animation atg, atg2,atg3;
    Button btn_voltar;

    public void montar_regras_jogo()
    {

        // Primeira posição é o simbolo, a segunda é quem ele mata

        regrasJogo.put("agua", Arrays.asList("tesoura", "fogo","pedra","arma","trovao","demonio","dragao"));
        regrasJogo.put("ar",Arrays.asList("fogo","pedra","arma","trovao","demonio","dragao","agua"));
        regrasJogo.put("papel",Arrays.asList("pedra","arma","trovao","demonio","dragrao","agua","ar"));
        regrasJogo.put("esponja",Arrays.asList("arma","trovao","demonio","dragao","agua","ar","papel"));
        regrasJogo.put("lobo",Arrays.asList("trovao","demonio","dragao","agua","ar","papel","esponja"));
        regrasJogo.put("arvore",Arrays.asList("demonio","dragao","agua","ar","papel","esponja","lobo"));
        regrasJogo.put("humano",Arrays.asList("dragao","agua","ar","papel","esponja","lobo","arvore"));
        regrasJogo.put("cobra",Arrays.asList("agua","ar","papel","esponja","lobo","arvore","humano"));
        regrasJogo.put("tesoura",Arrays.asList("ar","papel","esponja","lobo","arvore","humano","cobra"));
        regrasJogo.put("fogo",Arrays.asList("papel","esponja","lobo","arvore","humano","cobra","tesoura"));
        regrasJogo.put("pedra",Arrays.asList("esponja","lobo","arvore","humano","cobra","tesoura","fogo"));
        regrasJogo.put("arma",Arrays.asList("lobo","arvore","humano","cobra","tesoura","fogo","pedra"));
        regrasJogo.put("trovao",Arrays.asList("arvore","humano","cobra","tesoura","fogo","pedra","arma"));
        regrasJogo.put("demonio",Arrays.asList("humano","cobra","tesoura","fogo","pedra","arma","trovao"));
        regrasJogo.put("dragao",Arrays.asList("cobra","tesoura","fogo","pedra","arma","trovao","demonio"));

    }
    public  int aleatoriar(int minimo, int maximo) {
        Random random = new Random();
        return random.nextInt((maximo - minimo) + 1) + minimo;
    }
    public String vetor_alternativas(int num)
    {
        String [] jogada = new String[]{"agua","ar","papel","esponja","lobo","arvore","humano",
                "cobra","tesoura","fogo","pedra","arma","trovao","demonio","dragao"};
        return jogada[num];

    }
    public int vetor_ganhou()
    {
        int [] ganhou = new int[]{R.drawable.ganhou1,R.drawable.ganhou2,R.drawable.ganhou3};
        return ganhou[aleatoriar(0,2)];
    }
    public int vetor_perdeu()
    {
        int [] perdeu = new int[]{R.drawable.perdeu1,R.drawable.perdeu2,R.drawable.perdeu3,
        R.drawable.perdeu4};
        return perdeu[aleatoriar(0,3)];
    }
    public int buscar_imagem(String img)
    {
        Map<String,Integer> imagens = new HashMap<String,Integer>();
        imagens.put("agua",R.drawable.agua);
        imagens.put("ar",R.drawable.ar);
        imagens.put("papel",R.drawable.papel);
        imagens.put("esponja",R.drawable.esponja);
        imagens.put("lobo",R.drawable.lobo);
        imagens.put("arvore",R.drawable.arvore);
        imagens.put("humano",R.drawable.humano);
        imagens.put("cobra",R.drawable.cobra);
        imagens.put("tesoura",R.drawable.tesoura);
        imagens.put("fogo",R.drawable.fogo);
        imagens.put("pedra",R.drawable.pedra);
        imagens.put("arma",R.drawable.arma);
        imagens.put("trovao",R.drawable.trovao);
        imagens.put("demonio",R.drawable.demonio);
        imagens.put("dragao",R.drawable.dragrao);

        return imagens.get(img);



    }
    void verificar_quem_ganhou(String simbolo)
    {


        String jogada_maquina = vetor_alternativas(aleatoriar(0,14)); // jogar um número aleatório

        List<String> verificar = regrasJogo.get(simbolo);


        TextView resultado = findViewById(R.id.resultado);
        ImageView imagemresultado = findViewById(R.id.imagem_resultado);
        TextView comentarios = findViewById(R.id.comentario);


        Typeface MR = Typeface.createFromAsset(getAssets(),"fonts/MR.ttf");
        if(verificar.contains(jogada_maquina))
        {
            imagemresultado.setImageResource(vetor_ganhou());
            resultado.setText("PARABÉNS VOCÊ GANHOU :)");
            comentarios.setText("Continue Jogando se divertindo.");

        }
        else
        {
            if(jogada_maquina.equals(simbolo))
            {
                imagemresultado.setImageResource(vetor_perdeu());
                resultado.setText("Você Empatou :| ");
                comentarios.setText("Continue Jogando que você irá ganhar.");
            }
            else {
                imagemresultado.setImageResource(vetor_perdeu());
                resultado.setText("Você perdeu :( ");
                comentarios.setText("Continue Jogando que você irá ganhar.");
            }
        }
        ImageView img_usu = findViewById(R.id.jogador);
        ImageView img_comp = findViewById(R.id.computador);

        img_usu.setImageResource(buscar_imagem(simbolo));
        img_comp.setImageResource(buscar_imagem(jogada_maquina));

        resultado.setTypeface(MR);
        comentarios.setTypeface(MR);
        atg = AnimationUtils.loadAnimation(this, R.anim.atg);
        atg2 = AnimationUtils.loadAnimation(this, R.anim.atg2);
        atg3 = AnimationUtils.loadAnimation(this, R.anim.atg3);

        imagemresultado.startAnimation(atg);
        resultado.startAnimation(atg2);
        comentarios.startAnimation(atg2);
        img_comp.startAnimation(atg2);
        img_usu.startAnimation(atg2);
        btn_voltar.startAnimation(atg3);

    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_resultado);

        Intent dados_tela_jogo = getIntent();
        String simbolo = dados_tela_jogo.getStringExtra("simbolo");

        montar_regras_jogo();
        TextView resultado = findViewById(R.id.resultado);
        resultado.setText(simbolo + Integer.toString(aleatoriar(0,14)));
        btn_voltar = findViewById(R.id.botao_voltar);
        verificar_quem_ganhou(simbolo);
        btn_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TelaResultado.this, Jogo_dificil.class);
                startActivity(i);
            }
        });
    }
}
