package View;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;



import com.example.conectamovil.R;

public class ChatReceiverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_receiver);

        // Aquí puedes obtener el mensaje recibido desde MQTT y mostrarlo en un TextView
        TextView receivedMessageTextView = findViewById(R.id.receivedMessageTextView);

        // Obtén el mensaje del intent
        String receivedMessage = getIntent().getStringExtra("MESSAGE_KEY");

        // Muestra el mensaje
        receivedMessageTextView.setText(receivedMessage);
    }
}



