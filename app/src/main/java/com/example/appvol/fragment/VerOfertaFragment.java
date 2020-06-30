package com.example.appvol.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.appvol.R;
import com.example.appvol.model.Oferta;

public class VerOfertaFragment extends Fragment {

    TextView tvPrueba;
    private Oferta oferta;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ver_oferta, container, false);
        tvPrueba = view.findViewById(R.id.tvPrueba);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            VerOfertaFragmentArgs args = VerOfertaFragmentArgs.fromBundle(getArguments());
            oferta = args.getOferta();
            tvPrueba.setText(oferta.getFecha());
        }
    }
}