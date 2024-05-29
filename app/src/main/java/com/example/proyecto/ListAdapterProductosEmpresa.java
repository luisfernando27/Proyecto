package com.example.proyecto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapterProductosEmpresa extends RecyclerView.Adapter<ListAdapterProductosEmpresa.ViewHolder> {
    private List<ListaElementosProductosEmpresa> mData;
    private LayoutInflater mInflater;
    private Context context;

    public ListAdapterProductosEmpresa(List<ListaElementosProductosEmpresa> itemList, Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public ListAdapterProductosEmpresa.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.lista_elementos_ver_prod, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListAdapterProductosEmpresa.ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));
    }

    public void setItems(List<ListaElementosProductosEmpresa> items) {
        mData = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView idProducto, nombreProducto, cantidad, precio, totalV1;

        ViewHolder(View itemView) {
            super(itemView);
            idProducto = itemView.findViewById(R.id.idProducto);
            nombreProducto = itemView.findViewById(R.id.nombreProducto);
            cantidad = itemView.findViewById(R.id.cantidad);
            precio = itemView.findViewById(R.id.precio);
            totalV1 = itemView.findViewById(R.id.totalV1);
        }

        void bindData(final ListaElementosProductosEmpresa item) {
            idProducto.setText("ID Producto: " + item.getIdP());
            nombreProducto.setText("Nombre Producto: " + item.getNombreProducto());
            cantidad.setText("Cantidad: " + item.getCodigoProducto());
            precio.setText("Precio: $" + item.getPrecioProducto());

            float cantidad = Float.parseFloat(item.getCodigoProducto());
            float precio = Float.parseFloat(item.getPrecioProducto());

            // Calcular el total de la venta
            float totalVenta = cantidad * precio;

            // Mostrar el total de la venta en el TextView correspondiente
            totalV1.setText("Total Venta: $" + totalVenta);
        }
    }
}
