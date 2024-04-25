package com.example.proyecto;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

public class RegistroEmpresas extends AppCompatActivity {
    private EditText nombre, correo, contra, confContra, direccion, ciudad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_empresas);

        // Inicializar los EditTexts
        nombre = findViewById(R.id.TxtNombre);
        correo = findViewById(R.id.TxtCorreo);
        contra = findViewById(R.id.TxtContraseña);
        confContra = findViewById(R.id.TxtCContraseña);
        direccion = findViewById(R.id.TxtDireccion);
        ciudad = findViewById(R.id.TxtCiudad);
    }

    public void guardarEmpresa(View v) {
        // Obtener los valores de los EditTexts
        String nombre1 = nombre.getText().toString().trim();
        String correo1 = correo.getText().toString().trim();
        String contra1 = contra.getText().toString().trim();
        String confContra1 = confContra.getText().toString().trim();
        String direccion1 = direccion.getText().toString().trim();
        String ciudad1 = ciudad.getText().toString().trim();

        // Validar cada campo individualmente
        if (nombre1.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese su nombre", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (correo1.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese su correo electrónico", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (contra1.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese su contraseña", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (confContra1.isEmpty()) {
            Toast.makeText(this, "Por favor, confirme su contraseña", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (direccion1.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese su dirección", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (ciudad1.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese su ciudad", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!contra1.equals(confContra1)) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return;
        }

        // Guardar los datos en la base de datos
        AdminSqLite admin = new AdminSqLite(this, "localMarket", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        ContentValues registro = new ContentValues();
        registro.put("nombre", nombre1);
        registro.put("correo", correo1);
        registro.put("contra", contra1);
        registro.put("confContra", confContra1);
        registro.put("direccion", direccion1);
        registro.put("ciudad", ciudad1);

        bd.insert("empresas", null, registro);

        bd.close();
        nombre.setText("");
        correo.setText("");
        contra.setText("");
        confContra.setText("");
        direccion.setText("");
        ciudad.setText("");

        // Mostrar un mensaje de éxito
        Toast.makeText(RegistroEmpresas.this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show();
    }



    public void consultarEmpresa(View v) {
        Intent intent = new Intent(this, ConsultarEmpresa.class);
        startActivity(intent);

    }

    public void atrasEmpresa(View v) {
        onBackPressed();

    }
}
