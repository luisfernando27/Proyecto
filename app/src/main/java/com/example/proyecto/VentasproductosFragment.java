package com.example.proyecto;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class VentasproductosFragment extends Fragment {
    List<ListaElementosVentasProductos> elements;

    private ListAdapterVentasProductos listAdapter;

    private String userEmail;

    public VentasproductosFragment() {}

    public static VentasproductosFragment newInstance(String userEmail) {
        VentasproductosFragment fragment = new VentasproductosFragment();
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
        View view = inflater.inflate(R.layout.fragment_ventasproductos, container, false);

        // Get user email from arguments
        Bundle args = getArguments();
        if (args != null) {
            userEmail = args.getString("CORREO");
        }

        // Inicializar y configurar el RecyclerView
        // Initialize RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerListaVentasProd);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize List and Adapter
        elements = new ArrayList<>();
        listAdapter = new ListAdapterVentasProductos(elements, getContext());
        recyclerView.setAdapter(listAdapter);

        // Load data from database
        loadDataFromDatabase();

        return view;
    }
    private void loadDataFromDatabase() {
        AdminSqLite admin = new AdminSqLite(getContext(), "localMarket", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        // Retrieve the ID of the company associated with the user email
        Cursor cursorID = bd.rawQuery("SELECT idE FROM empresas WHERE correo='" + userEmail + "'", null);

        if (cursorID.moveToFirst()) {
            String idE = cursorID.getString(0);

            // Query to select products from the company with idE
            Cursor cursor = bd.rawQuery("SELECT ventasProducto.idVP, productos.nombre_producto, ventasProducto.totalC, productos.cantidad, productos.precio FROM ventasProducto, productos WHERE ventasProducto.idP=productos.idP AND ventasProducto.idE=productos.idE AND ventasProducto.idE='" + idE + "'", null);

            if (cursor.moveToFirst()) {
                do {
                    String idVP = cursor.getString(0);
                    String nombreProducto = cursor.getString(1);
                    String totalC = cursor.getString(2);
                    String cantidad = cursor.getString(3);
                    String precio = cursor.getString(4);
                    elements.add(new ListaElementosVentasProductos(idVP, nombreProducto, totalC, cantidad, precio));
                } while (cursor.moveToNext());
            }

            cursor.close();
        }

        cursorID.close();
        bd.close();
    }
}