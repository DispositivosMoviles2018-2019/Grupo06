package ec.edu.uce.appaudio;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class RecordActivity extends AppCompatActivity {

    private MediaRecorder mediaRecorder;
    private MediaPlayer mediaPlayer;
    private Button bGrabar, bDetener, bReproducir;

    private static String fichero = Environment.getExternalStorageDirectory().getAbsolutePath()+"/audio.3gp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        bGrabar = (Button) findViewById(R.id.grabarButton);
        bDetener = (Button) findViewById(R.id.detenerButton);
        bReproducir = (Button) findViewById(R.id.reproducirButton);

        bDetener.setEnabled(false);
        bReproducir.setEnabled(false);
    }

    public void grabar(View view) {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setOutputFile(fichero);
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        //mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaRecorder.start();
        bGrabar.setEnabled(false);
        bDetener.setEnabled(true);
        bReproducir.setEnabled(false);
    }

    public void detenerGrabacion(View view) {
        mediaRecorder.stop();
        mediaRecorder.release();
        bGrabar.setEnabled(true);
        bDetener.setEnabled(false);
        bReproducir.setEnabled(true);
    }

    public void reproducir(View view) {
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(fichero);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
