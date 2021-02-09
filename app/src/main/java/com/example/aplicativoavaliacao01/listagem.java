package com.example.aplicativoavaliacao01;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class listagem extends AppCompatActivity {

    TextView txtclose;
    ListView lista_eventos;
    private int []imgs= {R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e,R.drawable.evento};

    public String pegar_eventos()
    {
        SharedPreferences preferences = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        return preferences.getString("eventos","");


    }
    public  int aleatoriar(int minimo, int maximo) {
        Random random = new Random();
        return random.nextInt((maximo - minimo) + 1) + minimo;
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
        setContentView(R.layout.activity_listagem);
        txtclose = findViewById(R.id.txtclose);
        lista_eventos = findViewById(R.id.lista_eventos);
        ArrayList<Evento> eventos;
        eventos = getEventos();
        if(eventos.size()>0)
        {
            //Toast.makeText(getApplicationContext(),"Apagado", Toast.LENGTH_SHORT).show();
            ListaCustomizada lista = new ListaCustomizada(this,R.layout.teste,eventos);
            lista_eventos.setAdapter(lista);
        }
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(listagem.this,CadEvento.class);
                startActivity(i);
            }
        });
    }
}
