package com.appmoviles.miprimerapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class app extends AppCompatActivity {

    private TextView tv_register;
    private Button bt_login;
    private EditText et_username;
    private EditText et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        Log.e("LifeCycle", "onCreate");

        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);

        tv_register = (TextView) findViewById(R.id.tv_register);
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(app.this, Register.class);
                startActivity(i);
            }
        });

        bt_login = findViewById(R.id.bt_login);
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(app.this, Profile.class);
                i.putExtra("username",et_username.getText().toString());
                i.putExtra("password",et_password.getText().toString());
                startActivity(i);

            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("SALIR")
                .setMessage("¿Desea salir?")
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        app.this.onBackPressed();
                        //System.exit(0);
                    }
                });
        builder.show();
    }
}
