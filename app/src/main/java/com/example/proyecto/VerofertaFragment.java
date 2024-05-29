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
public class VerofertaFragment extends Fragment {
    List<ListaElementosOfertasEmpresa> elements;

    public VerofertaFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflar el diseño del fragmento
        View view = inflater.inflate(R.layout.fragment_veroferta, container, false);


        // Inicializar y configurar el RecyclerView
        init(view);

        return view;
    }

    private void init(View view) {
        elements = new ArrayList<>();

        elements.add(new ListaElementosOfertasEmpresa("Oferta 1", "2", "30", "500", "29-05-24", "02-06-24"));
        elements.add(new ListaElementosOfertasEmpresa("Oferta 2", "3", "35", "500", "29-05-24", "02-06-24"));

        ListAdapterOfertasEmpresa listAdapterOfertasEmpresa = new ListAdapterOfertasEmpresa(elements, getContext());
        RecyclerView recyclerView = view.findViewById(R.id.recyclerListaofertasReg);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(listAdapterOfertasEmpresa);

    }
}