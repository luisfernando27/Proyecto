package com.example.proyecto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapterProductosClientes extends RecyclerView.Adapter<ListAdapterProductosClientes.ViewHolder>{
    private List<ListaElementosProductosClientes> mData;
    private LayoutInflater mInflater;
    private Context mContext;

    private String userEmail, idC, idE, idVP;

    public ListAdapterProductosClientes(List<ListaElementosProductosClientes> itemList, Context context, String userEmail) {
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mData = itemList;
        this.userEmail = userEmail;
    }

    @Override
    public int getItemCount() { return mData.size(); }

    @Override
    public ListAdapterProductosClientes.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.lista_elementos_prod_clientes, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));
    }

    public void setItems(List<ListaElementosProductosClientes> items) {
        mData = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView idP, nombreProducto, empresa, cantidad, precio;

        EditText cantidadIngresada;
        Button botonComprar;

        ViewHolder(View itemView) {
            super(itemView);
            idP = itemView.findViewById(R.id.idProdClient);
            nombreProducto = itemView.findViewById(R.id.nombreProdCLient);
            empresa = itemView.findViewById(R.id.empresa);
            cantidad = itemView.findViewById(R.id.cantidad);
            precio = itemView.findViewById(R.id.precio);

            cantidadIngresada = itemView.findViewById(R.id.txtPrecio);
            botonComprar = itemView.findViewById(R.id.botonComprar);

            botonComprar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Acción a realizar cuando se hace clic en el botón
                    // Aquí puedes insertar la lógica para registrar la oferta en la base de datos de clientes
                    int position = getAdapterPosition();
                    ListaElementosProductosClientes producto = mData.get(position);
                    String cantidadStr = cantidadIngresada.getText().toString();

                    if (cantidadStr.isEmpty()) {
                        Toast.makeText(mContext, "Ingrese una cantidad", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    int cantidad = Integer.parseInt(cantidadStr);
                    registrarProductosEnClientes(producto, cantidad);
                    cantidadIngresada.setText("");
                }
            });
        }

        void bindData(final ListaElementosProductosClientes item) {
            idP.setText(item.getID());
            nombreProducto.setText("Nombre del producto: " + item.getNombreProducto());
            empresa.setText("Empresa: " + item.getEmpresa());
            cantidad.setText("Cantidad: " + item.getCantidad());
            precio.setText("Precio: $" + item.getPrecio());
        }
    }

    private String generateUniqueVentaProductoId() {
        AdminSqLite admin = new AdminSqLite(mContext, "localMarket", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        // Realizar la consulta en la base de datos
        Cursor fila = bd.rawQuery("SELECT COUNT(*) FROM ventasProducto", null);
        String id;
        if (fila.moveToFirst()) {
            // Obtener el número de registros y añadir 1 para el nuevo ID
            int count = fila.getInt(0) + 1;
            // Formatear el ID con el prefijo "C" y 5 dígitos
            id = String.format("VP%05d", count);
        } else {
            // Si no hay registros, el ID inicial será "C00001"
            id = "VP00001";
        }
        fila.close();
        bd.close();
        return id;
    }

    private void registrarProductosEnClientes(ListaElementosProductosClientes producto, int cantidadSolicitada) {
        AdminSqLite admin = new AdminSqLite(mContext, "localMarket", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        Cursor cursorID = bd.rawQuery("SELECT idC FROM clientes WHERE correo='" + userEmail + "'", null);
        if (cursorID.moveToFirst()) {
            idC = cursorID.getString(0);
        }

        Cursor cursorIDE = bd.rawQuery("SELECT idE FROM empresas WHERE nombre='" + producto.getEmpresa() + "'", null);
        if (cursorIDE.moveToFirst()) {
            idE = cursorIDE.getString(0);
        }

        idVP = generateUniqueVentaProductoId();

        Cursor datos = bd.rawQuery("SELECT cantidad FROM productos WHERE idP='" + producto.getID() + "'", null);
        if (datos.moveToFirst()) {
            int cantidad = datos.getInt(0);
            if (cantidadSolicitada == 0) {
                Toast.makeText(mContext, "Ingresa un número correcto", Toast.LENGTH_SHORT).show();
                return;
            } else if (cantidadSolicitada > cantidad) {
                Toast.makeText(mContext, "Cantidad insuficiente", Toast.LENGTH_SHORT).show();
                return;
            }

            Cursor exitencia = bd.rawQuery("SELECT totalC FROM ventasProducto WHERE idP='" + producto.getID() + "' and idC='" + idC + "'", null);
            if (exitencia.moveToFirst()) {
                float totalC = exitencia.getFloat(0);
                float totalc= totalC + cantidadSolicitada;

                ContentValues valores6 = new ContentValues();
                valores6.put("totalC", totalc);

                int rowsUpdated = bd.update("ventasProducto", valores6, "idP=? and idC=?", new String[]{producto.getID(), idC});
                if (rowsUpdated > 0) {
                    Toast.makeText(mContext, "Compra realizada con éxito", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, "Error al actualizar la oferta", Toast.LENGTH_SHORT).show();
                }

                float totalp= cantidad - cantidadSolicitada;

                ContentValues valores7 = new ContentValues();
                valores7.put("cantidad", totalp);

                int rowsUpdated1 = bd.update("productos", valores7, "idP=?", new String[]{producto.getID()});
                if (rowsUpdated1 > 0) {

                } else {
                    Toast.makeText(mContext, "Error al actualizar la oferta", Toast.LENGTH_SHORT).show();
                }

            }else{

                ContentValues registro1 = new ContentValues();
                registro1.put("idVP", idVP);
                registro1.put("idP", producto.getID());
                registro1.put("idC", idC);
                registro1.put("idE", idE);
                registro1.put("totalC", cantidadSolicitada);

                bd.insert("ventasProducto", null, registro1);

                float total= cantidad - cantidadSolicitada;

                ContentValues valores5 = new ContentValues();
                valores5.put("cantidad", total);

                int rowsUpdated = bd.update("productos", valores5, "idP=?", new String[]{producto.getID()});
                if (rowsUpdated > 0) {
                    Toast.makeText(mContext, "Compra realizada con éxito", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, "Error al actualizar la oferta", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }
}
