package com.itca.crud_finalproyect;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter {
    private Context mCtx;
    private List<dto_catprod> productList;

    //Uno
    private OnItemClickListener mListener;

    //Dos
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    //Tres
    public void setOnItemClickListener(Context mCtx, OnItemClickListener listener) {
        this.mCtx = mCtx;
        mListener = listener;
    }


    public ProductAdapter(Context mCtx, List<dto_catprod> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.rv_consulta1, null);
        return new ProductViewHolder (view);*/

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_rv_cons, parent, false);
        ProductViewHolder evh = new ProductViewHolder(v, mListener);
        return evh;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder holder, int position) {
        dto_catprod datos = productList.get(position);

        holder.textViewNombre1.setText(datos.getId_producto() + ":" + datos.getNombre_producto());
        holder.textViewDescripcion1.setText(datos.getDes_producto());
        holder.textViewPrecio1.setText(String.valueOf(datos.getPrecio()));
        holder.textViewStock1.setText(String.valueOf(datos.getStock()));
        holder.textViewCategoria1.setText(String.valueOf(datos.getCategoria()) + ":" + datos.getNombre_categoria());
        holder.textViewFecha.setText(datos.getFecha());
        holder.textViewTitleProducto.setText(datos.getNombre_producto());

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


    public class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNombre1, textViewDescripcion1, textViewPrecio1, textViewStock1, textViewCategoria1, textViewFecha, textViewTitleProducto;


        public ProductViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            textViewNombre1 = itemView.findViewById(R.id.textViewNombre1);
            textViewDescripcion1 = itemView.findViewById(R.id.textViewDescripcion1);
            textViewPrecio1 = itemView.findViewById(R.id.textViewPrecio1);
            textViewStock1 = itemView.findViewById(R.id.textViewStock1);
            textViewCategoria1 = itemView.findViewById(R.id.textViewCategoria1);
            textViewFecha = itemView.findViewById(R.id.textViewFecha);
            textViewTitleProducto = itemView.findViewById(R.id.textViewTitleProducto);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }

                        /*Esto lo he puesto para verificar desde la pestaña Logcat que efectivamente pase por este método al dar click sobre un item  del recyclerview*/
                        //Toast.makeText(mCtx, "Hola", Toast.LENGTH_SHORT).show();
                        System.out.println("" + position);
                        //Muestro datos en LogCat para verificar la respuesta obtenida desde el servidor.
                        Log.i("Itca", "ITCA-FEPADE");

                    }
                }
            });

        }
    }



    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
