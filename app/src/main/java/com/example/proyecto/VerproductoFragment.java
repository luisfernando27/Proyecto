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


public class VerproductoFragment extends Fragment {
    List<ListaElementosProductosEmpresa> elements;

    public VerproductoFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflar el diseño del fragmento
        View view = inflater.inflate(R.layout.fragment_verproducto, container, false);


        // Inicializar y configurar el RecyclerView
        init(view);

        return view;
    }

    private void init(View view) {
        elements = new ArrayList<>();

        elements.add(new ListaElementosProductosEmpresa("Mega", "0001", "50"));
        elements.add(new ListaElementosProductosEmpresa("Corona", "0002", "50"));

        ListAdapterProductosEmpresa listAdapterProductosEmpresa = new ListAdapterProductosEmpresa(elements, getContext());
        RecyclerView recyclerView = view.findViewById(R.id.recyclerListaProdReg);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(listAdapterProductosEmpresa);

    }
}