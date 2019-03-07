package com.appmoviles.miprimerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Profile extends AppCompatActivity {

    private RelativeLayout root_profile;
    private Button btn_salir_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        root_profile = findViewById(R.id.profile_root);
        btn_salir_profile = findViewById(R.id.btn_salir_profile);

        btn_salir_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Profile.this, app.class);
                startActivity(i);
            }
        });

        root_profile.setOnTouchListener(new View.OnTouchListener() {

            float Xini = 0;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        btn_salir_profile.setText("DOWN: "+event.getX()+", "+event.getY());
                        break;
                    case MotionEvent.ACTION_MOVE:
                        btn_salir_profile.setText("MOVE: "+event.getX()+", "+event.getY());
                        break;
                    case MotionEvent.ACTION_UP:
                        float deltaX = event.getX()-Xini;
                        if(deltaX > 500){
                            Intent i = new Intent(Profile.this, MainProfile.class);
                            startActivity(i);
                        }
                        if(deltaX < -500) {
                            finish();
                        }
                        btn_salir_profile.setText("UP: "+event.getX()+", "+event.getY());
                        break;
                }
                return true;
            }
        });

        Intent datos = getIntent();
        String username = datos.getExtras().getString("username");
        String password= datos.getExtras().getString("password");

        Toast.makeText(this, username+" "+password, Toast.LENGTH_LONG).show();


    }
}
