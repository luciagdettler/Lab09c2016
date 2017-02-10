package dam.isi.frsf.utn.edu.ar.lab09c2016;

import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mSensor;
    private ListView listView_Acelerómetro;
    private float[] gravity;
    private float[] linear_aceleration;
    ArrayAdapter<String> adaptador;
    String[] sistemas;
    @TargetApi(Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorManager.registerListener(this,mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);
        listView_Acelerómetro = (ListView) findViewById(R.id.listView_Acelerómetro);
        sistemas = new String[]{"Hora Maximo X:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()))+"\nMagnitud: 0",
                "Hora Maximo Y:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()))+"\nMagnitud: 0",
                "Hora Maximo Z:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()))+"\nMagnitud: 0"};
        //sistemas[0]="Hora Maximo X:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()))+"\nMagnitud: 0";
        //sistemas[1]="Hora Maximo Y:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()))+"\nMagnitud: 0";
        //sistemas[2]="Hora Maximo Z:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()))+"\nMagnitud: 0";
        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sistemas);
        listView_Acelerómetro.setAdapter(adaptador);

        gravity = new float[3];
        linear_aceleration = new float[3];
        for (int i = 0; i < 3; i++) {
            gravity[i]=(float)9.8;
            linear_aceleration[i]=0;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        synchronized (this) {
            switch (event.sensor.getType()) {
                case Sensor.TYPE_ACCELEROMETER:
                    final float alpha = (float) 0.8;
                    for (int i = 0; i < 3; i++) {
                        gravity[i] = alpha * gravity[i] + (1 - alpha) * event.values[i];
                        if((event.values[i] - gravity[i])-linear_aceleration[i]>0) {
                            linear_aceleration[i] = event.values[i] - gravity[i];
                            modificarTexto(i,linear_aceleration[i]);
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @TargetApi(Build.VERSION_CODES.N)
    private void modificarTexto(int pos, float Magnitud){
        switch (pos){
            case 0:
                sistemas[0] = "Hora Maximo X:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()))+"\nMagnitud: "+Magnitud;
                break;
            case 1:
                sistemas[1]="Hora Maximo Y:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()))+"\nMagnitud: "+Magnitud;
                break;
            case 2:
                sistemas[2]="Hora Maximo Z:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()))+"\nMagnitud: "+Magnitud;
            default:
                break;
        }
        adaptador.notifyDataSetChanged();
    }


}
