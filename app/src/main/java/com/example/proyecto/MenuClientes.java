package com.example.proyecto;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MenuClientes  extends AppCompatActivity{
    List<ListaElementosClientes> elements;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_clientes);

        init();
    }

    public void init(){
    elements = new ArrayList<>();

    elements.add(new ListaElementosClientes("Oferta 2 Megas", "Grupo modelo", "2 Megas por $50", "$50"));
    elements.add(new ListaElementosClientes("Oferta 2 Power Azul", "Coca-Cola", "2 Power Azul por $50", "$50"));
    ListAdapter listAdapter = new ListAdapter(elements, this);
    RecyclerView recyclerView = findViewById(R.id.recyclerListaOfertasUs);
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setAdapter(listAdapter);
    }
}
