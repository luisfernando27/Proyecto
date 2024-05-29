package com.example.proyecto;

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

import java.util.ArrayList;
import java.util.List;

public class VentasofertasFragment extends Fragment {
    List<ListaElementosVentasOfertas> elements;

    private String userEmail;

    public VentasofertasFragment() {}

    public static VentasofertasFragment newInstance(String userEmail) {
        VentasofertasFragment fragment = new VentasofertasFragment();
        Bundle args = new Bundle();
        args.putString("CORREO", userEmail);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ventasofertas, container, false);

        // Get user email from arguments
        Bundle args = getArguments();
        if (args != null) {
            userEmail = args.getString("CORREO");
        }

        // Initialize RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerListaVentaOfert);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize List and Adapter
        elements = new ArrayList<>();
        ListAdapterVentasOfertas listAdapterVentasOfertas = new ListAdapterVentasOfertas(elements, getContext());
        recyclerView.setAdapter(listAdapterVentasOfertas);

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

            // Query to select sales of offers from the company with idE
            Cursor cursor = bd.rawQuery("SELECT ofertasEmpresas.nombre_oferta, ventasOferta.idVO, ventasOferta.totalO, ofertasEmpresas.precio_oferta, ofertasEmpresas.total_oferta FROM ventasOferta, ofertasEmpresas WHERE ventasOferta.idE = ofertasEmpresas.idE AND ventasOferta.idF = ofertasEmpresas.idF AND ventasOferta.idE='" + idE + "'", null);

            if (cursor.moveToFirst()) {
                do {
                    String nombreOferta = cursor.getString(0);
                    String idO = cursor.getString(1);
                    String cantidad = cursor.getString(2);
                    String precio = cursor.getString(3);
                    String restantes = cursor.getString(4);
                    elements.add(new ListaElementosVentasOfertas(nombreOferta, idO, cantidad, precio, restantes));
                } while (cursor.moveToNext());
            }

            cursor.close();
        }

        cursorID.close();
        bd.close();
    }
}
