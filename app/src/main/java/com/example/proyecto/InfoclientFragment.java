package com.example.proyecto;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class InfoclientFragment extends Fragment {

    private String userEmail;
    private TextView nombreCliente, edadCliente, sexoCliente, correoCliente, contraseñaCliente, direccionCliente, ciudadCliente;

    public static InfoclientFragment newInstance(String userEmail) {
        InfoclientFragment fragment = new InfoclientFragment();
        Bundle args = new Bundle();
        args.putString("CORREO", userEmail);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_infoclient, container, false);

        Bundle args = getArguments();
        if (args != null) {
            userEmail = args.getString("CORREO");
        }

        // Inicializar los TextViews
        nombreCliente = view.findViewById(R.id.nombreCliente);
        edadCliente = view.findViewById(R.id.edadCliente);
        sexoCliente = view.findViewById(R.id.sexoCliente);
        correoCliente = view.findViewById(R.id.correoCliente);
        contraseñaCliente = view.findViewById(R.id.contraseñaCliente);
        direccionCliente = view.findViewById(R.id.direccionCliente);
        ciudadCliente = view.findViewById(R.id.ciudadCliente);

        loadClientData();

        return view;
    }

    private void loadClientData() {
        AdminSqLite admin = new AdminSqLite(getContext(), "localMarket", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        // Consulta SQL para obtener los datos del cliente o empresa
        Cursor cursor = bd.rawQuery("SELECT nombre, edad, sexo, correo, contra, direccion, ciudad FROM clientes WHERE correo = ? UNION SELECT nombre, NULL, NULL, correo, contra, direccion, ciudad FROM empresas WHERE correo = ?", new String[]{userEmail, userEmail});

        if (cursor.moveToFirst()) {
            String nombre = cursor.getString(0);
            int edad = cursor.getInt(1);
            String sexo = cursor.getString(2);
            String correo = cursor.getString(3);
            String contraseña = cursor.getString(4);
            String direccion = cursor.getString(5);
            String ciudad = cursor.getString(6);

            // Mostrar los datos en los TextViews
            nombreCliente.setText(nombre);
            edadCliente.setText(edad > 0 ? String.valueOf(edad) : "");
            sexoCliente.setText(sexo != null ? sexo : "");
            correoCliente.setText(correo);
            contraseñaCliente.setText(contraseña);
            direccionCliente.setText(direccion);
            ciudadCliente.setText(ciudad);
        }

        cursor.close();
        bd.close();
    }
}
