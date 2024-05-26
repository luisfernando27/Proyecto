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

public class OfertasFragment extends Fragment {
    private List<ListaElementosClientes> elements;
    private ListAdapter listAdapter;

    private String userEmail;

    // Constructor vacío requerido
    public OfertasFragment() {}

    // Método estático para crear una nueva instancia del fragmento y pasar el correo electrónico como argumento
    public static OfertasFragment newInstance(String userEmail) {
        OfertasFragment fragment = new OfertasFragment();
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
        View view = inflater.inflate(R.layout.menu_clientes, container, false);

        // Obtener el correo electrónico de los argumentos
        Bundle args = getArguments();
        if (args != null) {
            userEmail = args.getString("CORREO");
        }

        // Inicializar y configurar el RecyclerView
        init(view);

        // Cargar datos desde la base de datos
        loadDataFromDatabase();

        return view;
    }

    private void init(View view) {
        elements = new ArrayList<>();
        listAdapter = new ListAdapter(elements, getContext(), userEmail); // Pasar el correo electrónico al constructor del ListAdapter

        RecyclerView recyclerView = view.findViewById(R.id.recyclerListaOfertasUs);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(listAdapter);

    }

    private void loadDataFromDatabase() {
        AdminSqLite admin = new AdminSqLite(getContext(), "localMarket", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        Cursor cursor = bd.rawQuery("SELECT ofertasEmpresas.idF, ofertasEmpresas.nombre_oferta, empresas.nombre, ofertasEmpresas.maximo_clientes, ofertasEmpresas.precio_oferta, ofertasEmpresas.fecha_inicio, ofertasEmpresas.fecha_fin FROM ofertasEmpresas, empresas WHERE ofertasEmpresas.idE=empresas.idE", null);

        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(0);
                String nombreOferta = cursor.getString(1);
                String empresa = cursor.getString(2);
                String cantidadOfertaPorUsuario = cursor.getString(3);
                String precio = cursor.getString(4);
                String fechaI = cursor.getString(5);
                String fechaF = cursor.getString(6);
                elements.add(new ListaElementosClientes(id, nombreOferta, empresa, cantidadOfertaPorUsuario, precio, fechaI, fechaF));
            } while (cursor.moveToNext());
        }

        cursor.close();
        bd.close();

        listAdapter.setItems(elements);
    }
}

