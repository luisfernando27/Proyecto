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

public class ConsultarCliente extends AppCompatActivity {
    private EditText correo;
    private TextView txtNombre, txtEdad, txtSexo, txtContra, txtDireccion, txtCiudad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrar_clientes);

        correo = findViewById(R.id.TxtCorreo);
        txtNombre = findViewById(R.id.TxtNombre);
        txtEdad = findViewById(R.id.TxtEdad);
        txtSexo = findViewById(R.id.TxtSexo);
        txtContra = findViewById(R.id.TxtContraseña);
        txtDireccion = findViewById(R.id.TxtDireccion);
        txtCiudad = findViewById(R.id.TxtCiudad);
    }

    public void consultarCliente(View v) {
        // Obtener el texto del campo de correo
        String correo1 = correo.getText().toString().trim();

        // Verificar si el campo de correo está vacío
        if (correo1.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese un correo", Toast.LENGTH_SHORT).show();
            txtNombre.setText("");
            txtEdad.setText("");
            txtSexo.setText("");
            txtContra.setText("");
            txtDireccion.setText("");
            txtCiudad.setText("");
            return; // Salir del método si el campo de correo está vacío
        }

        // Continuar con la consulta si el campo de correo no está vacío
        AdminSqLite admin = new AdminSqLite(this, "localMarket", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        // Realizar la consulta en la base de datos
        Cursor fila = bd.rawQuery("select nombre, edad, sexo, contra, direccion, ciudad from clientes where correo='" + correo1 + "'", null);
        if (fila.moveToFirst()) {
            // Asignar los valores a los TextView
            txtNombre.setText("Nombre: " + fila.getString(0));
            txtEdad.setText("Edad: " + fila.getString(1));
            txtSexo.setText("Sexo: " + fila.getString(2));
            txtContra.setText("Contraseña: " + fila.getString(3));
            txtDireccion.setText("Dirección: " + fila.getString(4));
            txtCiudad.setText("Ciudad: " + fila.getString(5));
        } else {
            // Mostrar un mensaje si no se encontraron registros con el correo dado
            Toast.makeText(this, "No se encontraron registros para el correo proporcionado", Toast.LENGTH_SHORT).show();
            txtNombre.setText("");
            txtEdad.setText("");
            txtSexo.setText("");
            txtContra.setText("");
            txtDireccion.setText("");
            txtCiudad.setText("");
        }
        // Cerrar la base de datos
        bd.close();
    }

    public void atrasClientes1(View v) {
        onBackPressed();

    }

}