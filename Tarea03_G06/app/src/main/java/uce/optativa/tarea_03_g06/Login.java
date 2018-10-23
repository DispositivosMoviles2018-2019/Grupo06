package uce.optativa.tarea_03_g06;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btn_in = (Button) findViewById(R.id.sign_in_button);
        btn_in.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String usuario = ((EditText)findViewById(R.id.user)).getText().toString();
                String contraseña = ((EditText)findViewById(R.id.password)).getText().toString();

                if ((usuario.equals("wladimir")&& contraseña.equals("chipuxi")) ||
                        (usuario.equals("jose")&& contraseña.equals("naranjo")) ||
                        (usuario.equals("joel")&& contraseña.equals("vargas")))
                {
                    startActivity(new Intent(Login.this, Inicio.class));
                } else {
                    Toast.makeText(getApplicationContext(),"Credenciales Incorrectas",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
