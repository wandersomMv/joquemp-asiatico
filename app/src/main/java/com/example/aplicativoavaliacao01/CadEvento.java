package com.example.aplicativoavaliacao01;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class CadEvento extends AppCompatActivity {
    private TextView txtfechar;
    private Button btn_close,btn_cad;
    private int []imgs= {R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e,R.drawable.evento};

    public  int aleatoriar(int minimo, int maximo) {
        Random random = new Random();
        return random.nextInt((maximo - minimo) + 1) + minimo;
    }
    View.OnClickListener actionHandle = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent main = new Intent(CadEvento.this,MainActivity.class);
            startActivity(main);
        }
    };
    View.OnClickListener tela_adicionar = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent main = new Intent(CadEvento.this,MainActivity.class);
            startActivity(main);
        }
    };
    View.OnClickListener tela_cad = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent form = new Intent(CadEvento.this,FormularioEvento.class);
            startActivity(form);
        }
    };
    void pegar_dados_activity()
    {
        txtfechar = findViewById(R.id.txtfechar);
        btn_close = findViewById(R.id.btn_close);
        btn_cad = findViewById(R.id.btn_cad);
    }
    public String pegar_eventos()
    {
        SharedPreferences preferences = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        return preferences.getString("eventos","");


    }
    public void deletarSharedPreferences()
    {
        SharedPreferences.Editor prefsEditor = getSharedPreferences("preferencias", 0).edit();
        prefsEditor.clear();
        prefsEditor.apply();
    }
    public ArrayList<Evento> getEventos()
    {
        ArrayList<Evento> eventos = new ArrayList<Evento>();
        //deletarSharedPreferences();
        String event_mem = pegar_eventos();

        if(!event_mem.matches(""))
        {
            String []event = event_mem.split("\n");
            for (String dados: event) {

                String []separar = dados.split(";");
                //Toast.makeText(getApplicationContext(), Integer.toString(separar.length)+ "String: "+ dados, Toast.LENGTH_SHORT).show();

                //Toast.makeText(getApplicationContext(), separar[4], Toast.LENGTH_SHORT).show();
                if(separar.length > 1)
                    eventos.add(new Evento(separar[0],imgs[aleatoriar(0,imgs.length-1)],separar[1], separar[2]+". Data:" + separar[3]+" ás "+ separar[4]));

            }

        }
        else
        {
            Toast.makeText(getApplicationContext(),"Apagado", Toast.LENGTH_SHORT).show();
        }
        return eventos;


       // ultimo_numero() + ";" + name + ";" + desc + ";" + date+ ";"+ hr + "\n"

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_evento);

        ListView lista_eventos = findViewById(R.id.lista_eventos);

        ArrayList<Evento> eventos;
        eventos = getEventos();
        if(eventos.size()>0)
        {
            //Toast.makeText(getApplicationContext(),"Apagado", Toast.LENGTH_SHORT).show();
            ListaCustomizada lista = new ListaCustomizada(this,R.layout.teste,eventos);
            lista_eventos.setAdapter(lista);
        }


        pegar_dados_activity();
        txtfechar.setOnClickListener(actionHandle);
        btn_close.setOnClickListener(actionHandle);
        btn_cad.setOnClickListener(tela_cad);


    }
}
