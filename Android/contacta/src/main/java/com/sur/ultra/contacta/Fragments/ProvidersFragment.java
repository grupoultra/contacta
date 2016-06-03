package com.sur.ultra.contacta.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sur.ultra.contacta.Adapters.ProvidersAdapter;
import com.sur.ultra.contacta.R;
import com.sur.ultra.contacta.Util.DecoracionLineaDivisoria;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProvidersFragment extends Fragment {
    private RecyclerView reciclador;
    private LinearLayoutManager layoutManager;
    private ProvidersAdapter adaptador;

    public ProvidersFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.providers_fragment, container, false);

        reciclador = (RecyclerView) view.findViewById(R.id.reciclador);
        layoutManager = new LinearLayoutManager(getActivity());
        reciclador.setLayoutManager(layoutManager);

        adaptador = new ProvidersAdapter();
        reciclador.setAdapter(adaptador);
        reciclador.addItemDecoration(new DecoracionLineaDivisoria(getActivity()));

        return view;
    }

}
