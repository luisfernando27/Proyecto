package com.example.proyecto;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

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
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new VerofertaFragment()).commit();
        } else if (itemId == R.id.nav_productos_registradas) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new VerproductoFragment()).commit();
        } else if (itemId == R.id.nav_venta_ofertas) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new VentasofertasFragment()).commit();
        } else if (itemId == R.id.nav_venta_productos) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new VentasproductosFragment()).commit();
        } else if (itemId == R.id.nav_info_us) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new InfoclientFragment()).commit();
        } else if (itemId == R.id.nav_logout) {
            Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
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
