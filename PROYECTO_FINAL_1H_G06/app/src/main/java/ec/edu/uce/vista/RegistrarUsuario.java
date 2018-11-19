package ec.edu.uce.vista;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



import java.io.BufferedInputStream;
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

    private String archivo = "usuarios";
    private String carpeta = "/archivos/";

    String file_path = "";
    String name = "";
    EditText usuario;
    EditText contraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);

        usuario = (EditText) findViewById(R.id.usuarioUsuarioText);
        contraseña = (EditText) findViewById(R.id.contraseñaUsuarioText);
        /*mostrarUsuario = (TextView) findViewById(R.id.pruebaUsuarioLabel);

        confirmarRegistro = (Button) findViewById(R.id.confirmarUsuarioButton);
        leer = (Button) findViewById(R.id.leerButton);


        confirmarRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usu = new Usuario();
                usu.setUsuario(usuario.getText().toString());
                usu.setContraseña(contraseña.getText().toString());

                try {
                    crearJSON(usu);
                    //crearJSON();
                    //registrar(usu);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        leer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //consultar();

                try {
                    leerJSON();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });*/
    }
    /*
    public void confirmarRegistro(View view) throws IOException {
        File file;
        List<Usuario> usuarios = new ArrayList<>();
        Usuario usuarioAux;
        this.file_path = (Environment.getExternalStorageDirectory() + this.carpeta);
        File localFile = new File(this.file_path);

        if (!localFile.exists()) {
            localFile.mkdir();
        }

        this.name = (this.archivo + ".bin");
        file = new File(localFile, this.name);

        if (file.exists()) {
            try {
                FileInputStream fis;
                fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                while (fis.available() > 0) {
                    usuarioAux = (Usuario) ois.readObject();
                    usuarios.add(usuarioAux);
                }
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (this.usuario.getText().toString().isEmpty()) {
            Toast.makeText(this, "Campo Usuario vacio", Toast.LENGTH_SHORT).show();
        } else {
            if (this.contraseña.getText().toString().isEmpty()) {
                Toast.makeText(this, "Campo Clave vacio", Toast.LENGTH_SHORT).show();
            } else {
                String usuario = this.usuario.getText().toString();
                String clave = this.contraseña.getText().toString();
                usuarioAux = new Usuario(usuario, clave);
                usuarios.add(usuarioAux);

                OutputStream os = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(os);
                for (Usuario u : usuarios) {
                    oos.writeObject(u);
                }
                oos.close();
                os.close();
                Intent siguiente;
                siguiente = new
                        Intent(this, Login.class);
                startActivity(siguiente);
                finish();
            }
        }
    }
    */

    public void confirmarRegistro (View view){
        File file;
        Usuario nuevoUsuario;
        List<Usuario> usuarios = new ArrayList<>();
        this.file_path = (Environment.getExternalStorageDirectory()+this.carpeta);
        File localFile = new File(this.file_path);

        if (!localFile.exists()){
            localFile.mkdir();
        }

        //this.name = (this.archivo + ".bim");
        this.name = (this.archivo + ".txt");
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
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        String usuario = this.usuario.getText().toString();
        String contraseña = this.contraseña.getText().toString();
        if (usuario.isEmpty() || contraseña.isEmpty()){
            Toast.makeText(this, "Necesita llenar todos los campos", Toast.LENGTH_SHORT).show();
        }else{
           nuevoUsuario = new Usuario(usuario, contraseña);
           usuarios.add(nuevoUsuario);
            try {
                OutputStream os = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(os);
                for (Usuario u : usuarios){
                    oos.writeObject(u);
                }
                oos.close();
                os.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Intent login = new Intent(this, Login.class);
            Toast.makeText(this, "Usuario registrado", Toast.LENGTH_SHORT).show();
            startActivity(login);
            finish();
        }
    }

    /*
    public void crearJSON(Usuario usu) throws JSONException, IOException {
    //public void crearJSON() throws JSONException, IOException {
        JSONArray jsonarray = new JSONArray();

        JSONObject item;

        item = new JSONObject();
        item.put("usuario", usu.getUsuario());
        item.put("contraseña", usu.getContraseña());
        //item.put("usuario", usu);
        jsonarray.put(item);


        item = new JSONObject();
        item.put("usuario", "uce");
        item.put("contraseña", "uce");
        jsonarray.put(item);

        item = new JSONObject();
        item.put("usuario", "admin");
        item.put("contraseña", "admin");
        jsonarray.put(item);

        item = new JSONObject();
        item.put("usuario", "opta");
        item.put("contraseña", "opta");
        jsonarray.put(item);



        String texto = jsonarray.toString();
        //String texto = usu.toString();

        FileOutputStream fo = openFileOutput("ejemplo", MODE_APPEND);


        //FileOutputStream fo = openFileOutput("usuarios", MODE_APPEND);

        fo.write(texto.getBytes());
        fo.close();
        mostrarUsuario.setText(usu.toString());
    }

    public void leerJSON() throws IOException, JSONException {
        FileInputStream fi = openFileInput("ejemplo");
        BufferedInputStream bi = new BufferedInputStream(fi);
        StringBuffer buffer = new StringBuffer();

        while(bi.available() !=0){
            char c = (char) bi.read();
            buffer.append(c);
        }

        bi.close();
        fi.close();

        JSONArray array = new JSONArray(buffer.toString());

        StringBuffer itemBuffer = new StringBuffer();
        for (int i = 0; i<array.length(); i++){
            String item = array.getJSONObject(i).getString("usuario");
            //String item2 = array.getJSONObject(i).getString("contraseña");
            itemBuffer.append(item+"\n");
            mostrarUsuario.setText(itemBuffer.toString());
        }
    }

    */
}
