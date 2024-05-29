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
        TextView idvo, nombreOferta, totalO, precio, restantes, totalV;
        ViewHolder(View itemView) {
            super(itemView);
            idvo = itemView.findViewById(R.id.idOferta);
            nombreOferta = itemView.findViewById(R.id.nombreOferta);
            totalO = itemView.findViewById(R.id.totalO);
            precio = itemView.findViewById(R.id.precio);
            restantes = itemView.findViewById(R.id.restantes);
            totalV = itemView.findViewById(R.id.totalV);


        }
        void bindData(final ListaElementosVentasOfertas item) {
            idvo.setText("ID Oferta: " + item.getIdVo());
            nombreOferta.setText("Nombre: " + item.getNombreOferta());
            totalO.setText("Total Vendidos: " + item.getTotalO());
            precio.setText("Precio: $" + item.getPrecio());
            restantes.setText("Restantes: " + item.getRestantes());

            // Calcular el total multiplicando la cantidad por el precio
            try {
                int cantidadValue = Integer.parseInt(item.getTotalO());
                double precioValue = Double.parseDouble(item.getPrecio());
                double totalValue = cantidadValue * precioValue;
                totalV.setText("Total venta: $" + String.format("%.2f", totalValue));
            } catch (NumberFormatException e) {
                totalV.setText("Total venta: N/A");
            }

        }
    }
}
