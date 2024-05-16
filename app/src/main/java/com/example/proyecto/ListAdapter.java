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

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<ListaElementosClientes> mData;
    private LayoutInflater mInflater;
    private Context context;
    public ListAdapter(List<ListaElementosClientes> itemList, Context context) {

        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }
    @Override
    public int getItemCount() { return mData.size(); }
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.lista_elementos_ofertas, null);
        return new ListAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final ListAdapter.ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));
    }
    public void setItems(List<ListaElementosClientes> items) { mData = items; }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconImage;
        TextView nombreOferta, nombreEmpresa, descripcion, precio;
        ViewHolder(View itemView) {
            super(itemView);
            iconImage = itemView.findViewById(R.id.imagenOfertaUs);
            nombreOferta = itemView.findViewById(R.id.nombreOferta);
            nombreEmpresa = itemView.findViewById(R.id.nombreEmpresa);
            descripcion = itemView.findViewById(R.id.descripcionOferta);
            precio = itemView.findViewById(R.id.lblPrecio);

        }
        void bindData(final ListaElementosClientes item) {
            nombreOferta.setText(item.getNombreOferta());
            nombreEmpresa.setText(item.getNombreEmpresa());
            descripcion.setText(item.getDescripcion());
            precio.setText(item.getPrecio());

        }
    }



}
