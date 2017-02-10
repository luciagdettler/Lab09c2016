package dam.isi.frsf.utn.edu.ar.lab09c2016;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Usuario on 24/1/2017.
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {
    public MyFirebaseMessagingService() {
    }

    public static final String TAG="NOTICIAS";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

       String from = remoteMessage.getFrom();
        Log.d(TAG,"Mensaje recibido de:"+from);

        if(remoteMessage.getNotification() != null){
            Log.d(TAG,"Notificacion:"+remoteMessage.getNotification().getBody());
        }

        NotificationManager nManager = (NotificationManager) getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);
        Intent targetIntent = new Intent();
        PendingIntent contentIntent = PendingIntent.getActivity(getBaseContext(), 0,targetIntent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext())

                .setContentIntent(contentIntent)
                .setContentTitle("Mensaje de Firebase")
                .setContentText(remoteMessage.getNotification().getBody())
                .setWhen(System.currentTimeMillis());




        builder.setAutoCancel(true);
        nManager.notify(123456,builder.build());
    }
}
