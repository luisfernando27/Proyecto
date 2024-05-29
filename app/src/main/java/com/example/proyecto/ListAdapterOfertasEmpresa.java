package com.example.proyecto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapterOfertasEmpresa extends RecyclerView.Adapter<ListAdapterOfertasEmpresa.ViewHolder> {
    private List<ListaElementosOfertasEmpresa> mData;
    private LayoutInflater mInflater;
    private Context context;

    public ListAdapterOfertasEmpresa(List<ListaElementosOfertasEmpresa> itemList, Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public ListAdapterOfertasEmpresa.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.lista_elementos_ver_ofertas, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListAdapterOfertasEmpresa.ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));
    }

    public void setItems(List<ListaElementosOfertasEmpresa> items) {
        mData = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView idO, nombreOferta, cantidadMaxima, totalOfertas, precio, fechaInicio, fechaFin;

        ViewHolder(View itemView) {
            super(itemView);
            idO = itemView.findViewById(R.id.idOferta);
            nombreOferta = itemView.findViewById(R.id.nombreOferta);
            cantidadMaxima = itemView.findViewById(R.id.cantidadmaxima);
            totalOfertas = itemView.findViewById(R.id.cantidadtotal);
            precio = itemView.findViewById(R.id.precio);
            fechaInicio = itemView.findViewById(R.id.fechaI);
            fechaFin = itemView.findViewById(R.id.fechaF);
        }

        void bindData(final ListaElementosOfertasEmpresa item) {
            idO.setText("ID oferta: " + item.getIDO());
            nombreOferta.setText("Nombre de la oferta: " + item.getNombreOferta());
            cantidadMaxima.setText("Cantidad máxima por usuario: " + item.getMaximaCantidad());
            totalOfertas.setText("Total de ofertas: " + item.getTotalOfertas());
            precio.setText("Precio: " + item.getPrecioOferta());
            fechaInicio.setText("Fecha de inicio: " + item.getFechaInicio());
            fechaFin.setText("Fecha de fin: " + item.getFechaFin());
        }
    }
}
