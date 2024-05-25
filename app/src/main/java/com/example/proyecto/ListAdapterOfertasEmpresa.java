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
        View view = mInflater.inflate(R.layout.lista_ofertas_registradas, null);
        return new ListAdapterOfertasEmpresa.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListAdapterOfertasEmpresa.ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));
    }
    public void setItems(List<ListaElementosOfertasEmpresa> items) { mData = items; }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconImage;
        TextView nombreOferta, descripcion, precio, totalOferta;
        ViewHolder(View itemView) {
            super(itemView);
            iconImage = itemView.findViewById(R.id.imagenOfertaUs);
            nombreOferta = itemView.findViewById(R.id.nombreOferta);
            descripcion = itemView.findViewById(R.id.descripcionOferta);
            precio = itemView.findViewById(R.id.lblPrecio);
            totalOferta = itemView.findViewById(R.id.totalOfertas);

        }
        void bindData(final ListaElementosOfertasEmpresa item) {
            nombreOferta.setText(item.getNombreOferta());
            descripcion.setText(item.getDescripcionOferta());
            precio.setText(item.getPrecioOferta());
            totalOferta.setText(item.getNumTotalOferta());

        }
    }
}
