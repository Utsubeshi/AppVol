package com.example.appvol.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appvol.R;
import com.example.appvol.model.Oferta;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PostulacionesAdapter extends RecyclerView.Adapter<PostulacionesAdapter.ViewHolder> {

    Context context;
    ArrayList<Oferta> listOfertas;

    public PostulacionesAdapter(Context context) {
        this.context = context;
        this.listOfertas = new ArrayList<Oferta>();
    }

    public void agregarDatos(ArrayList<Oferta> data){
        listOfertas.clear();
        listOfertas.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_postulacion, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Oferta oferta = listOfertas.get(position);
        holder.tvOferta.setText(oferta.getTitulo());
        holder.tvOrganizacion.setText(oferta.getOrganizacion());
        holder.tvFecha.setText(oferta.getFecha());
        Picasso.get().load(oferta.getUrlImagen()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return listOfertas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvOferta, tvOrganizacion, tvFecha;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFecha = itemView.findViewById(R.id.tvPostulacionFecha);
            tvOferta = itemView.findViewById(R.id.tvPostulacionOferta);
            tvOrganizacion = itemView.findViewById(R.id.tvPostulacionOrganizacion);
            imageView = itemView.findViewById(R.id.ivPostulacionImagen);
        }
    }
}
