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

public class CarritoFragment extends Fragment {

    List<ListaElementosCarrito> elements;

    private ListAdapterCarrito listAdapter;

    private String userEmail;

    public CarritoFragment() {}

    public static CarritoFragment newInstance(String userEmail) {
        CarritoFragment fragment = new CarritoFragment();
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
        View view = inflater.inflate(R.layout.fragment_carrito, container, false);

        Bundle args = getArguments();
        if (args != null) {
            userEmail = args.getString("CORREO");
        }

        // Inicializar y configurar el RecyclerView
        init(view);

        loadDataFromDatabase();

        return view;
    }

    private void init(View view) {
        elements = new ArrayList<>();
        listAdapter = new ListAdapterCarrito(elements, getContext(), userEmail);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerListaCarrito);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(listAdapter);
    }

    private void loadDataFromDatabase() {
        AdminSqLite admin = new AdminSqLite(getContext(), "localMarket", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        Cursor cursorID = bd.rawQuery("SELECT idC FROM clientes WHERE correo='" + userEmail + "'", null);

        if (cursorID.moveToFirst()) {
            String idC = cursorID.getString(0);


            Cursor cursor = bd.rawQuery("SELECT productos.nombre_producto, ventasProducto.totalC, productos.precio FROM ventasProducto, clientes, productos WHERE ventasProducto.idC=clientes.idC and ventasProducto.idP=productos.idP and clientes.idC='" + idC + "'", null);

            if (cursor.moveToFirst()) {
                do {
                    String nombreProductos = cursor.getString(0);
                    String cantidad = cursor.getString(1);
                    String precio = cursor.getString(2);
                    elements.add(new ListaElementosCarrito(nombreProductos, cantidad, precio));
                } while (cursor.moveToNext());
            }

            cursor.close();
            bd.close();

            listAdapter.setItems(elements);
        }
    }
}
