package ec.edu.uce.vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.io.IOException;

import ec.edu.uce.controlador.AdapterVehiculos;
import ec.edu.uce.controlador.ArchivosConfig;

public class ListaVehiculos extends AppCompatActivity {

    private RecyclerView recyclerViewVehiculo;
    private AdapterVehiculos adapterVehiculos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_vehiculos);

        recyclerViewVehiculo = findViewById(R.id.vehiculosRecycler);
        recyclerViewVehiculo.setLayoutManager(new LinearLayoutManager(this));

        adapterVehiculos = new AdapterVehiculos(Login.vehiculos);
        recyclerViewVehiculo.setAdapter(adapterVehiculos);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_lista_vehiculos, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.insertarVehiculoMenu:
                RegistrarVehiculo.editar = false;
                Intent insertar = new Intent(this, RegistrarVehiculo.class);
                startActivity(insertar);
                finish();
                break;
            case R.id.persistirVehiculoMenu:
                try {
                    ArchivosConfig.persistir();
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
