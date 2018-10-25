package com.optativa.uce.tarea_04_g06;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import entity.Persona;



public class RegistroActivity extends AppCompatActivity {

    private EditText usuario,clave,nombre,apellido,email,celular;
    private Button ingreso,registro;
    private CheckBox check1,check2,check3,check4,check5 ;
    private RadioButton rb_masculino,rb_femenino;
    private Spinner sp1;
    private final String ARCHIVO = "data.obj";
    List<Persona> lista = new ArrayList<>();
    File dataFile = new File(Environment.getExternalStorageDirectory(), ARCHIVO);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        usuario = (EditText) findViewById(R.id.);
        clave = (EditText) findViewById(R.id.);
        nombre = (EditText) findViewById(R.id.);
        apellido = (EditText) findViewById(R.id.);
        email = (EditText) findViewById(R.id.);
        celular = (EditText) findViewById(R.id.);
        check1 = (CheckBox) findViewById(R.id.);
        check2 = (CheckBox) findViewById(R.id.);
        check3 = (CheckBox) findViewById(R.id.);
        check4 = (CheckBox) findViewById(R.id.);
        check5 = (CheckBox) findViewById(R.id.);
        rb_masculino = (RadioButton) findViewById(R.id.);
        rb_femenino = (RadioButton) findViewById(R.id.);
        sp1 = (Spinner) findViewById(R.id.);
        registro = (Button) findViewById(R.id.btnRegistro);

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrar();
            }
        });

    }

    private void registrar(){

        try{

            FileOutputStream out = new FileOutputStream(dataFile, true);
            ObjectOutputStream ost = new ObjectOutputStream(out);

            //MiObjectOutputStream ost = new MiObjectOutputStream(out);
            ost.writeObject(new Persona(usuario.getText().toString(), clave.getText().toString(),
                    nombre.getText().toString(), apellido.getText().toString(), email.getText().toString(),
                    celular.getText().toString(),"Masculino"));
            ost.close();
            Intent intent = new Intent(RegistroActivity.this, MainActivity.class);
            startActivity(intent);
            //System.out.println("Escrito Correctamente");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
