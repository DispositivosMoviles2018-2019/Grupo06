package ec.edu.uce.vista;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import ec.edu.uce.modelo.Usuario;

public class RegistrarUsuario extends AppCompatActivity {

    private EditText usuario;
    private EditText clave;

    private String archivo = "miarchivo";
    private String carpeta = "/archivos/";

    String file_path = "";
    String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);

        usuario = (EditText) findViewById(R.id.usuarioLoginText);
        clave = (EditText) findViewById(R.id.claveLoginText);
    }

    public void guardarUsuario(View view) {
        File file;
        Usuario nuevoUsuario;
        List<Usuario> usuarios = new ArrayList<>();
        this.file_path = (Environment.getExternalStorageDirectory()+this.carpeta);
        File localFile = new File(this.file_path);

        if (!localFile.exists()){
            localFile.mkdir();
        }

        //this.name = (this.archivo + ".bim");
        this.name = (this.archivo + ".bin");
        file = new File(localFile, this.name);

        if (file.exists()){
            try {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                while (fis.available()>0){
                    nuevoUsuario = (Usuario) ois.readObject();
                    usuarios.add(nuevoUsuario);
                }
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        String usuario = this.usuario.getText().toString();
        String clave = this.clave.getText().toString();
        if (usuario.isEmpty() || clave.isEmpty()){
            Toast.makeText(this, "Necesita llenar todos los campos", Toast.LENGTH_SHORT).show();
        }else{
            nuevoUsuario = new Usuario(usuario, clave);
            usuarios.add(nuevoUsuario);
            try {
                OutputStream os = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(os);
                for (Usuario u : usuarios){
                    oos.writeObject(u);
                }
                oos.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Intent login = new Intent(this, Login.class);
            Toast.makeText(this, "Usuario registrado", Toast.LENGTH_SHORT).show();
            startActivity(login);
            finish();
        }
    }
}
