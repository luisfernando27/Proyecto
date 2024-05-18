package com.example.proyecto;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Calendar;

public class RegistroProductos extends AppCompatActivity {

    private EditText idProducto, nombreProducto, descripcion, cantidad, precio;

    private String correo, id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_productos);

        Bundle bundle=this.getIntent().getExtras();

        //Construimos el mensaje a mostrar


        idProducto=findViewById(R.id.TxtidProducto);
        nombreProducto=findViewById(R.id.TxtNombreProducto);
        descripcion=findViewById(R.id.Txtdescripcion);
        cantidad=findViewById(R.id.Txtcantidad);
        precio = findViewById(R.id.Txtprecio);


        String productoID = generateUniqueProductoId();
        idProducto.setText(productoID);
        idProducto.setFocusable(false);
        idProducto.setClickable(false);

        correo = bundle.getString("CORREO");

        AdminSqLite admin = new AdminSqLite(this, "localMarket", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        // Realizar la consulta en la base de datos
        Cursor fila = bd.rawQuery("select idE from empresas where correo='" + correo + "'", null);
        if (fila.moveToFirst()) {
            // Asignar los valores a los TextView
            id = fila.getString(0);
        }
    }

    private String generateUniqueProductoId() {
        AdminSqLite admin = new AdminSqLite(this, "localMarket", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        // Realizar la consulta en la base de datos
        Cursor fila = bd.rawQuery("SELECT COUNT(*) FROM productos", null);
        String id;
        if (fila.moveToFirst()) {
            // Obtener el número de registros y añadir 1 para el nuevo ID
            int count = fila.getInt(0) + 1;
            // Formatear el ID con el prefijo "C" y 5 dígitos
            id = String.format("P%05d", count);
        } else {
            // Si no hay registros, el ID inicial será "C00001"
            id = "P00001";
        }
        fila.close();
        bd.close();
        return id;
    }

    public void guardarProducto(View v) {

        String idProducto1 = idProducto.getText().toString().trim();
        String nombreProducto1 = nombreProducto.getText().toString().trim();
        String descripcion1 = descripcion.getText().toString().trim();
        String cantidad1 = cantidad.getText().toString().trim();
        String precio1 = precio.getText().toString().trim();

        // Validar cada campo individualmente
        if (nombreProducto1.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese el nombre del producto", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (descripcion1.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese la descripcion del producto", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (cantidad1.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese la cantidad del producto", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (precio1.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese el precio del producto", Toast.LENGTH_SHORT).show();
            return;
        }

        // Guardar los datos en la base de datos
        AdminSqLite admin = new AdminSqLite(this, "localMarket", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        ContentValues registro = new ContentValues();
        registro.put("idP", idProducto1);
        registro.put("idE", id);
        registro.put("nombre_producto", nombreProducto1);
        registro.put("descripcion", descripcion1);
        registro.put("cantidad", cantidad1);
        registro.put("precio", precio1);

        bd.insert("productos", null, registro);


        idProducto.setText(generateUniqueProductoId());
        nombreProducto.setText("");
        descripcion.setText("");
        cantidad.setText("");
        precio.setText("");

        // Mostrar un mensaje de éxito
        Toast.makeText(this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show();
    }

    public void atrasRegistroProductos(View v) {
        onBackPressed();

    }
}
