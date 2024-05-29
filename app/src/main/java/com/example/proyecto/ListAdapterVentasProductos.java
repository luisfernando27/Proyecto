package com.example.proyecto;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
public class ListAdapterVentasProductos extends RecyclerView.Adapter<ListAdapterVentasProductos.ViewHolder>{
    private List<ListaElementosVentasProductos> mData;
    private LayoutInflater mInflater;
    private Context context;

    public ListAdapterVentasProductos (List<ListaElementosVentasProductos> itemList, Context context) {

        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    @Override
    public int getItemCount() { return mData.size(); }

    @Override
    public ListAdapterVentasProductos.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.lista_elementos_ventas_prod, null);
        return new ListAdapterVentasProductos.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListAdapterVentasProductos.ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));
    }

    public void setItems(List<ListaElementosVentasProductos> items) { mData = items; }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconImage;
        TextView idvp, nombreProducto, totalC, cantidad, precio, total_venta;
        ViewHolder(View itemView) {
            super(itemView);
            idvp = itemView.findViewById(R.id.idProd);
            nombreProducto = itemView.findViewById(R.id.nombreProducto);
            totalC = itemView.findViewById(R.id.totalPro);
            cantidad = itemView.findViewById(R.id.cant);
            precio = itemView.findViewById(R.id.precioProd);
            total_venta = itemView.findViewById(R.id.total_venta);



        }
        void bindData(final ListaElementosVentasProductos item) {
            idvp.setText("ID Venta: " + item.getIDVP());
            nombreProducto.setText("Nombre producto: " + item.getNombreProducto());
            totalC.setText("Total vendido: " + item.getTotalC());
            cantidad.setText("Restantes: " + item.getCantidad());
            precio.setText("Precio: $" + item.getPrecio());

            // Calcular el total multiplicando la cantidad por el precio
            try {
                int cantidadValue = Integer.parseInt(item.getTotalC());
                double precioValue = Double.parseDouble(item.getPrecio());
                double totalValue = cantidadValue * precioValue;
                total_venta.setText("Total venta: $" + String.format("%.2f", totalValue));
            } catch (NumberFormatException e) {
                total_venta.setText("Total venta: N/A");
            }

        }
    }

}
