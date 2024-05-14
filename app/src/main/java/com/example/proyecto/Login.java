package com.example.proyecto;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private EditText correo, contra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        correo = findViewById(R.id.txtCorreo);
        contra = findViewById(R.id.txtContraseña);
    }

    public void login(View v) {
        // Obtener el texto del campo de correo
        String correo1 = correo.getText().toString().trim();
        String contra1 = contra.getText().toString().trim();

        // Verificar si el campo de correo está vacío
        if (correo1.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese un correo", Toast.LENGTH_SHORT).show();
        }else if (contra1.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese su contraseña", Toast.LENGTH_SHORT).show();
        }else {
            // Continuar con la consulta si el campo de correo no está vacío
            AdminSqLite admin = new AdminSqLite(this, "localMarket", null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase();

            // Realizar la consulta en la base de datos
            Cursor fila = bd.rawQuery("select contra from clientes where correo='" + correo1 + "'", null);
            Cursor fila1 = bd.rawQuery("select contra from empresas where correo='" + correo1 + "'", null);
            if (fila.moveToFirst()) {
                // Asignar los valores a los TextView
                String contra2 = fila.getString(0);
                if (contra2.equals(contra1)) {
                    Toast.makeText(this, "Iniciado sesión cliente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Contraseña incorrecta cliente: ", Toast.LENGTH_SHORT).show();
                }
            } else if (fila1.moveToFirst()) {
                // Asignar los valores a los TextView
                String contra3 = fila1.getString(0);
                if (contra3.equals(contra1)) {
                    Toast.makeText(this, "Iniciado sesión empresa", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Contraseña incorrecta empresa: ", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Correo no registrado", Toast.LENGTH_SHORT).show();
            }

        }
    }

    public void pantallaPrincipal(View v) {
        Intent intent = new Intent(this, PrincipalRegistro.class);
        startActivity(intent);
    }
}