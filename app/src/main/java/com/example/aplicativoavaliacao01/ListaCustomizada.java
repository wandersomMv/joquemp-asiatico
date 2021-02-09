package com.example.aplicativoavaliacao01;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

class ListaCustomizada extends ArrayAdapter<Evento> {

    private Context context;
    int resourceLayout;

    public ListaCustomizada(@NonNull  Context context, int resource, @NonNull ArrayList<Evento> eventos) {
        super(context, resource,eventos);

        this.context = context;
        this.resourceLayout = resource;
    }

    @NonNull

    @Override
    public View getView(int position, @Nullable  View convertView,  @NonNull ViewGroup parent) {


        int idimg;
        String titulo, subtitulo,identificador;

        identificador = getItem(position).getIdentificador();
        idimg = getItem(position).getIdimg();
        titulo = getItem(position).getTitulo();
        subtitulo = getItem(position).getSubtitulo();

        Evento evento = new Evento(identificador,idimg,titulo,subtitulo);

        LayoutInflater inflater = LayoutInflater.from(context);

        convertView = inflater.inflate(resourceLayout,parent,false);

        CircleImageView imagem = convertView.findViewById(R.id.idimg);
        TextView title = convertView.findViewById(R.id.titulo);
        TextView subt = convertView.findViewById(R.id.subtitulo);
        TextView id = convertView.findViewById(R.id.identificador);

        imagem.setImageResource(idimg);
        title.setText(titulo);
        subt.setText(subtitulo);
        id.setText(identificador);

        return convertView;

    }
}
