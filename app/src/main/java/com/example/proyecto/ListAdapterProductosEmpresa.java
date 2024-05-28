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
public class ListAdapterProductosEmpresa extends RecyclerView.Adapter<ListAdapterProductosEmpresa.ViewHolder>{
    private List<ListaElementosProductosEmpresa> mData;
    private LayoutInflater mInflater;
    private Context context;
    public ListAdapterProductosEmpresa (List<ListaElementosProductosEmpresa> itemList, Context context) {

        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    @Override
    public int getItemCount() { return mData.size(); }

    @Override
    public ListAdapterProductosEmpresa.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.lista_elementos_ver_prod, null);
        return new ListAdapterProductosEmpresa.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListAdapterProductosEmpresa.ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));
    }

    public void setItems(List<ListaElementosProductosEmpresa> items) { mData = items; }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconImage;
        TextView nombreProducto, codigoProducto, precioProducto;
        ViewHolder(View itemView) {
            super(itemView);
            nombreProducto = itemView.findViewById(R.id.nombreProducto);
            codigoProducto = itemView.findViewById(R.id.codigoProducto);
            precioProducto = itemView.findViewById(R.id.precio);

        }
        void bindData(final ListaElementosProductosEmpresa item) {
            nombreProducto.setText(item.getNombreProducto());
            codigoProducto.setText(item.getCodigoProducto());
            precioProducto.setText(item.getPrecioProducto());

        }
    }
}
