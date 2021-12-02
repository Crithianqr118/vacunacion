package com.example.vacunacionshark.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.vacunacionshark.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.Calendar;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button vlog_btn_salir,vlog_btn_ingresar;

    private EditText vlog_etext_dni,vlog_etxt_fechaNac,vlog_etxt_fechaEmi,txtDni;
    private int dia, mes, ano;
    private String txt_dni_scan;

    private ImageButton btn_scan_dni;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        vlog_etext_dni= findViewById(R.id.log_etxt_dni);
        vlog_etxt_fechaNac= findViewById(R.id.log_etxt_fechaNac);
        vlog_etxt_fechaEmi= findViewById(R.id.log_etxt_fechaEmi);
        vlog_btn_ingresar= findViewById(R.id.log_btn_ingresar);
        vlog_btn_salir= findViewById(R.id.log_btn_salir);
        btn_scan_dni = findViewById(R.id.btn_scan);



        vlog_etxt_fechaNac.setOnClickListener(this);
        vlog_etxt_fechaEmi.setOnClickListener(this);
        vlog_btn_ingresar.setOnClickListener(this);
        vlog_btn_salir.setOnClickListener(this);
        btn_scan_dni.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.log_etxt_fechaNac:
                selector_fechaNac();
                break;
            case R.id.log_etxt_fechaEmi:
                selector_fechaEmi();
                break;

            case R.id.log_btn_ingresar: validarSeion(vlog_etext_dni/*,vlog_etxt_fechaNac.getText().toString(),
                    vlog_etxt_fechaEmi.getText().toString() */); break;

            case R.id.log_btn_salir: salir(); break;

            case R.id.btn_scan:
                leerCodBarra(v);

                break;
        }


    }

    private void selector_fechaNac() {
        final Calendar calendar = Calendar.getInstance();
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        int mes = calendar.get(Calendar.MONTH);//0...11
        int año = calendar.get(Calendar.YEAR);
        //dialogo de selector de fechas
        DatePickerDialog selector = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                vlog_etxt_fechaNac.setText((dayOfMonth<10?"0"+dayOfMonth : dayOfMonth)+"/"+((month+1)<10?"0"+(month+1) : (month+1))+"/"+year);//2000-11-05
            }
        },año,mes,dia);
        selector.show();
    }
    private void selector_fechaEmi() {
        final Calendar calendar = Calendar.getInstance();
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        int mes = calendar.get(Calendar.MONTH);//0...11
        int año = calendar.get(Calendar.YEAR);
        //dialogo de selector de fechas
        DatePickerDialog selector = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                vlog_etxt_fechaEmi.setText((dayOfMonth<10?"0"+dayOfMonth : dayOfMonth)+"/"+((month+1)<10?"0"+(month+1) : (month+1))+"/"+year);//2000-11-05
            }
        },año,mes,dia);
        selector.show();
    }

    private void validarSeion(EditText dni/*,String fechaNac,String fechaEmi*/){
        if(dni.getText().toString().trim().equals("7")/*&&fechaNac.equals("27/09/1995")&&fechaEmi.equals("20/02/2019")*/){
            Intent iPrincipal = new Intent(this,MenuActivity.class);
            startActivity(iPrincipal);
            finish();
        }else{
            Toast.makeText(this,"No existe el usuario",Toast.LENGTH_LONG).show();

        }
    }

    private void validarSeion_scan(EditText dni){
        System.out.println("dni capturado: " + dni.getText().toString());
        if(dni.equals("7")){
            Intent iPrincipal = new Intent(this,MenuActivity.class);
            startActivity(iPrincipal);
            finish();
        }else{
            Toast.makeText(this,"No existe el usuario",Toast.LENGTH_LONG).show();
        }
    }

    private void salir(){
        System.exit(0);
        finish();
    }



    private void leerCodBarra(View view ){
        System.out.println("daad");
        IntentIntegrator integrator = new IntentIntegrator( LoginActivity.this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("lector - CDP");
        integrator.setCameraId(0);
        integrator.setOrientationLocked(true);
        integrator.setBeepEnabled(true);
        integrator.setBarcodeImageEnabled(true);
        integrator.initiateScan();

    }

    protected  void  onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result != null){
            if (result.getContents()==null){

                Toast.makeText(this, "Lectura cancelada",Toast.LENGTH_LONG).show();

            }else{
                Toast.makeText(this, result.getContents(),Toast.LENGTH_LONG).show();
                vlog_etext_dni.setText(result.getContents());
                System.out.println("DATO CAPTURADO:" +vlog_etext_dni.getText().toString());
                validarSeion(vlog_etext_dni);

            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}