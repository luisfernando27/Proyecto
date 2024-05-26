package com.example.proyecto;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

// Adaptador para la lista de ofertas de empresas
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<ListaElementosClientes> mData;
    private LayoutInflater mInflater;
    private Context mContext; // Mantener una referencia al contexto
    private String userEmail, idC;


    // Constructor que acepta la lista de elementos, el contexto y el correo electrónico del usuario
    public ListAdapter(List<ListaElementosClientes> elements, Context context, String userEmail) {
        this.mData = elements;
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.userEmail = userEmail;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar el diseño de cada elemento de la lista
        View view = mInflater.inflate(R.layout.lista_elementos_ofertas, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Vincular los datos de la posición actual al ViewHolder
        holder.bindData(mData.get(position));
    }

    public void setItems(List<ListaElementosClientes> items) {
        // Actualizar la lista de elementos y notificar al adaptador
        mData = items;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id, nombreOferta, empresa, cantidadOfertaPorUsuario, precio;
        EditText cantidadIngresada;
        Button botonComprar;

        ViewHolder(View itemView) {
            super(itemView);
            // Obtener referencias a las vistas en el diseño del elemento de la lista
            cantidadIngresada = itemView.findViewById(R.id.txtPrecio);
            id = itemView.findViewById(R.id.idOferta);
            nombreOferta = itemView.findViewById(R.id.nombreOferta);
            empresa = itemView.findViewById(R.id.empresa);
            cantidadOfertaPorUsuario = itemView.findViewById(R.id.cantidadOfertaPorUsuario);
            precio = itemView.findViewById(R.id.precio);
            botonComprar = itemView.findViewById(R.id.botonComprar);

            // Configurar el listener para el botón de compra
            botonComprar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Acción a realizar cuando se hace clic en el botón
                    // Aquí puedes insertar la lógica para registrar la oferta en la base de datos de clientes
                    int position = getAdapterPosition();
                    ListaElementosClientes oferta = mData.get(position);
                    String cantidadStr = cantidadIngresada.getText().toString();

                    if (cantidadStr.isEmpty()) {
                        Toast.makeText(mContext, "Ingrese una cantidad", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    int cantidad = Integer.parseInt(cantidadStr);
                    registrarOfertaEnClientes(oferta, cantidad);
                    cantidadIngresada.setText("");
                }
            });
        }

        void bindData(final ListaElementosClientes item) {
            // Asignar los datos del elemento actual a las vistas
            id.setText(item.getID());
            nombreOferta.setText(item.getNombreOferta());
            empresa.setText(item.getEmpresa());
            cantidadOfertaPorUsuario.setText(item.getCantidadOfertaPorUsuario());
            precio.setText(item.getPrecio());

        }
    }

    // Método para registrar la oferta en la base de datos de clientes
    private void registrarOfertaEnClientes(ListaElementosClientes oferta, int cantidadSolicitada) {
        AdminSqLite admin = new AdminSqLite(mContext, "localMarket", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        Cursor cursorID = bd.rawQuery("SELECT idC FROM clientes WHERE correo='" + userEmail + "'", null);
        if (cursorID.moveToFirst()) {
            idC = cursorID.getString(0);
        }

        Cursor cursor_fecha = bd.rawQuery("SELECT fecha_inicio, fecha_fin, total_oferta, maximo_clientes FROM ofertasEmpresas WHERE idF='" + oferta.getID() + "'", null);
        Cursor maximo = bd.rawQuery("SELECT cantidad_maxima FROM ofertaCliente WHERE idC='" + idC + "' and idF='" + oferta.getID() + "'", null);

        if (!cursor_fecha.moveToFirst()) {
            Toast.makeText(mContext, "Error al obtener la oferta", Toast.LENGTH_SHORT).show();
            return;
        }

        String fecha_inicio = cursor_fecha.getString(0);
        String fecha_fin = cursor_fecha.getString(1);
        int total_oferta = cursor_fecha.getInt(2);
        int maximo_clientes = cursor_fecha.getInt(3);

        Calendar fechaActual = Calendar.getInstance();
        int mesActual = fechaActual.get(Calendar.MONTH) + 1;
        String formatoHoy = String.format(Locale.getDefault(), "%02d/%02d/%04d",
                fechaActual.get(Calendar.DAY_OF_MONTH), mesActual, fechaActual.get(Calendar.YEAR));

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        try {
            Date fechaInicioDate = dateFormat.parse(fecha_inicio);
            Date fechaFinDate = dateFormat.parse(fecha_fin);
            Date fechaHoyDate = dateFormat.parse(formatoHoy);

            if (fechaHoyDate.after(fechaFinDate)) {
                Toast.makeText(mContext, "La oferta ha expirado ", Toast.LENGTH_SHORT).show();
                return;
            } else if (fechaHoyDate.before(fechaInicioDate)) {
                Toast.makeText(mContext, "La oferta no ha comenzado ", Toast.LENGTH_SHORT).show();
                return;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            Log.e("ERROR", "Error parsing dates: " + e.getMessage());
            return;
        }

        if (cantidadSolicitada > total_oferta) {
            Toast.makeText(mContext, "Cantidad de oferta insuficiente", Toast.LENGTH_SHORT).show();
            return;
        } else if (cantidadSolicitada > maximo_clientes) {
            Toast.makeText(mContext, "Solo puedes comprar máximo " + maximo_clientes + " ofertas", Toast.LENGTH_SHORT).show();
            return;
        } else if (cantidadSolicitada == 0) {
            Toast.makeText(mContext, "Ingresa un número correcto", Toast.LENGTH_SHORT).show();
            return;
        }


        if (maximo.moveToFirst()) {
            int cantidad_maxima = maximo.getInt(0);
            if (cantidad_maxima < maximo_clientes) {
                int total = cantidad_maxima + cantidadSolicitada;
                if (total <= maximo_clientes) {
                    ContentValues valores = new ContentValues();
                    valores.put("cantidad_maxima", total);

                    int rowsUpdated = bd.update("ofertaCliente", valores, "idC=? and idF=?", new String[]{idC, oferta.getID()});
                    if (rowsUpdated > 0) {
                        Toast.makeText(mContext, "Compra realizada con éxito", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(mContext, "Error al actualizar la oferta", Toast.LENGTH_SHORT).show();
                    }

                    int nuevoTotalOferta = total_oferta - cantidadSolicitada;
                    ContentValues valores1 = new ContentValues();
                    valores1.put("total_oferta", nuevoTotalOferta);

                    int rowsUpdated1 = bd.update("ofertasEmpresas", valores1, "idF=?", new String[]{oferta.getID()});
                    if (rowsUpdated1 > 0) {
                        Toast.makeText(mContext, "Compra realizada con éxito", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(mContext, "Error al actualizar la oferta", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    int limite = maximo_clientes - cantidad_maxima;
                    Toast.makeText(mContext, "Las ofertas que puedes comprar son: " + limite, Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(mContext, "Tienes el maximo de ofertas", Toast.LENGTH_SHORT).show();
            }

        } else {
            ContentValues registro = new ContentValues();
            registro.put("idC", idC);
            registro.put("idF", oferta.getID());
            registro.put("cantidad_maxima", cantidadSolicitada);

            bd.insert("ofertaCliente", null, registro);
            Toast.makeText(mContext, "Compra realizada con éxito", Toast.LENGTH_SHORT).show();

        }
    }
}

