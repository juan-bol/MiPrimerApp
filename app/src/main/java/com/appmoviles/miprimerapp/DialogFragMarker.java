package com.appmoviles.miprimerapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DialogFragMarker extends AppCompatDialogFragment {

    private EditText et_nombre_marcador;

    private DialogFragMarkerActions listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_marker_frag,null);
        builder.setView(view)
                .setTitle("Agregar Marcador")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(et_nombre_marcador.getText().toString().length()>0 && !et_nombre_marcador.getText().toString().equals("")){
                            Toast.makeText(getActivity(), "El nombre del marcador no debe ser nulo", Toast.LENGTH_SHORT).show();
                        } else {
                            String nombre_marcador = et_nombre_marcador.getText().toString();
                            listener.obtenerNombreMarcador(nombre_marcador);
                        }
                    }
                });

        et_nombre_marcador = view.findViewById(R.id.et_nombre_marcador);

        return builder.create();
    }

    public interface DialogFragMarkerActions{
        void obtenerNombreMarcador(String nombreMarcador);
    }


}