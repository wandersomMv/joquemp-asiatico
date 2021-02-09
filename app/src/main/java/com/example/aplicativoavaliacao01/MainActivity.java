package com.example.aplicativoavaliacao01;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Type;

public class MainActivity extends AppCompatActivity {

    private TextView nomeUsuario, abaixoNomeUsuario,listar,arquivos,config,apps,menu,ajuda,subajuda;
    private ImageView img_invesor,eventos;
    private Button btncomecar,img_numero_aleatorio;
    private ImageView imagemComecar,img_listar;
    public Animation atg, atg2,atg3;

    public void pegar_elementos_tela()
    {
        nomeUsuario = findViewById(R.id.nomeUsuario);
        abaixoNomeUsuario = findViewById(R.id.abaixoNomeUsuario);
        listar = findViewById(R.id.textoAbaixoLista);
        arquivos = findViewById(R.id.textoAbaixoArquivo);
        config = findViewById(R.id.textoAbaixoConfig);
        apps = findViewById(R.id.textoAbaixoApps);
        menu = findViewById(R.id.menuPrincipalTexto);
        ajuda = findViewById(R.id.textoAbaixoAjuda);
        subajuda = findViewById(R.id.textoAbaixoSubAjuda);
        btncomecar = findViewById(R.id.botaoComecar);
        imagemComecar = findViewById(R.id.imageView);
        img_listar = findViewById(R.id.listar);

        img_numero_aleatorio = findViewById(R.id.img_numero_aleatorio);
        img_invesor = findViewById(R.id.inversor);
        eventos= findViewById(R.id.eventos);

    }
    public void animar_elemtos()
    {
        atg = AnimationUtils.loadAnimation(this, R.anim.atg);
        atg2 = AnimationUtils.loadAnimation(this, R.anim.atg2);
        atg3 = AnimationUtils.loadAnimation(this, R.anim.atg3);

        imagemComecar.startAnimation(atg);
        ajuda.startAnimation(atg2);
        subajuda.startAnimation(atg2);
        btncomecar.startAnimation(atg3);
    }
    public void setar_fontes()
    {
        //IMPORTAR AS FONTES DOS TEXTOS QUE IRÃO SER USADAS NA TELA MAIN
        Typeface ML = Typeface.createFromAsset(getAssets(),"fonts/ML.ttf");
        Typeface MM = Typeface.createFromAsset(getAssets(),"fonts/MM.ttf");
        Typeface MR = Typeface.createFromAsset(getAssets(),"fonts/MR.ttf");

        nomeUsuario.setTypeface(ML);
        abaixoNomeUsuario.setTypeface(MM);
        menu.setTypeface(MR);
        listar.setTypeface(MM);
        arquivos.setTypeface(MM);
        config.setTypeface(MM);
        apps.setTypeface(MM);
        ajuda.setTypeface(MR);
        subajuda.setTypeface(ML);
        btncomecar.setTypeface(MM);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // PEGAR OS TEXTOS NA TELA PRINCIPAL PARA SETAR AS FONTES
        pegar_elementos_tela();
        // Comecar as animações
        animar_elemtos();
        // SETAR A FONTE
        setar_fontes();
        btncomecar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tela_jogo = new Intent(MainActivity.this,Jogo_dificil.class);
                startActivity(tela_jogo);
            }
        });
        img_numero_aleatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aleatorio = new Intent(MainActivity.this,NumeroAleatorio.class);
                startActivity(aleatorio);
            }
        });

        img_invesor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inversor = new Intent(MainActivity.this,inversor.class);
                startActivity(inversor);
            }
        });
        eventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inversor = new Intent(MainActivity.this,CadEvento.class);
                startActivity(inversor);
            }
        });
        img_listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listar_event = new Intent(MainActivity.this,listagem.class);
                startActivity(listar_event);
            }
        });





    }

}
