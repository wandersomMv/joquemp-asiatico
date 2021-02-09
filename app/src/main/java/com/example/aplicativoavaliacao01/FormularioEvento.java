package com.example.aplicativoavaliacao01;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Objects;

public class FormularioEvento extends AppCompatActivity {

    EditText  nome_evento,data_evento,descricao_evento,hora_env;
    LinearLayout fundo_form;
    TextView txt_cad_event,txt_nome_event,txt_desc_event,data_txt,hora_txt;
    Calendar calendario;
    DatePickerDialog dialogCalendar;
    TimePickerDialog horaDialog;
    TextView txtclose ;
    String buffer;
    Button btn_cad_ev,btn_listar_ev,btn_fechar_ev;
    public Animation atg, atg2,atg3;
    public Dialog myDialog;

   public void animar(){
        atg = AnimationUtils.loadAnimation(this, R.anim.atg);
        atg2 = AnimationUtils.loadAnimation(this, R.anim.atg2);
        atg3 = AnimationUtils.loadAnimation(this, R.anim.atg3);
   }
    View.OnClickListener actiondata = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {

                calendario = Calendar.getInstance();
                int dia, mes, ano, hora, minuto;
                dia = calendario.get(Calendar.DAY_OF_MONTH);
                mes = calendario.get(Calendar.MONTH);
                ano = calendario.get(Calendar.YEAR);



                dialogCalendar = new DatePickerDialog(FormularioEvento.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String date = dayOfMonth+"/"+ (month+1) + "/" + year;
                        data_evento.setText(date);

                    }
                }, ano, mes,dia);
                //dialogCalendar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialogCalendar.show();



        }
    };
    View.OnClickListener actiohora = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int hora,minuto;
            calendario = Calendar.getInstance();
            hora = calendario.get(Calendar.HOUR);
            minuto =  calendario.get(Calendar.MINUTE);
            horaDialog = new TimePickerDialog(FormularioEvento.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    String horas;
                    horas= hourOfDay+ ":" + minute;
                    hora_env.setText(horas);

                }
            },hora,minuto,true);
            horaDialog.show();

        }
    };
    View.OnClickListener voltar_cad = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(FormularioEvento.this, CadEvento.class);
            startActivity(i);
        }
    };



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


        resultado.setImageResource(R.drawable.sucesso);
        resultado_aleatorio.setText(buffer);




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

    View.OnClickListener cadastrar_evento = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String name, desc, date,hr;

            name = nome_evento.getText().toString();
            desc = descricao_evento.getText().toString();
            date = data_evento.getText().toString();
            hr = hora_env.getText().toString();
            if(name.matches("") || desc.matches("") || date.matches("") || hr.matches(""))
            {
                Toast.makeText(getApplicationContext(), "Preencha os campos!", Toast.LENGTH_SHORT).show();
            }
            else
            {
                String gravar = ultimo_numero() + ";" + name + ";" + desc + ";" + date+ ";"+ hr + "\n";
                buffer = name + ", "+ desc+ ", " + date +" ás" + hr;
                gravar_eventos(gravar);
                //Toast.makeText(getApplicationContext(), "Cadastrado", Toast.LENGTH_SHORT).show();
                ShowPopup(v);

            }
        }
    };
    public  String ultimo_numero()
    {
        SharedPreferences preferences = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        String eventos = preferences.getString("eventos","");
        if(eventos.matches(""))
        {
            return "1";
        }
        else
        {
            int id = 2;
           String[] evnt=  eventos.split("\n");
            for(int i = evnt.length-1; i>=0;i--)
            {
                if(evnt[i].split(";").length > 2)
                {
                    id = Integer.parseInt(evnt[i].split(";")[0]);
                    id++;
                    return Integer.toString(id);
                }
            }

           return Integer.toString(id);

        }
    }
    public String pegar_eventos()
    {

        SharedPreferences preferences = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        return preferences.getString("eventos","");


    }
    public void gravar_eventos(String gravar)
    {
        SharedPreferences preferences = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        String salvo = pegar_eventos()  + gravar;
        SharedPreferences.Editor ed = preferences.edit();
        ed.putString("eventos",salvo);
        ed.apply();

    }
    public void pegar_elementos()
    {
        nome_evento = findViewById(R.id.nome_evento);
        data_evento = findViewById(R.id.data_evento);
        descricao_evento = findViewById(R.id.descricao_evento);
        hora_env = findViewById(R.id.hora_env);
        txtclose = findViewById(R.id.txtclose);
        btn_cad_ev = findViewById(R.id.btn_cad_ev);
        txt_cad_event = findViewById(R.id.txt_cad_event);
        fundo_form = findViewById(R.id.fundo_form);
        txt_nome_event = findViewById(R.id.txt_nome_event);
        txt_desc_event = findViewById(R.id.txt_desc_event);
        data_txt = findViewById(R.id.data_txt);
        hora_txt = findViewById(R.id.hora_txt);
        btn_listar_ev = findViewById(R.id.btn_listar_ev);
        btn_fechar_ev = findViewById(R.id.btn_fechar_ev);


        animar();

        nome_evento.startAnimation(atg);
        data_evento .startAnimation(atg);
        descricao_evento .startAnimation(atg2);
        hora_env .startAnimation(atg2);
        txtclose .startAnimation(atg3);
        btn_cad_ev .startAnimation(atg3);
        txt_cad_event .startAnimation(atg2);
        fundo_form .startAnimation(atg2);
        txt_nome_event .startAnimation(atg3);
        txt_desc_event .startAnimation(atg2);
        data_txt .startAnimation(atg3);
        hora_txt .startAnimation(atg2);
        btn_listar_ev .startAnimation(atg3);
        btn_fechar_ev .startAnimation(atg3);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_evento);
        myDialog = new Dialog(this);
        pegar_elementos();

        data_evento.setOnClickListener(actiondata);
        hora_env.setOnClickListener(actiohora);
        txtclose.setOnClickListener(voltar_cad);
        btn_cad_ev.setOnClickListener(cadastrar_evento);

    }
}
