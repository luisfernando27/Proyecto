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
        TextView nombreProducto, usuario, cantidadUsuaio, total;
        ViewHolder(View itemView) {
            super(itemView);
            nombreProducto = itemView.findViewById(R.id.nombreProducto);
            usuario = itemView.findViewById(R.id.usuario);
            cantidadUsuaio = itemView.findViewById(R.id.cantidadProdPorUsuario);
            total = itemView.findViewById(R.id.total);


        }
        void bindData(final ListaElementosVentasProductos item) {
            nombreProducto.setText(item.getNombreProducto());
            usuario.setText(item.getUsuario());
            cantidadUsuaio.setText(item.getCantidadUsuario());
            total.setText(item.getTotal());

        }
    }

}
