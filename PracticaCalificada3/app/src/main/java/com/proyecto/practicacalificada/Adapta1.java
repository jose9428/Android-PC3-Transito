package com.proyecto.practicacalificada;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapta1 extends RecyclerView.Adapter<Adapta1.MyHolder> {
    List<Papeleta> lista;
    Context contexto;

    public Adapta1(List<Papeleta> lista, Context contexto) {
        this.lista = lista;
        this.contexto = contexto;
    }

    @NonNull
    @Override
    public Adapta1.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater infla = LayoutInflater.from(contexto);
        View vista = infla.inflate(R.layout.vista1 , parent , false);
        //return new MyHolder(vista);
        return new Adapta1.MyHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapta1.MyHolder holder, int position) {
        final Papeleta obj = lista.get(position);
        holder.fecha.setText(obj.getFecha());
        holder.multa.setText(""+obj.getMulta());
        holder.infraccion.setText(obj.getInfraccion());
        holder.papeleta.setText(""+obj.getNroPap());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView papeleta;
        TextView fecha;
        TextView multa;
        TextView infraccion;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            papeleta = itemView.findViewById(R.id.txtPapeleta);
            fecha = itemView.findViewById(R.id.txtFecha);
            infraccion = itemView.findViewById(R.id.txtInfraccion);
            multa = itemView.findViewById(R.id.txtMulta);
        }
    }
}
