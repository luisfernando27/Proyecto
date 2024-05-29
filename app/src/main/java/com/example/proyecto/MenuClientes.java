package com.example.proyecto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import com.google.android.material.navigation.NavigationView;
public class MenuClientes extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;

    private String userEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prueba_menu);
        Toolbar toolbar = findViewById(R.id.toolbar); //Ignore red line errors
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        userEmail = getIntent().getExtras().getString("CORREO");

        if (savedInstanceState == null) {
            OfertasFragment ofertasFragment = OfertasFragment.newInstance(userEmail);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, ofertasFragment).commit();
            navigationView.setCheckedItem(R.id.nav_ofertas);
        }
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.nav_ofertas){
            OfertasFragment ofertasFragment = OfertasFragment.newInstance(userEmail);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, ofertasFragment).commit();
        } else if (itemId == R.id.nav_productos) {
            ProductosFragment productosFragment = ProductosFragment.newInstance(userEmail);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, productosFragment).commit();
        } else if (itemId == R.id.nav_carrito) {
            CarritoFragment carritoFragment = CarritoFragment.newInstance(userEmail);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, carritoFragment).commit();
        } else if (itemId == R.id.nav_info_us) {
            InfoclientFragment infoclientFragment = InfoclientFragment.newInstance(userEmail);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, infoclientFragment).commit();
        } else if (itemId == R.id.nav_logout) {
            logout();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    private void logout() {
        // Mostrar mensaje de cierre de sesión
        Toast.makeText(this, "Sesión cerrada", Toast.LENGTH_SHORT).show();

        // Redirigir a la pantalla de inicio de sesión
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish(); // Finalizar la actividad actual
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}