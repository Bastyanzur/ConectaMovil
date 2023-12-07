package View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.conectamovil.R;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class HiveMq extends AppCompatActivity {

    private Button btn;
    private static final String TAG = "HiveMq";
    private String topic, clienteID;

    private MqttAndroidClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hive_mq);
        init();
    }

    private void init() {
        btn = findViewById(R.id.btn_Sub);
        clienteID = "xxx";
        topic = "testtopic/deyby";
        client = new MqttAndroidClient(
                this.getApplicationContext(),
                "tcp://broker.hivemq.com:1883",
                clienteID
        );
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MqttConnectTask().execute();
            }
        });
    }

    private class MqttConnectTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {
            try {
                IMqttToken token = client.connect();
                token.waitForCompletion(); // Esperar a que la conexión se complete antes de pasar al siguiente paso

                if (token.getException() == null) {
                    return true; // Conexión exitosa
                } else {
                    Log.d(TAG, "Error en la conexión: " + token.getException());
                    return false;
                }

            } catch (MqttException e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {
                Log.d(TAG, "Conexión exitosa");
                sub();
            } else {
                Log.d(TAG, "Error en la conexión");
                // Manejar el error de conexión aquí
            }
        }
    }

    private void sub() {
        try {
            Log.d(TAG, "Suscripción iniciada");
            client.subscribe(topic, 0);
            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    Log.e(TAG, "Conexión perdida", cause);
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    Log.d(TAG, "topic: " + topic);
                    Log.d(TAG, "message" + new String(message.getPayload()));
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    Log.d(TAG, "Entrega completa");
                    //toast of log
                }
            });
        } catch (MqttException e) {
            Log.e(TAG, "Error en la suscripción", e);
            e.printStackTrace();
        }
    }
}