package com.example.proyecto;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegistroproductoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistroproductoFragment extends Fragment {

    private static final String ARG_CORREO = "correo";
    private String mCorreo;
    private String idEmpresa;

    private EditText idProducto, nombreProducto, descripcion, cantidad, precio;
    private Button btnGuardar;

    public RegistroproductoFragment() {
        // Required empty public constructor
    }

    public static RegistroproductoFragment newInstance(String correo) {
        RegistroproductoFragment fragment = new RegistroproductoFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.registro_productos, container, false);

        idProducto = view.findViewById(R.id.TxtidProducto);
        nombreProducto = view.findViewById(R.id.TxtNombreProducto);
        descripcion = view.findViewById(R.id.Txtdescripcion);
        cantidad = view.findViewById(R.id.Txtcantidad);
        precio = view.findViewById(R.id.Txtprecio);
        btnGuardar = view.findViewById(R.id.BtnRegistar);

        String productoID = generateUniqueProductoId();
        idProducto.setText(productoID);
        idProducto.setFocusable(false);
        idProducto.setClickable(false);

        btnGuardar.setOnClickListener(this::guardarProducto);

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

    private String generateUniqueProductoId() {
        AdminSqLite admin = new AdminSqLite(getContext(), "localMarket", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor fila = bd.rawQuery("SELECT COUNT(*) FROM productos", null);
        String id;
        if (fila.moveToFirst()) {
            int count = fila.getInt(0) + 1;
            id = String.format("P%05d", count);
        } else {
            id = "P00001";
        }
        fila.close();
        bd.close();
        return id;
    }

    public void guardarProducto(View v) {
        String idProducto1 = idProducto.getText().toString().trim();
        String nombreProducto1 = nombreProducto.getText().toString().trim();
        String descripcion1 = descripcion.getText().toString().trim();
        String cantidad1 = cantidad.getText().toString().trim();
        String precio1 = precio.getText().toString().trim();

        if (nombreProducto1.isEmpty()) {
            Toast.makeText(getActivity(), "Por favor, ingrese el nombre del producto", Toast.LENGTH_SHORT).show();
            return;
        }
        if (descripcion1.isEmpty()) {
            Toast.makeText(getActivity(), "Por favor, ingrese la descripcion del producto", Toast.LENGTH_SHORT).show();
            return;
        }
        if (cantidad1.isEmpty()) {
            Toast.makeText(getActivity(), "Por favor, ingrese la cantidad del producto", Toast.LENGTH_SHORT).show();
            return;
        }
        if (precio1.isEmpty()) {
            Toast.makeText(getActivity(), "Por favor, ingrese el precio del producto", Toast.LENGTH_SHORT).show();
            return;
        }

        AdminSqLite admin = new AdminSqLite(getContext(), "localMarket", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        ContentValues registro = new ContentValues();
        registro.put("idP", idProducto1);
        registro.put("idE", idEmpresa);
        registro.put("nombre_producto", nombreProducto1);
        registro.put("descripcion", descripcion1);
        registro.put("cantidad", cantidad1);
        registro.put("precio", precio1);

        bd.insert("productos", null, registro);

        idProducto.setText(generateUniqueProductoId());
        nombreProducto.setText("");
        descripcion.setText("");
        cantidad.setText("");
        precio.setText("");

        Toast.makeText(getActivity(), "Datos guardados correctamente", Toast.LENGTH_SHORT).show();
    }
}
