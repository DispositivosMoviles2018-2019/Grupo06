package com.example.josen.tarea_04_g06;

import android.content.Intent;
import android.os.Environment;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import entity.Persona;
import utils.MyDatePicker;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class RegistroActivity extends AppCompatActivity{

    private TextView usuario;
    private TextView clave;
    private TextView nombre;
    private TextView apellido;
    private TextView email;
    private TextView celular;
    private RadioButton radioMasculino;
    private RadioButton radioFemenino;
    private Button registro;
    //private DatePicker fecha;
    private Spinner dia;
    private Spinner mes;
    private Spinner anio;
    private Switch swBeca;
    private CheckBox checkProgramacion;
    private CheckBox checkAnalisis;
    private CheckBox checkAlgoritmos;
    private CheckBox checkCalidad;
    private CheckBox checkProtocolos;
    private final String ARCHIVO = "data.obj";

    List<Persona> lista = new ArrayList<>();
    File dataFile = new File(Environment.getExternalStorageDirectory(), ARCHIVO);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        usuario = (TextView) findViewById(R.id.txtUsuario);
        clave = (TextView) findViewById(R.id.txtClave);
        nombre = (TextView) findViewById(R.id.txtUsuarioMain);
        apellido = (TextView) findViewById(R.id.txtApellido);
        email = (TextView) findViewById(R.id.txtEmail);
        celular = (TextView) findViewById(R.id.txtCelular);
        radioMasculino = (RadioButton) findViewById(R.id.radioMasculino);
        radioFemenino = (RadioButton) findViewById(R.id.radioFemenino);
        registro = (Button) findViewById(R.id.btnRegistro);
        //fecha = (DatePicker) findViewById(R.id.spFecha);
        //dia =(Spinner)findViewById(R.id.spinner1);
        //mes =(Spinner)findViewById(R.id.spinner2);
        //anio =(Spinner)findViewById(R.id.spinner3);
        swBeca = (Switch) findViewById(R.id.swBeca);
        checkProgramacion = (CheckBox) findViewById(R.id.chbProgramacion);
        checkAnalisis = (CheckBox) findViewById(R.id.chbAnalisis);
        checkAlgoritmos = (CheckBox) findViewById(R.id.chbAlgoritmos);
        checkCalidad = (CheckBox) findViewById(R.id.chbCalidad);
        checkProtocolos = (CheckBox) findViewById(R.id.chbProtocolos);
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrar();
            }
        });
    }

    private void registrar(){

        try{
            List<String> materias = new ArrayList<>();
            if(checkProtocolos.isChecked() ){
                materias.add("Protocolos");
            }
            if(checkCalidad.isChecked()){
                materias.add("Calidad");
            }
            if(checkAlgoritmos.isChecked()){
                materias.add("Algoritmos");
            }
            if(checkAnalisis.isChecked()){
                materias.add("Analisis");
            }
            if(checkProgramacion.isChecked()){
                materias.add("Programacion");
            }
            String genero=null;
            Boolean beca=null;
            if(swBeca.isChecked()){
                beca=true;
            }else{
                beca=false;
            }
            if(radioMasculino.isChecked()){
                genero="Masculino";
            }else{
                genero = "Femenino";
            }

            //StringBuilder sb = new StringBuilder();
            //sb.append(spinner3.).append("/").append(fecha.getMonth()).append("/").append(fecha.getDayOfMonth());

            dia=(Spinner)findViewById(R.id.spinner1);
            ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.dia, android.R.layout.simple_spinner_item);
            dia.setAdapter(adapter);

            mes=(Spinner)findViewById(R.id.spinner2);
            ArrayAdapter<CharSequence> adapter2=ArrayAdapter.createFromResource(this, R.array.dia, android.R.layout.simple_spinner_item);
            mes.setAdapter(adapter2);

            anio=(Spinner)findViewById(R.id.spinner3);
            ArrayAdapter<CharSequence> adapter3=ArrayAdapter.createFromResource(this, R.array.dia, android.R.layout.simple_spinner_item);
            anio.setAdapter(adapter3);

            String dia = adapter.toString();
            String mes = adapter2.toString();
            String anio = adapter3.toString();
            String sb = dia + mes + anio;
            FileOutputStream out = new FileOutputStream(dataFile, true);
            ObjectOutputStream ost = new ObjectOutputStream(out);
            ost.writeObject(new Persona(usuario.getText().toString(), clave.getText().toString(),
                    nombre.getText().toString(), apellido.getText().toString(), email.getText().toString(),
                    celular.getText().toString(),genero, sb, beca, materias));
            ost.close();
            Intent intent = new Intent(RegistroActivity.this, MainActivity.class);
            startActivity(intent);
            //System.out.println("Escrito Correctamente");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void showDatePicker(View view){
        DialogFragment newFragment = new MyDatePicker();
        newFragment.show(getSupportFragmentManager(), "date picker");
    }

}
