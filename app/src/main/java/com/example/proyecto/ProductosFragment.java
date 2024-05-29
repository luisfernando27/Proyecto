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

public class ProductosFragment extends Fragment {
    private List<ListaElementosProductosClientes> elements;
    private ListAdapterProductosClientes listAdapter;

    private String userEmail;

    public ProductosFragment() {}

    public static ProductosFragment newInstance(String userEmail) {
        ProductosFragment fragment = new ProductosFragment();
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
        View view = inflater.inflate(R.layout.fragment_productos, container, false);

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
        listAdapter = new ListAdapterProductosClientes(elements, getContext(), userEmail);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerListaProdClie); // Asegúrate de que este ID coincida con tu layout
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(listAdapter);
    }

    private void loadDataFromDatabase() {
        AdminSqLite admin = new AdminSqLite(getContext(), "localMarket", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        Cursor cursor = bd.rawQuery("SELECT productos.idP, productos.nombre_producto, empresas.nombre, productos.cantidad, productos.precio FROM productos, empresas WHERE productos.idE=empresas.idE", null);

        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(0);
                String nombreProductos = cursor.getString(1);
                String empresa = cursor.getString(2);
                String cantidad = cursor.getString(3);
                String precio = cursor.getString(4);
                elements.add(new ListaElementosProductosClientes(id, nombreProductos, empresa, cantidad, precio));
            } while (cursor.moveToNext());
        }

        cursor.close();
        bd.close();

        listAdapter.setItems(elements);
    }
}
