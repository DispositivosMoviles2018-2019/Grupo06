package ec.edu.uce.vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import ec.edu.uce.controlador.ArchivosConfig;
import ec.edu.uce.modelo.Usuario;
import ec.edu.uce.modelo.Vehiculo;

public class Login extends AppCompatActivity {

    EditText usuario;
    EditText contraseña;
    public static List<Usuario> usuarios;
    public static List<Vehiculo> vehiculos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuarios = new ArrayList();
        vehiculos = new ArrayList();

        usuario = (EditText) findViewById(R.id.usuarioLoginText);
        contraseña = (EditText) findViewById(R.id.contraseñaLoginText);

    }

    public void registrarLogin(View view){
        Intent regitsrarLogin = new Intent(Login.this, RegistrarUsuario.class);
        startActivity(regitsrarLogin);
    }

    public void iniciarLogin(View view){
        ArchivosConfig arch = new ArchivosConfig();

        for (Object object : arch.leerUsuario("/archivos/","usuarios")){
            Usuario u = (Usuario) object;
            usuarios.add(u);
        }

        for (Object object : arch.leerVehiculo("/archivos/","vehiculos")){
            Vehiculo v = (Vehiculo) object;
            vehiculos.add(v);
        }

        if (vehiculos.isEmpty()){
            cargarVehiculos();
        }

        String usuario = this.usuario.getText().toString();
        String contraseña = this.contraseña.getText().toString();

        Usuario usu = buscarUsuario(usuario,contraseña, usuarios);

        if (usu!=null) {
            Intent login = new Intent(Login.this, ListaVehiculos.class);
            startActivity(login);
            finish();
        }else{
            Toast.makeText(Login.this,"Credenciales erroneas", Toast.LENGTH_SHORT).show();
        }

        /*
        for (Usuario u : usuarios){
            if (usuario.equals(u.getUsuario()) && contraseña.equals(u.getContraseña())){
                Intent iniciarSesion = new Intent(Login.this, ListaVehiculos.class);
                startActivity(iniciarSesion);
            }
        }*/
    }

    public Usuario buscarUsuario(String usuario, String contraseña,List<Usuario> listaUsuarios) {
        Usuario resultado = null;
        for (Usuario u : listaUsuarios) {
            System.out.println(u.toString());
            if ((u.getUsuario().equals(usuario)) || (u.getContraseña().equals(contraseña))) {
                resultado = u;
            }
        }
        return resultado;
    }

    public static void cargarVehiculos() {
        Vehiculo vehiculo = new Vehiculo();

        vehiculo.setMarca("Audi");
        vehiculo.setPlaca("XTR-9784");
        vehiculo.setColor("Negro");
        vehiculo.setCosto(79990.0);
        vehiculo.setMatriculado(true);
        vehiculo.setFechaFabricacion(new GregorianCalendar(2015, 11, 13).getTime());
        vehiculos.add(vehiculo);

        vehiculo = new Vehiculo();
        vehiculo.setMarca("Honda");
        vehiculo.setPlaca("CCD-0789");
        vehiculo.setColor("Blanco");
        vehiculo.setCosto(15340.0);
        vehiculo.setMatriculado(false);
        vehiculo.setFechaFabricacion(new GregorianCalendar(1998, 03, 05).getTime());
        vehiculos.add(vehiculo);

    }

    public static List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public static void setVehiculos(List<Vehiculo> vehiculos) {
        Login.vehiculos = vehiculos;
    }
}
