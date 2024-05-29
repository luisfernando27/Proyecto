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
import android.widget.Toast;

public class InfoempresaFragment extends Fragment {

    private String userEmail;
    private TextView tvNombreEmpresa, tvCorreoEmpresa, tvDireccionEmpresa, tvCiudadEmpresa, tvContrasenaEmpresa;

    public InfoempresaFragment() {
        // Required empty public constructor
    }

    public static InfoempresaFragment newInstance(String userEmail) {
        InfoempresaFragment fragment = new InfoempresaFragment();
        Bundle args = new Bundle();
        args.putString("CORREO", userEmail);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userEmail = getArguments().getString("CORREO");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el diseño del fragmento
        View view = inflater.inflate(R.layout.fragment_infoempresa, container, false);

        // Inicializar TextViews
        tvNombreEmpresa = view.findViewById(R.id.tvNombreEmpresa);
        tvCorreoEmpresa = view.findViewById(R.id.tvCorreoEmpresa);
        tvDireccionEmpresa = view.findViewById(R.id.tvDireccionEmpresa);
        tvCiudadEmpresa = view.findViewById(R.id.tvCiudadEmpresa);
        tvContrasenaEmpresa = view.findViewById(R.id.tvContrasenaEmpresa);

        // Cargar datos desde la base de datos
        loadEmpresaData();

        return view;
    }

    private void loadEmpresaData() {
        AdminSqLite admin = new AdminSqLite(getContext(), "localMarket", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT nombre, correo, direccion, ciudad, contra FROM empresas WHERE correo='" + userEmail + "'", null);

        if (cursor.moveToFirst()) {
            String nombre = cursor.getString(0);
            String correo = cursor.getString(1);
            String direccion = cursor.getString(2);
            String ciudad = cursor.getString(3);
            String contrasena = cursor.getString(4);

            tvNombreEmpresa.setText("Nombre: " + nombre);
            tvCorreoEmpresa.setText("Correo: " + correo);
            tvDireccionEmpresa.setText("Dirección: " + direccion);
            tvCiudadEmpresa.setText("Ciudad: " + ciudad);
            tvContrasenaEmpresa.setText("Contraseña: " + contrasena);
        } else {
            // Manejar el caso donde no se encuentre la empresa
            Toast.makeText(getContext(), "No se encontró la información de la empresa", Toast.LENGTH_SHORT).show();
        }

        cursor.close();
        db.close();
    }
}
