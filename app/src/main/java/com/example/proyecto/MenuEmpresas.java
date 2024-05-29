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

public class MenuEmpresas extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //Aqui va el código del menu para cada uno de los apartados

    private DrawerLayout drawerLayout;
    private String userEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prueba_menu_empresa);
        Toolbar toolbar = findViewById(R.id.toolbar); //Ignore red line errors
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Recuperar el correo electrónico del intent
        userEmail = getIntent().getExtras().getString("CORREO");

        if (savedInstanceState == null) {
            RegistroofertaFragment registroofertaFragment = RegistroofertaFragment.newInstance(userEmail);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, registroofertaFragment).commit();
            navigationView.setCheckedItem(R.id.nav_ofertas);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.nav_ofertas) {
            RegistroofertaFragment registroofertaFragment = RegistroofertaFragment.newInstance(userEmail);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, registroofertaFragment).commit();
        } else if (itemId == R.id.nav_productos) {
            RegistroproductoFragment registroproductoFragment = RegistroproductoFragment.newInstance(userEmail);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, registroproductoFragment).commit();
        } else if (itemId == R.id.nav_ofertas_registradas) {
            VerofertaFragment verofertaFragment = VerofertaFragment.newInstance(userEmail);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, verofertaFragment).commit();
        } else if (itemId == R.id.nav_productos_registradas) {
            VerproductoFragment verproductoFragment = VerproductoFragment.newInstance(userEmail);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, verproductoFragment).commit();
        } else if (itemId == R.id.nav_venta_ofertas) {
            VentasofertasFragment ventasofertasFragment = VentasofertasFragment.newInstance(userEmail);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, ventasofertasFragment).commit();
        } else if (itemId == R.id.nav_venta_productos) {
            VentasproductosFragment ventasproductosFragment = VentasproductosFragment.newInstance(userEmail);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, ventasproductosFragment).commit();
        } else if (itemId == R.id.nav_info_us) {
            InfoempresaFragment infoempresaFragment = InfoempresaFragment.newInstance(userEmail);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, infoempresaFragment).commit();
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
