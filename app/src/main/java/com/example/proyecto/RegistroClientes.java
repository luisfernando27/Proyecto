package com.example.proyecto;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.res.ColorStateList;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.text.Spanned;
import android.util.Patterns;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;
import android.text.InputFilter;

public class RegistroClientes extends AppCompatActivity {

    private EditText nombre, edad, correo, contra, confContra, direccion, ciudad;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_clientes);

        // Inicializar los EditTexts
        nombre = findViewById(R.id.TxtNombre);
        edad = findViewById(R.id.TxtEdad);
        correo = findViewById(R.id.TxtCorreo);
        contra = findViewById(R.id.TxtContraseña);
        confContra = findViewById(R.id.TxtCContraseña);
        direccion = findViewById(R.id.TxtDireccion);
        ciudad = findViewById(R.id.TxtCiudad);

        contra.setBackgroundTintList(null);
        confContra.setBackgroundTintList(null);

        nombre.setFilters(new InputFilter[]{new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (!Character.isLetter(source.charAt(i)) && source.charAt(i) != ' ') {
                        return "";
                    }
                }
                return null;
            }
        }});

        contra.setFilters(new InputFilter[]{new InputFilter.LengthFilter(16), new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                // Verificar la longitud de la contraseña
                int length = dest.length() + source.length();
                if (length > 16 || length < 8) {
                    // Cambiar el color del fondo del campo solo si la validación no es válida
                    contra.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                } else {
                    // Restaurar el color del fondo del campo si la validación es válida
                    contra.setBackgroundTintList(null);
                }

                // Permitir la entrada
                return null;
            }

        }});

        confContra.setFilters(new InputFilter[]{new InputFilter.LengthFilter(16), new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                // Verificar la longitud de la contraseña
                int length = dest.length() + source.length();
                if (length > 16 || length < 8) {
                    // Cambiar el color del fondo del campo solo si la validación no es válida
                    confContra.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                } else {
                    // Restaurar el color del fondo del campo si la validación es válida
                    confContra.setBackgroundTintList(null);
                }

                // Permitir la entrada
                return null;
            }

        }});

        direccion.setFilters(new InputFilter[]{new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                // Expresión regular para validar la dirección
                String regex = "[a-zA-Z0-9#áéíóúÁÉÍÓÚüÜ ]+"; // Acepta letras, números, espacios, # y letras con acentos

                // Verifica cada carácter de la entrada
                for (int i = start; i < end; i++) {
                    if (!String.valueOf(source.charAt(i)).matches(regex)) {
                        // Si el carácter no coincide con la expresión regular, no lo permite
                        Toast.makeText(RegistroClientes.this, "La dirección solo puede contener letras, números y #", Toast.LENGTH_SHORT).show();
                        return "";
                    }
                }

                // Permite la entrada
                return null;
            }
        }});

        ciudad.setFilters(new InputFilter[]{new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                // Expresión regular para validar la ciudad
                String regex = "[a-zA-Z0-9,áéíóúÁÉÍÓÚüÜ ]+"; // Acepta letras, números, espacios, # y letras con acentos

                // Verifica cada carácter de la entrada
                for (int i = start; i < end; i++) {
                    if (!String.valueOf(source.charAt(i)).matches(regex)) {
                        // Si el carácter no coincide con la expresión regular, no lo permite
                        Toast.makeText(RegistroClientes.this, "La ciudad solo puede contener letras y ,", Toast.LENGTH_SHORT).show();
                        return "";
                    }
                }

                // Permite la entrada
                return null;
            }
        }});

        // Inicializar el Spinner
        spinner = findViewById(R.id.TxtSexo);

        // Definir un array de opciones para el spinner
        String[] opciones = {"Hombre", "Mujer"};

        // Crear un adaptador para el spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones);

        // Especificar el estilo del dropdown
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Asignar el adaptador al spinner
        spinner.setAdapter(adapter);
    }

    public void guardarCliente(View v) {
        // Obtener los valores de los EditTexts
        String nombre1 = nombre.getText().toString().trim();
        String edad1 = edad.getText().toString().trim();
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
        else if (edad1.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese su edad", Toast.LENGTH_SHORT).show();
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


        // Obtener la selección del Spinner
        String seleccion = spinner.getSelectedItem().toString();


        // Guardar los datos en la base de datos
        AdminSqLite admin = new AdminSqLite(this, "localMarket", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        ContentValues registro = new ContentValues();
        registro.put("nombre", nombre1);
        registro.put("edad", edad1);
        registro.put("sexo", seleccion);
        registro.put("correo", correo1);
        registro.put("contra", contra1);
        registro.put("confContra", confContra1);
        registro.put("direccion", direccion1);
        registro.put("ciudad", ciudad1);

        bd.insert("clientes", null, registro);

        nombre.setText("");
        edad.setText("");
        correo.setText("");
        contra.setText("");
        confContra.setText("");
        direccion.setText("");
        ciudad.setText("");

        // Mostrar un mensaje de éxito
        Toast.makeText(this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show();
        contra.setBackgroundTintList(null);
        confContra.setBackgroundTintList(null);
    }



    public void consultarClientes(View v) {
        Intent intent = new Intent(this, ConsultarCliente.class);
        startActivity(intent);

    }

    public void atrasCliente(View v) {
        onBackPressed();

    }

}