package ec.edu.uce.appperiodictask;

import android.content.Intent;
import android.media.TimedMetaData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{

    Button ej6_2;
    Button ej6_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*ej6_2 = (Button) findViewById(R.id.Ej6_2);
        ej6_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TimingActivity.class));
            }
        });
        ej6_3 = (Button) findViewById(R.id.Ej6_3);

        ej6_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AlarmActivity.class));
            }
        });*/

    }

    public void Ejercicio_6_2 (View view){
        Intent intent = new Intent(MainActivity.this, TimingActivity.class);
        startActivity(intent);
    }

    public void Ejercicio_6_3 (View view){
        Intent intent = new Intent(MainActivity.this, AlarmActivity.class);
        startActivity(intent);
    }
}
