package com.example.proyecto;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }
    public void pantallaPrincipal(View v) {
        Intent intent = new Intent(this, PrincipalRegistro.class);
        startActivity(intent);
    }
}