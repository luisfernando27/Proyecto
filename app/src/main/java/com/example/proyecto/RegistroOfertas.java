package com.example.proyecto;
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
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;


public class RegistroOfertas extends AppCompatActivity {
    private EditText idOferta, nombreOferta, totalOferta, maximoCliente, txtFechaInicio, txtFechaFin;

    private String correo, id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_ofertas);

        Bundle bundle=this.getIntent().getExtras();

        //Construimos el mensaje a mostrar


        idOferta=findViewById(R.id.TxtidOferta);
        nombreOferta=findViewById(R.id.TxtNombreOferta);
        totalOferta=findViewById(R.id.TxttotalOfertas);
        maximoCliente=findViewById(R.id.TxtmaximoClientes);
        txtFechaInicio = findViewById(R.id.TxtfechaInicio);
        txtFechaFin = findViewById(R.id.TxtfechaFin);

        txtFechaInicio.setOnClickListener(v -> showDatePickerDialog(txtFechaInicio));
        txtFechaFin.setOnClickListener(v -> showDatePickerDialog(txtFechaFin));

        String ofertaID = generateUniqueOfertaId();
        idOferta.setText(ofertaID);
        idOferta.setFocusable(false);
        idOferta.setClickable(false);

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

    private void showDatePickerDialog(EditText editText) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year1, monthOfYear, dayOfMonth) -> {
                    String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1;
                    editText.setText(selectedDate);
                }, year, month, day);
        datePickerDialog.show();
    }

    private String generateUniqueOfertaId() {
        AdminSqLite admin = new AdminSqLite(this, "localMarket", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        // Realizar la consulta en la base de datos
        Cursor fila = bd.rawQuery("SELECT COUNT(*) FROM ofertasEmpresas", null);
        String id;
        if (fila.moveToFirst()) {
            // Obtener el número de registros y añadir 1 para el nuevo ID
            int count = fila.getInt(0) + 1;
            // Formatear el ID con el prefijo "C" y 5 dígitos
            id = String.format("O%05d", count);
        } else {
            // Si no hay registros, el ID inicial será "C00001"
            id = "O00001";
        }
        fila.close();
        bd.close();
        return id;
    }

    public void guardarOferta(View v) {

        String idOferta1 = idOferta.getText().toString().trim();
        String nombreOferta1 = nombreOferta.getText().toString().trim();
        String totalOfertas1 = totalOferta.getText().toString().trim();
        String maximoClientes1 = maximoCliente.getText().toString().trim();
        String fechaInicio1 = txtFechaInicio.getText().toString().trim();
        String fechaFin1 = txtFechaFin.getText().toString().trim();

        // Validar cada campo individualmente
        if (nombreOferta1.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese el nombre de la oferta", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (totalOfertas1.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese el total de ofertas", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (maximoClientes1.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese el maximo de clientes", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (fechaInicio1.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese la fecha de inicio", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (fechaFin1.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese la fecha de inicio", Toast.LENGTH_SHORT).show();
            return;
        }

        // Guardar los datos en la base de datos
        AdminSqLite admin = new AdminSqLite(this, "localMarket", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        ContentValues registro = new ContentValues();
        registro.put("idF", idOferta1);
        registro.put("idE", id);
        registro.put("nombre_oferta", nombreOferta1);
        registro.put("total_oferta", totalOfertas1);
        registro.put("maximo_clientes", maximoClientes1);
        registro.put("fecha_inicio", fechaInicio1);
        registro.put("fecha_fin", fechaFin1);

        bd.insert("ofertasEmpresas", null, registro);


        idOferta.setText(generateUniqueOfertaId());
        nombreOferta.setText("");
        totalOferta.setText("");
        maximoCliente.setText("");
        txtFechaInicio.setText("");
        txtFechaFin.setText("");

        // Mostrar un mensaje de éxito
        Toast.makeText(this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show();
    }

    public void atrasRegistroOfertas(View v) {
        onBackPressed();

    }
}
