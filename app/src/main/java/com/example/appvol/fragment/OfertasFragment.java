package com.example.appvol.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appvol.R;
import com.example.appvol.adapter.OfertasAdapter;
import com.example.appvol.model.Oferta;

import java.util.ArrayList;

public class OfertasFragment extends Fragment {

    RecyclerView rvOfertas;
    ArrayList<Oferta> listaOfertas;
    private OfertasAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ofertas, container, false);
        rvOfertas = view.findViewById(R.id.rv_listado_ofertas);
        rvOfertas.setLayoutManager(new LinearLayoutManager(getContext()));
        listaOfertas = new ArrayList<>();
        listaOfertas.add(new Oferta("1","asdf","10/01/21","asdf", "nose","https://cdn.pixabay.com/photo/2020/03/06/11/14/black-4906807_1280.jpg", true));
        listaOfertas.add(new Oferta("1","asdf","10/01/22","asdf", "nose","https://cdn.pixabay.com/photo/2020/03/06/11/14/black-4906807_1280.jpg", true));
        listaOfertas.add(new Oferta("1","asdf","10/01/23","asdf", "nose","https://cdn.pixabay.com/photo/2020/03/06/11/14/black-4906807_1280.jpg", true));


        adapter = new OfertasAdapter(getContext());
        adapter.agregarOfertas(listaOfertas);
        rvOfertas.setAdapter(adapter);

        return view;

    }
}