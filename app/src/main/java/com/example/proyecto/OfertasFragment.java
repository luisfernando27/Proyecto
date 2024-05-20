package com.example.proyecto;

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
    List<ListaElementosClientes> elements;

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

        return view;
    }

    private void init(View view) {
        elements = new ArrayList<>();

        elements.add(new ListaElementosClientes("Oferta 2 Megas", "Grupo modelo", "2 Megas por $50", "$50"));
        elements.add(new ListaElementosClientes("Oferta 2 Power Azul", "Coca-Cola", "2 Power Azul por $50", "$50"));

        ListAdapter listAdapter = new ListAdapter(elements, getContext());
        RecyclerView recyclerView = view.findViewById(R.id.recyclerListaOfertasUs);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(listAdapter);
    }

}