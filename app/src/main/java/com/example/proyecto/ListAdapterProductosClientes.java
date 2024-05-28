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

public class ListAdapterProductosClientes extends RecyclerView.Adapter<ListAdapterProductosClientes.ViewHolder>{
    private List<ListaElementosProductosClientes> mData;
    private LayoutInflater mInflater;
    private Context context;

    public ListAdapterProductosClientes (List<ListaElementosProductosClientes> itemList, Context context) {

        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    @Override
    public int getItemCount() { return mData.size(); }

    @Override
    public ListAdapterProductosClientes.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.lista_elementos_prod_clientes, null);
        return new ListAdapterProductosClientes.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final ListAdapterProductosClientes.ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));
    }

    public void setItems(List<ListaElementosProductosClientes> items) { mData = items; }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconImage;
        TextView nombreProducto, empresa, precio;
        ViewHolder(View itemView) {
            super(itemView);
            nombreProducto = itemView.findViewById(R.id.nombreProdCLient);
            empresa = itemView.findViewById(R.id.empresa);
            precio = itemView.findViewById(R.id.precio);


        }
        void bindData(final ListaElementosProductosClientes item) {
            nombreProducto.setText(item.getNombreProducto());
            empresa.setText(item.getEmpresa());
            precio.setText(item.getPrecio());

        }
    }
}
