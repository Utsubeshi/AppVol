package com.example.appvol.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appvol.R;
import com.example.appvol.model.Voluntario;

import java.util.ArrayList;

public class PostulantesAdapter extends RecyclerView.Adapter<PostulantesAdapter.ViewHolder> {

    Context context;
    ArrayList<Voluntario> list;

    public PostulantesAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<Voluntario>();
    }

    public void agregarDatos(ArrayList<Voluntario> data){
        list.clear();
        list.addAll(data);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public PostulantesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_postulante, parent, false);
        return new ViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull PostulantesAdapter.ViewHolder holder, int position) {
        Voluntario voluntario = list.get(position);
        holder.tvNombres.setText(voluntario.getNombres());
        holder.tvExperiencia.setText(voluntario.getExperiencia());
        holder.tvIntereses.setText(voluntario.getIntereses());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNombres, tvIntereses, tvExperiencia;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombres = itemView.findViewById(R.id.tv_postulante_nombre);
            tvIntereses = itemView.findViewById(R.id.tv_postulante_intereses);
            tvExperiencia = itemView.findViewById(R.id.tv_postulante_experiencia);
        }
    }
}
