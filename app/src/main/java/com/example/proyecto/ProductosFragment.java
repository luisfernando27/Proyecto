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

public class ProductosFragment extends Fragment {
    List<ListaElementosProductosClientes> elements;

    public ProductosFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflar el diseño del fragmento
        View view = inflater.inflate(R.layout.fragment_productos, container, false);


        // Inicializar y configurar el RecyclerView
        init(view);

        return view;
    }

    private void init(View view) {
        elements = new ArrayList<>();

        elements.add(new ListaElementosProductosClientes("Producto 1", "Modelo", "50"));
        elements.add(new ListaElementosProductosClientes("Producto 2", "Modelo", "50"));

        ListAdapterProductosClientes listAdapterProductosClientes = new ListAdapterProductosClientes(elements, getContext());
        RecyclerView recyclerView = view.findViewById(R.id.recyclerListaProdClie);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(listAdapterProductosClientes);

    }
}