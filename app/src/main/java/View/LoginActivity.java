package View;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.conectamovil.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    private Button btnLogin;

    private FirebaseAuth mAuth;

    Button btnIngresarRegistro;

    public Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogout = findViewById(R.id.btnLogout);

        mAuth = FirebaseAuth.getInstance();

        btnIngresarRegistro = findViewById(R.id.btnregresarRegistro);

        btnIngresarRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí agregas la lógica para ir al activity_login
                irAActivityRegister();
            }
        });
    }
    private void irAActivityRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los valores ingresados por el usuario
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                // Validar que los campos no estén vacíos
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Iniciar sesión utilizando Firebase Authentication
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<com.google.firebase.auth.AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Inicio de sesión exitoso
                                    Toast.makeText(LoginActivity.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();

                                    // Redirigir al usuario a la pantalla de contactos (activity_contacts)
                                    Intent intent = new Intent(LoginActivity.this, ChatActivity.class);
                                    startActivity(intent);
                                    finish(); // Cerrar la actividad actual para que el usuario no pueda volver atrás

                                } else {
                                    // Inicio de sesión fallido
                                    String errorMessage = task.getException().getMessage();
                                    Toast.makeText(LoginActivity.this, "Error al iniciar sesión: " + errorMessage, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }

        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cerrar sesión en Firebase
                if (mAuth.getCurrentUser() != null) {
                    mAuth.signOut();
                    Toast.makeText(LoginActivity.this, "Sesión cerrada", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "No hay sesión activa", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}