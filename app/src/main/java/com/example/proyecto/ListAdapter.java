package com.example.proyecto;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<ListaElementosClientes> mData;
    private LayoutInflater mInflater;

    public ListAdapter(List<ListaElementosClientes> itemList, Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = itemList;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.lista_elementos_ofertas, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(mData.get(position));
    }

    public void setItems(List<ListaElementosClientes> items) {
        mData = items;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombreOferta, empresa, cantidadOfertaPorUsuario, precio;

        ViewHolder(View itemView) {
            super(itemView);
            nombreOferta = itemView.findViewById(R.id.nombreOferta);
            empresa = itemView.findViewById(R.id.empresa);
            cantidadOfertaPorUsuario = itemView.findViewById(R.id.cantidadOfertaPorUsuario);
            precio = itemView.findViewById(R.id.precio);
        }

        void bindData(final ListaElementosClientes item) {
            nombreOferta.setText(item.getNombreOferta());
            empresa.setText(item.getEmpresa());
            cantidadOfertaPorUsuario.setText(item.getCantidadOfertaPorUsuario());
            precio.setText(item.getPrecio());
        }
    }
}
