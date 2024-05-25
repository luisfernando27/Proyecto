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

public class OfertasFragment extends Fragment {
    private List<ListaElementosClientes> elements;
    private ListAdapter listAdapter;

    public OfertasFragment() {
        // Constructor vacío requerido
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflar el diseño del fragmento
        View view = inflater.inflate(R.layout.menu_clientes, container, false);

        // Inicializar y configurar el RecyclerView
        init(view);

        // Cargar datos desde la base de datos
        loadDataFromDatabase();

        return view;
    }

    private void init(View view) {
        elements = new ArrayList<>();
        listAdapter = new ListAdapter(elements, getContext());

        RecyclerView recyclerView = view.findViewById(R.id.recyclerListaOfertasUs);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(listAdapter);
    }

    private void loadDataFromDatabase() {
        AdminSqLite admin = new AdminSqLite(getContext(), "localMarket", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        Cursor cursor = bd.rawQuery("SELECT ofertasEmpresas.nombre_oferta, empresas.nombre, ofertasEmpresas.maximo_clientes, ofertasEmpresas.precio_oferta FROM ofertasEmpresas, empresas WHERE ofertasEmpresas.idE=empresas.idE", null);

        if (cursor.moveToFirst()) {
            do {
                String nombreOferta = cursor.getString(0);
                String empresa = cursor.getString(1);
                String cantidadOfertaPorUsuario = cursor.getString(2);
                String precio = cursor.getString(3);
                elements.add(new ListaElementosClientes(nombreOferta, empresa, cantidadOfertaPorUsuario, precio));
            } while (cursor.moveToNext());
        }

        cursor.close();
        bd.close();

        listAdapter.setItems(elements);
    }
}
