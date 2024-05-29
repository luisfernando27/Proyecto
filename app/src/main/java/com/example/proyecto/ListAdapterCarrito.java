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
    private Context mContext;

    private String userEmail;

    public ListAdapterCarrito (List<ListaElementosCarrito> itemList, Context context, String userEmail) {
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mData = itemList;
        this.userEmail = userEmail;
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
        TextView nombreProducto, cantidad, precio, total;
        ViewHolder(View itemView) {
            super(itemView);
            nombreProducto= itemView.findViewById(R.id.nombreProducto);
            cantidad = itemView.findViewById(R.id.cantidad);
            precio = itemView.findViewById(R.id.precio);
            total = itemView.findViewById(R.id.total);

        }
        void bindData(final ListaElementosCarrito item) {
            nombreProducto.setText(item.getNombreProducto());
            cantidad.setText(item.getCantidad());
            precio.setText(item.getPrecio());


        }
    }
}
