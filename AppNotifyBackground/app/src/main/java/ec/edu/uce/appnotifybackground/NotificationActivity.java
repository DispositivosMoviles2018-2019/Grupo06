package ec.edu.uce.appnotifybackground;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

public class NotificationActivity extends AppCompatActivity {

    private RadioGroup mOptionsGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        mOptionsGroup = (RadioGroup) findViewById(R.id.options_group);
    }

    public void onPostClick(View v) {
        final int noteId = mOptionsGroup.getCheckedRadioButtonId();
        final Notification note;
        switch (noteId){
            case R.id.option_basic:
            case R.id.option_bigtext:
            case R.id.option_bigpicture:
            case R.id.option_inbox:
                note = buildStyleNotification(noteId);
                break;
            case R.id.option_private:
            case R.id.option_secret:
            case R.id.option_headsup:
                note = buildSecuredNotification(noteId);
                break;
             default:
                 throw new IllegalArgumentException("Unknown Type");
        }

        NotificationManager manager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(noteId, note);
    }

    //Estas propiedades pueden ser anuladas por la configuración de notificación del usuario
    private Notification buildSecuredNotification(int type) {
        Intent launchIntent = new Intent(this, NotificationActivity.class);
        PendingIntent contenIntent = PendingIntent.getActivity(this, 0, launchIntent,0);

        // Construir la notificación base
        NotificationCompat.Builder builder= new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Account Balance Update")
                .setContentText("Your account balance is -$250.00")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Your Account balance is -$250.00; pay us please "
                                + "or we wiññ be forced to take legal action!"))
                .setContentIntent(contenIntent);

        switch (type) {
            case R.id.option_private:
                // Proporcionar una versión única para pantallas de bloqueo seguro
                Notification publicNote = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    publicNote = new Notification.Builder(this)
                            .setSmallIcon(R.drawable.ic_launcher_foreground)
                            .setContentTitle("Account Notification")
                            .setContentText("An important message has arrived.")
                            .setContentIntent(contenIntent)
                            .build();
                }

                return builder.setPublicVersion(publicNote).build();
            case R.id.option_secret:
                //Ocultar la notificación de una pantalla de bloqueo segura completamente
                return builder.setVisibility(Notification.VISIBILITY_SECRET).build();
            case R.id.option_headsup:
                return builder.setDefaults(Notification.DEFAULT_SOUND)
                        .setPriority(Notification.PRIORITY_HIGH)
                        .build();
             default:
                 throw new IllegalArgumentException("Unknown Type");

        }

    }

    private Notification buildStyleNotification(int type) {
        Intent launchIntent =
                new Intent(this, NotificationActivity.class);
        PendingIntent contentIntent =
                PendingIntent.getActivity(this, 0, launchIntent, 0);

        // Crear notificación con la hora en que se disparó
        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                NotificationActivity.this);

        builder.setSmallIcon(R.drawable.ic_launcher_foreground)
                .setTicker("Something Happened")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setContentTitle("We're Finished!")
                .setContentText("Click Here!")
                .setContentIntent(contentIntent);

        switch (type) {
            case R.id.option_basic:
                //Retornar la notificación simple
                return builder.build();
            case R.id.option_bigtext:
                //Incluye dos acciones
                builder.addAction(android.R.drawable.ic_menu_call,
                        "Call", contentIntent);
                builder.addAction(android.R.drawable.ic_menu_recent_history,
                        "History", contentIntent);
                //Usar BigTextStyle cuando se expande
                NotificationCompat.BigTextStyle textStyle =
                        new NotificationCompat.BigTextStyle(builder);
                textStyle.bigText(
                        "Here is some additional text to be displayed when the notification is "
                         + "in expanded mode. I can fit so much more content into this giant view!");
                return textStyle.build();
            case R.id.option_bigpicture:
                //Agrega una acción adicional
                builder.addAction(android.R.drawable.ic_menu_compass,
                        "View Location", contentIntent);
                //Usar BigPictureStyle cuando se expande
                NotificationCompat.BigPictureStyle pictureStyle =
                        new NotificationCompat.BigPictureStyle(builder);
                pictureStyle.bigPicture(BitmapFactory.decodeResource(getResources(),
                        R.drawable.ic_launcher_background));
                return pictureStyle.build();
            case R.id.option_inbox:
                //Usar InboxStyle cuando se expanda
                NotificationCompat.InboxStyle inboxStyle =
                        new NotificationCompat.InboxStyle(builder);
                inboxStyle.setSummaryText("4 New Tasks");
                inboxStyle.addLine("Make Dinner");
                inboxStyle.addLine("Call Mom");
                inboxStyle.addLine("Call Wife First");
                inboxStyle.addLine("Pick up Kids");
                return inboxStyle.build();
            default:
                throw new IllegalArgumentException("Unknown Type");
        }
    }
}