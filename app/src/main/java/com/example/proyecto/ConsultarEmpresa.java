package com.example.proyecto;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.w3c.dom.Text;

public class ConsultarEmpresa extends AppCompatActivity {
    private EditText correo;
    private TextView txtNombre, txtContra, txtDireccion, txtCiudad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrar_empresas);

        correo = findViewById(R.id.TxtCorreo);
        txtNombre = findViewById(R.id.TxtNombre);
        txtContra = findViewById(R.id.TxtContraseña);
        txtDireccion = findViewById(R.id.TxtDireccion);
        txtCiudad = findViewById(R.id.TxtCiudad);
    }

    public void consultarEmpresa(View v) {
        // Obtener el texto del campo de correo
        String correo1 = correo.getText().toString().trim();

        // Verificar si el campo de correo está vacío
        if (correo1.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese un correo", Toast.LENGTH_SHORT).show();
            txtNombre.setText("");
            txtContra.setText("");
            txtDireccion.setText("");
            txtCiudad.setText("");
            return; // Salir del método si el campo de correo está vacío
        }

        // Continuar con la consulta si el campo de correo no está vacío
        AdminSqLite admin = new AdminSqLite(this, "localMarket", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        // Realizar la consulta en la base de datos
        Cursor fila = bd.rawQuery("select nombre, contra, direccion, ciudad from empresas where correo='" + correo1 + "'", null);
        if (fila.moveToFirst()) {
            // Asignar los valores a los TextView
            txtNombre.setText("Nombre: " + fila.getString(0));
            txtContra.setText("Contraseña: " + fila.getString(1));
            txtDireccion.setText("Dirección: " + fila.getString(2));
            txtCiudad.setText("Ciudad: " + fila.getString(3));
        } else {
            // Mostrar un mensaje si no se encontraron registros con el correo dado
            Toast.makeText(this, "No se encontraron registros para el correo proporcionado", Toast.LENGTH_SHORT).show();
            txtNombre.setText("");
            txtContra.setText("");
            txtDireccion.setText("");
            txtCiudad.setText("");
        }
        // Cerrar la base de datos
        bd.close();
    }

    public void atrasEmpresa1(View v) {
        onBackPressed();

    }
}
