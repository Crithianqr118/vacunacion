package com.example.vacunacionshark.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.vacunacionshark.R;

public class InicioActivity extends AppCompatActivity {
    ProgressBar v_pbCarga;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        v_pbCarga= findViewById(R.id.ini_pb_Carga);

        Thread tMain = new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    Intent iLogin = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(iLogin);
                    finish();
                }
            }
        };
        tMain.start();




    }
}