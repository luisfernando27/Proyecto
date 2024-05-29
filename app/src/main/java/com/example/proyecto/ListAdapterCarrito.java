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
public class ListAdapterCarrito extends RecyclerView.Adapter<ListAdapterCarrito.ViewHolder>{
    private List<ListaElementosCarrito> mData;
    private LayoutInflater mInflater;
    private Context context;

    public ListAdapterCarrito (List<ListaElementosCarrito> itemList, Context context) {

        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    @Override
    public int getItemCount() { return mData.size(); }

    @Override
    public ListAdapterCarrito.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.lista_elementos_carrito, null);
        return new ListAdapterCarrito.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListAdapterCarrito.ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));
    }

    public void setItems(List<ListaElementosCarrito> items) { mData = items; }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconImage;
        TextView nombreVenta, empresa, cantidadUsuaio, total;
        ViewHolder(View itemView) {
            super(itemView);
            nombreVenta= itemView.findViewById(R.id.nombreVenta);
            empresa = itemView.findViewById(R.id.empresa);
            cantidadUsuaio = itemView.findViewById(R.id.cantidadProdPorUsuario);
            total = itemView.findViewById(R.id.total);


        }
        void bindData(final ListaElementosCarrito item) {
            nombreVenta.setText(item.getNombreVenta());
            empresa.setText(item.getEmpresa());
            cantidadUsuaio.setText(item.getCantidad());
            total.setText(item.getTotal());

        }
    }
}
