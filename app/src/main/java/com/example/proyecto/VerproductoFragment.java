package com.example.proyecto;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class VerproductoFragment extends Fragment {
    List<ListaElementosProductosEmpresa> elements;

    private String userEmail;

    public VerproductoFragment() {}

    public static VerproductoFragment newInstance(String userEmail) {
        VerproductoFragment fragment = new VerproductoFragment();
        Bundle args = new Bundle();
        args.putString("CORREO", userEmail);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflar el diseño del fragmento
        View view = inflater.inflate(R.layout.fragment_verproducto, container, false);

        // Obtener el correo electrónico de los argumentos
        Bundle args = getArguments();
        if (args != null) {
            userEmail = args.getString("CORREO");
        }

        // Inicializar y configurar el RecyclerView
        init(view);

        // Cargar los datos de la base de datos
        loadDataFromDatabase();

        return view;
    }

    private void init(View view) {
        elements = new ArrayList<>();

        ListAdapterProductosEmpresa listAdapterProductosEmpresa = new ListAdapterProductosEmpresa(elements, getContext());
        RecyclerView recyclerView = view.findViewById(R.id.recyclerListaProdReg);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(listAdapterProductosEmpresa);
    }

    private void loadDataFromDatabase() {
        AdminSqLite admin = new AdminSqLite(getContext(), "localMarket", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        // Retrieve the ID of the company associated with the user email
        Cursor cursorID = bd.rawQuery("SELECT idE FROM empresas WHERE correo='" + userEmail + "'", null);

        if (cursorID.moveToFirst()) {
            String idE = cursorID.getString(0);

            // Query to select offers from the company with idE
            Cursor cursor = bd.rawQuery("SELECT idP, nombre_producto, cantidad, precio FROM productos WHERE idE='" + idE + "'", null);

            if (cursor.moveToFirst()) {
                do {
                    String idProducto = cursor.getString(0);
                    String nombreProducto = cursor.getString(1);
                    String cantidad = cursor.getString(2);
                    String precio = cursor.getString(3);
                    elements.add(new ListaElementosProductosEmpresa(idProducto, nombreProducto, cantidad, precio));
                } while (cursor.moveToNext());
            }

            cursor.close();
        }

        cursorID.close();
        bd.close();

    }
}
