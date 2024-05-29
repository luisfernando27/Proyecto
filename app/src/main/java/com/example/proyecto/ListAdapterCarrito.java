package com.example.proyecto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapterCarrito extends RecyclerView.Adapter<ListAdapterCarrito.ViewHolder> {
    private List<ListaElementosCarrito> mData;
    private LayoutInflater mInflater;
    private Context mContext;

    private String userEmail;

    public ListAdapterCarrito(List<ListaElementosCarrito> itemList, Context context, String userEmail) {
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mData = itemList;
        this.userEmail = userEmail;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public ListAdapterCarrito.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.lista_elementos_carrito, parent, false);
        return new ListAdapterCarrito.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListAdapterCarrito.ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));
    }

    public void setItems(List<ListaElementosCarrito> items) {
        mData = items;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombreProducto, cantidad, precio, total;

        ViewHolder(View itemView) {
            super(itemView);
            nombreProducto = itemView.findViewById(R.id.nombreProducto);
            cantidad = itemView.findViewById(R.id.cantidad);
            precio = itemView.findViewById(R.id.precio);
            total = itemView.findViewById(R.id.total);
        }

        void bindData(final ListaElementosCarrito item) {
            nombreProducto.setText("Nombre del producto: " + item.getNombreProducto());
            cantidad.setText("Cantidad: " + item.getCantidad());
            precio.setText("Precio: " + item.getPrecio());

            // Calcular el total multiplicando la cantidad por el precio
            try {
                int cantidadValue = Integer.parseInt(item.getCantidad());
                double precioValue = Double.parseDouble(item.getPrecio());
                double totalValue = cantidadValue * precioValue;
                total.setText("Total: $" + String.format("%.2f", totalValue));
            } catch (NumberFormatException e) {
                total.setText("Total: N/A");
            }
        }
    }
}
