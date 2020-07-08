package com.example.appvol.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appvol.R;
import com.example.appvol.fragment.OfertasFragmentDirections;
import com.example.appvol.model.Oferta;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static androidx.navigation.Navigation.findNavController;

public class OfertasAdapter extends RecyclerView.Adapter<OfertasAdapter.ViewHolder> {

    Context context;
    ArrayList<Oferta> lista;

    public OfertasAdapter(Context context) {
        this.context = context;
        this.lista = new ArrayList<>();
    }

    @NonNull
    @Override
    public OfertasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_oferta, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OfertasAdapter.ViewHolder holder, int position) {
        final Oferta oferta = lista.get(position);
        holder.tvTitulo.setText(oferta.getTitulo());
        holder.tvTipo.setText(oferta.getTipo());
        holder.tvFecha.setText(oferta.getFecha());
        holder.tvOrganizacion.setText(oferta.getOrganizacion());
        String estado = "no disponible";
        if (oferta.isEstado()){
            estado = "activo";
        }
        holder.tvEstado.setText(estado);
        holder.contenedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OfertasFragmentDirections.ActionNavOfertasFragToNavVerOferta action = OfertasFragmentDirections.actionNavOfertasFragToNavVerOferta(oferta);
                findNavController(v).navigate(action);
            }
        });
        Picasso.get().load(oferta.getUrlImagen()).into(holder.ivOferta);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public void agregarOfertas(ArrayList<Oferta> listaOfertas) {
        lista.clear();
        lista.addAll(listaOfertas);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivOferta;
        TextView tvTitulo, tvFecha, tvTipo, tvEstado, tvOrganizacion ;
        ConstraintLayout contenedor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivOferta = itemView.findViewById(R.id.iv_listado_imagen_oferta);
            tvTitulo = itemView.findViewById(R.id.tv_listado_titulo_oferta);
            tvFecha = itemView.findViewById(R.id.tv_listado_fecha_oferta);
            tvTipo = itemView.findViewById(R.id.tv_listado_ubicación_oferta);
            tvEstado = itemView.findViewById(R.id.tv_listado_estado_oferta);
            tvOrganizacion = itemView.findViewById(R.id.tv_listado_organizacion);
            contenedor = itemView.findViewById(R.id.contenedor_oferta);

        }
    }
}
