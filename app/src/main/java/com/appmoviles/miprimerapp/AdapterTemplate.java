package com.appmoviles.miprimerapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterTemplate extends RecyclerView.Adapter<AdapterTemplate.CustomViewHolder> {

    // Los datos que vamos a mostrar (View)
    private ArrayList<Persona> data;

    // Representa un renglón de la lista, es su constructor
    public static class CustomViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout root;
        public CustomViewHolder(LinearLayout v) {
            super(v);
            root = v;
        }
    }

    public AdapterTemplate(){
        data = new ArrayList<Persona>();
    }

    // Se crea visualmente el renglón
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // el último parametro define si se desea agregar a la clase padre después de creado
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.renglon, parent, false);
        CustomViewHolder vh = new CustomViewHolder(v);
        return vh;
    }

    // Se utiliza(llena) el renglón, mapeo
    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        ((TextView) holder.root.findViewById(R.id.row_nombre)).setText(data.get(position).nombre);
        ((TextView) holder.root.findViewById(R.id.row_numero)).setText(data.get(position).numero);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void agregarPersona(Persona persona){
        data.add(persona);
        notifyDataSetChanged();
    }

    public void eliminarPersona(Persona persona){
        data.remove(persona);
        notifyDataSetChanged();
    }

}
