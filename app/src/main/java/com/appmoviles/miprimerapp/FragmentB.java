package com.appmoviles.miprimerapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;


public class FragmentB extends Fragment implements View.OnClickListener {

    private EditText et_libreta_nombre;
    private EditText et_libreta_numero;
    private Button btn_libreta_agregar;
    private RecyclerView libreta;
    private AdapterTemplate adapter;

    public FragmentB() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_b, container, false);
        et_libreta_nombre = v.findViewById(R.id.et_libreta_nombre);
        et_libreta_numero = v.findViewById(R.id.et_libreta_numero);
        btn_libreta_agregar = v.findViewById(R.id.btn_libreta_agregar);
        libreta = v.findViewById(R.id.libreta);
        libreta.setHasFixedSize(true);
        LinearLayoutManager manager =  new LinearLayoutManager(getContext());
        libreta.setLayoutManager(manager);
        adapter = new AdapterTemplate();
        libreta.setAdapter(adapter);

        btn_libreta_agregar.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_libreta_agregar:
                Persona persona = new Persona(et_libreta_nombre.getText().toString(), et_libreta_numero.getText().toString());
                adapter.agregarPersona(persona);
                break;
        }
    }
}