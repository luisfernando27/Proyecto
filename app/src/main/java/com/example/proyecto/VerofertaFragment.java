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

public class VerofertaFragment extends Fragment {

    private List<ListaElementosOfertasEmpresa> elements;
    private ListAdapterOfertasEmpresa listAdapter;

    private String userEmail;

    public VerofertaFragment() {}

    public static VerofertaFragment newInstance(String userEmail) {
        VerofertaFragment fragment = new VerofertaFragment();
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
        View view = inflater.inflate(R.layout.fragment_veroferta, container, false);

        Bundle args = getArguments();
        if (args != null) {
            userEmail = args.getString("CORREO");
        }

        // Initialize RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerListaofertasReg);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize List and Adapter
        elements = new ArrayList<>();
        listAdapter = new ListAdapterOfertasEmpresa(elements, getContext());
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

            // Query to select offers from the company with idE
            Cursor cursor = bd.rawQuery("SELECT idF, nombre_oferta, precio_oferta, total_oferta, maximo_clientes, fecha_inicio, fecha_fin FROM ofertasEmpresas WHERE idE='" + idE + "'", null);

            if (cursor.moveToFirst()) {
                do {
                    String idO = cursor.getString(0);
                    String nombreOferta = cursor.getString(1);
                    String precioOferta = cursor.getString(2);
                    String totalOfertas = cursor.getString(3);
                    String maximaCantidad = cursor.getString(4);
                    String fechaInicio = cursor.getString(5);
                    String fechaFin = cursor.getString(6);
                    elements.add(new ListaElementosOfertasEmpresa(idO, nombreOferta, precioOferta, totalOfertas, maximaCantidad, fechaInicio, fechaFin));
                } while (cursor.moveToNext());
            }

            cursor.close();
        }

        cursorID.close();
        bd.close();

    }
}
