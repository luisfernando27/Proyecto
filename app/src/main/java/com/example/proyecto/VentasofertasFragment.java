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

public class VentasofertasFragment extends Fragment {
    List<ListaElementosVentasOfertas> elements;

    public VentasofertasFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflar el diseño del fragmento
        View view = inflater.inflate(R.layout.fragment_ventasofertas, container, false);


        // Inicializar y configurar el RecyclerView
        init(view);

        return view;
    }

    private void init(View view) {
        elements = new ArrayList<>();

        elements.add(new ListaElementosVentasOfertas("Oferta 1", "0001", "5", "500"));
        elements.add(new ListaElementosVentasOfertas("Oferta 2", "0002", "5", "500"));

        ListAdapterVentasOfertas listAdapterVentasOfertas = new ListAdapterVentasOfertas(elements, getContext());
        RecyclerView recyclerView = view.findViewById(R.id.recyclerListaVentaOfert);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(listAdapterVentasOfertas);

    }
}