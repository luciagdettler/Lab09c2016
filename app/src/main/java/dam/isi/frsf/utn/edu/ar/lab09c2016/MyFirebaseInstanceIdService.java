package dam.isi.frsf.utn.edu.ar.lab09c2016;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {
    public MyFirebaseInstanceIdService() {
    }
    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static final String TAG="NOTICIAS";
    @Override
    public void onTokenRefresh() {
        // obtener token InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        //saveTokenToPrefs(refreshedToken);
        Log.d(TAG,"Token:"+refreshedToken);
    }
    private void saveTokenToPrefs(String _token) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("registration_id", _token);
        editor.apply();
// luego en cualquier parte de la aplicación podremos recuperar el token con
// SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
// preferences.getString("registration_id", null);
    }

}
