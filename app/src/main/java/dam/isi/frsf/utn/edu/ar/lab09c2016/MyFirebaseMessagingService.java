package dam.isi.frsf.utn.edu.ar.lab09c2016;

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
    }
}
