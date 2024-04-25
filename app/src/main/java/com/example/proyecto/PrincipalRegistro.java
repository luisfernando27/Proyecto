package com.example.proyecto;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class PrincipalRegistro extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal_registro);
    }
    public void pantallaClientes(View v) {
        Intent intent = new Intent(this, RegistroClientes.class);
        startActivity(intent);
    }

    public void pantallaEmpresa(View v) {
        Intent intent = new Intent(this, RegistroEmpresas.class);
        startActivity(intent);
    }

    public void atrasPrincipal(View v) {
        onBackPressed();

    }
}
