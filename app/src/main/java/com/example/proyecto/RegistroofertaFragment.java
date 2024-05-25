package com.example.proyecto;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegistroofertaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistroofertaFragment extends Fragment {

    private static final String ARG_CORREO = "correo";

    private String mCorreo;
    private String idEmpresa;

    private EditText txtIdOferta, txtNombreOferta, txtPrecioOferta, txtTotalOfertas, txtMaximoClientes, txtFechaInicio, txtFechaFin;
    private Button btnRegistrar;

    public RegistroofertaFragment() {
        // Required empty public constructor
    }

    public static RegistroofertaFragment newInstance(String correo) {
        RegistroofertaFragment fragment = new RegistroofertaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CORREO, correo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCorreo = getArguments().getString(ARG_CORREO);
            obtenerIdEmpresa(mCorreo);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.registro_ofertas, container, false);

        txtIdOferta = view.findViewById(R.id.TxtidOferta);
        txtNombreOferta = view.findViewById(R.id.TxtNombreOferta);
        txtPrecioOferta = view.findViewById(R.id.TxtPrecioOferta);
        txtTotalOfertas = view.findViewById(R.id.TxttotalOfertas);
        txtMaximoClientes = view.findViewById(R.id.TxtmaximoClientes);
        txtFechaInicio = view.findViewById(R.id.TxtfechaInicio);
        txtFechaFin = view.findViewById(R.id.TxtfechaFin);
        btnRegistrar = view.findViewById(R.id.BtnRegistar);

        txtFechaInicio.setOnClickListener(v -> showDatePickerDialog(txtFechaInicio));
        txtFechaFin.setOnClickListener(v -> showDatePickerDialog(txtFechaFin));

        String ofertaID = generateUniqueOfertaId();
        txtIdOferta.setText(ofertaID);
        txtIdOferta.setFocusable(false);
        txtIdOferta.setClickable(false);

        btnRegistrar.setOnClickListener(v -> guardarOferta());

        return view;
    }

    private void obtenerIdEmpresa(String correo) {
        AdminSqLite admin = new AdminSqLite(getContext(), "localMarket", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor fila = bd.rawQuery("select idE from empresas where correo='" + correo + "'", null);
        if (fila.moveToFirst()) {
            idEmpresa = fila.getString(0);
        }
        fila.close();
        bd.close();
    }

    private void showDatePickerDialog(EditText editText) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                (view, year1, monthOfYear, dayOfMonth) -> {
                    String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1;
                    editText.setText(selectedDate);
                }, year, month, day);
        datePickerDialog.show();
    }

    private String generateUniqueOfertaId() {
        AdminSqLite admin = new AdminSqLite(getContext(), "localMarket", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor fila = bd.rawQuery("SELECT COUNT(*) FROM ofertasEmpresas", null);
        String id;
        if (fila.moveToFirst()) {
            int count = fila.getInt(0) + 1;
            id = String.format("O%05d", count);
        } else {
            id = "O00001";
        }
        fila.close();
        bd.close();
        return id;
    }

    private void guardarOferta() {
        String idOferta1 = txtIdOferta.getText().toString().trim();
        String nombreOferta1 = txtNombreOferta.getText().toString().trim();
        String precioOferta1 = txtPrecioOferta.getText().toString().trim();
        String totalOfertas1 = txtTotalOfertas.getText().toString().trim();
        String maximoClientes1 = txtMaximoClientes.getText().toString().trim();
        String fechaInicio1 = txtFechaInicio.getText().toString().trim();
        String fechaFin1 = txtFechaFin.getText().toString().trim();

        if (nombreOferta1.isEmpty()) {
            Toast.makeText(getContext(), "Por favor, ingrese el nombre de la oferta", Toast.LENGTH_SHORT).show();
            return;
        }
        if (precioOferta1.isEmpty()) {
            Toast.makeText(getContext(), "Por favor, ingrese el precio de la oferta", Toast.LENGTH_SHORT).show();
            return;
        }
        if (totalOfertas1.isEmpty()) {
            Toast.makeText(getContext(), "Por favor, ingrese el total de ofertas", Toast.LENGTH_SHORT).show();
            return;
        }
        if (maximoClientes1.isEmpty()) {
            Toast.makeText(getContext(), "Por favor, ingrese el máximo de clientes", Toast.LENGTH_SHORT).show();
            return;
        }
        if (fechaInicio1.isEmpty()) {
            Toast.makeText(getContext(), "Por favor, ingrese la fecha de inicio", Toast.LENGTH_SHORT).show();
            return;
        }
        if (fechaFin1.isEmpty()) {
            Toast.makeText(getContext(), "Por favor, ingrese la fecha de fin", Toast.LENGTH_SHORT).show();
            return;
        }

        AdminSqLite admin = new AdminSqLite(getContext(), "localMarket", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("idF", idOferta1);
        registro.put("idE", idEmpresa);
        registro.put("nombre_oferta", nombreOferta1);
        registro.put("precio_oferta", precioOferta1);
        registro.put("total_oferta", totalOfertas1);
        registro.put("maximo_clientes", maximoClientes1);
        registro.put("fecha_inicio", fechaInicio1);
        registro.put("fecha_fin", fechaFin1);

        bd.insert("ofertasEmpresas", null, registro);

        txtIdOferta.setText(generateUniqueOfertaId());
        txtNombreOferta.setText("");
        txtPrecioOferta.setText("");
        txtTotalOfertas.setText("");
        txtMaximoClientes.setText("");
        txtFechaInicio.setText("");
        txtFechaFin.setText("");

        Toast.makeText(getContext(), "Datos guardados correctamente", Toast.LENGTH_SHORT).show();
    }
}
