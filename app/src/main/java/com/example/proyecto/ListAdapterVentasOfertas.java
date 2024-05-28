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
public class ListAdapterVentasOfertas extends RecyclerView.Adapter<ListAdapterVentasOfertas.ViewHolder>{
    private List<ListaElementosVentasOfertas> mData;
    private LayoutInflater mInflater;
    private Context context;

    public ListAdapterVentasOfertas (List<ListaElementosVentasOfertas> itemList, Context context) {

        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    @Override
    public int getItemCount() { return mData.size(); }

    @Override
    public ListAdapterVentasOfertas.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.lista_elementos_ventas_ofer, null);
        return new ListAdapterVentasOfertas.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ListAdapterVentasOfertas.ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));
    }

    public void setItems(List<ListaElementosVentasOfertas> items) { mData = items; }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconImage;
        TextView nombreOferta, usuario, cantidadUsuaio, total;
        ViewHolder(View itemView) {
            super(itemView);
            nombreOferta = itemView.findViewById(R.id.nombreOferta);
            usuario = itemView.findViewById(R.id.usuario);
            cantidadUsuaio = itemView.findViewById(R.id.cantidadOfertaPorUsuario);
            total = itemView.findViewById(R.id.total);


        }
        void bindData(final ListaElementosVentasOfertas item) {
            nombreOferta.setText(item.getNombreOferta());
            usuario.setText(item.getUsuario());
            cantidadUsuaio.setText(item.getCantidadUsuario());
            total.setText(item.getTotal());

        }
    }
}
