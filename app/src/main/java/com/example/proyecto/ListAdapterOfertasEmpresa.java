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
public class ListAdapterOfertasEmpresa extends RecyclerView.Adapter<ListAdapterOfertasEmpresa.ViewHolder>{
    private List<ListaElementosOfertasEmpresa> mData;
    private LayoutInflater mInflater;
    private Context context;
    public ListAdapterOfertasEmpresa (List<ListaElementosOfertasEmpresa> itemList, Context context) {

        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    @Override
    public int getItemCount() { return mData.size(); }

    @Override
    public ListAdapterOfertasEmpresa.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.lista_elementos_ver_ofertas, null);
        return new ListAdapterOfertasEmpresa.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListAdapterOfertasEmpresa.ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));
    }
    public void setItems(List<ListaElementosOfertasEmpresa> items) { mData = items; }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombreOferta, cantidadMaxima, totalOfertas, precio, fechaInicio, fechaFin;
        ViewHolder(View itemView) {
            super(itemView);
            nombreOferta = itemView.findViewById(R.id.nombreOferta);
            cantidadMaxima = itemView.findViewById(R.id.cantidadOfertaPorUsuario);
            totalOfertas = itemView.findViewById(R.id.cantidadTotalOfertas);
            precio = itemView.findViewById(R.id.precio);
            fechaInicio = itemView.findViewById(R.id.fechaI);
            fechaFin = itemView.findViewById(R.id.fechaF);

        }
        void bindData(final ListaElementosOfertasEmpresa item) {
            nombreOferta.setText(item.getNombreOferta());
            cantidadMaxima.setText(item.getCantidadMaxima());
            totalOfertas.setText(item.getTotalOfertas());
            precio.setText(item.getPrecio());
            fechaInicio.setText(item.getFechaInicio());
            fechaFin.setText(item.getFechaFin());
        }
    }
}
