package ec.edu.uce.vista;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ec.edu.uce.modelo.Vehiculo;

public class RegistrarVehiculo extends AppCompatActivity {

    public static boolean editar = false;
    EditText placa;
    EditText marca;
    EditText costo;
    Switch matriculado;
    EditText color;
    TextView fecha;
    Date fechaFabricacion = new Date();
    DatePickerDialog.OnDateSetListener fechaDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_vehiculo);

        placa = (EditText) findViewById(R.id.placaVehiculoText);
        marca = (EditText) findViewById(R.id.marcaVehiculoText);
        costo = (EditText) findViewById(R.id.costoVehiculoText);
        color = (EditText) findViewById(R.id.colorVehiculoText);

        fecha = (TextView) findViewById(R.id.fechaFabVehiculoDate);

        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int año = calendar.get(Calendar.YEAR);
                int mes = calendar.get(Calendar.MONTH);
                int dia = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(RegistrarVehiculo.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, fechaDialog, año, mes, dia);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        fechaDialog = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                fechaFabricacion.setTime(calendar.getTimeInMillis());

                fecha.setText("Fecha de Fabricacion: " + date);
            }
        };

        matriculado = (Switch) findViewById(R.id.matriculadoSwitch);

        if (editar == true) {
            String placa = getIntent().getStringExtra("valorPlaca");
            System.out.println(placa);
            editarCampos(placa);
        }

    }

    private void editarCampos(String placa) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");

        List<Vehiculo> vehiculos = Login.vehiculos;

        for (Vehiculo v : vehiculos) {

            if (v.getPlaca().equals(placa)) {
                this.placa.setFocusable(false);
                this.placa.setText(v.getPlaca());
                this.marca.setText(v.getMarca());
                this.fechaFabricacion = v.getFechaFabricacion();
                this.fecha.setText("Fecha de fabricacion: " + sdf.format(this.fechaFabricacion).toString());
                this.costo.setText(v.getCosto().toString());
                if (v.getMatriculado() == true) {
                    this.matriculado.setChecked(true);
                } else {
                    this.matriculado.setChecked(false);
                }
                this.color.setText(v.getColor());
                vehiculos.remove(v);
                break;
            }
        }
        Login.setVehiculos(vehiculos);
    }

    public void confirmarRegistro(View view) {
        List <Vehiculo> vehiculos;
        Vehiculo vehiculo = new Vehiculo();

        if (this.placa.getText().toString().isEmpty()){
            Toast.makeText(this, "Es necesario ingresar el campo placa", Toast.LENGTH_SHORT).show();
        } else {

            this.placa.setText(this.placa.getText().toString());
            Pattern patronPlaca = Pattern.compile("^[A-Z]{3}-\\d{4}$");
            Matcher matcher = patronPlaca.matcher(this.placa.getText().toString());
            Boolean placaExistente = false;

            if (matcher.matches()){
                for (Vehiculo v : Login.vehiculos){
                    if (this.placa.getText().toString().equals(v.getPlaca()));
                    placaExistente = true;
                }

                //if (!placaExistente){
                    String placa = this.placa.getText().toString();
                    String marca = this.marca.getText().toString();
                    String costo = this.costo.getText().toString();
                    String color = this.color.getText().toString();
                    vehiculo.setFechaFabricacion(fechaFabricacion);
                    Boolean matriculado;
                    if (this.matriculado.isChecked() == true){
                        matriculado = true;
                    } else{
                        matriculado = false;
                    }

                    vehiculo.setPlaca(placa);
                    vehiculo.setMarca(marca);
                    vehiculo.setCosto(Double.parseDouble(costo));
                    vehiculo.setMatriculado(matriculado);
                    vehiculo.setColor(color);

                    vehiculos = Login.vehiculos;
                    vehiculos.add(vehiculo);
                    Login.setVehiculos(vehiculos);
                    Intent listaVehiculos = new Intent(this, ListaVehiculos.class);
                    startActivity(listaVehiculos);
                //} else {
                //    Toast.makeText(this, "Placa ya existe", Toast.LENGTH_SHORT).show();
                //}
            }else {
                Toast.makeText(this, "Formato de placa incorrecto", Toast.LENGTH_LONG).show();
            }


        }

    }

    public void createJSON(){

    }
}
