package ec.edu.uce.vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.io.IOException;

import ec.edu.uce.controlador.RecyclerViewAdaptador;

public class ListaVehiculos extends AppCompatActivity {

    private RecyclerView reciclerViewVehiculo;
    private RecyclerViewAdaptador adaptadorVehiculo;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_vehiculos);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        reciclerViewVehiculo = (RecyclerView) findViewById(R.id.recycler_vehiculo);
        reciclerViewVehiculo.setLayoutManager(new LinearLayoutManager(this));

        adaptadorVehiculo = new RecyclerViewAdaptador(Login.vehiculos);
        reciclerViewVehiculo.setAdapter(adaptadorVehiculo);
    }

    public void nuevo(View view) {
        RegistrarVehiculo.modificacion = false;
        Intent siguiente;
        siguiente = new Intent(this, RegistrarVehiculo.class);
        startActivity(siguiente);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //MenuInflater inflater = getMenuInflater();
        //inflater.inflate(R.menu.menu_lista_vehiculos, menu);
        //return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_lista_vehiculos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            //case R.id.insertarVehiculoMenu:
            //    Intent insertar = new Intent(ListaVehiculos.this, RegistrarVehiculo.class);
            //    startActivity(insertar);
            //    break;
            case R.id.persistir:
                try {
                    Login.persistir();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.regresarLoginMenu:
                finish();
                break;
            case R.id.salirAppMenu:
                Intent salir = new Intent(Intent.ACTION_MAIN);
                salir.addCategory(Intent.CATEGORY_HOME);
                salir.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(salir);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
