package com.sur.ultra.contacta.Fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sur.ultra.contacta.Adapters.MessageAdapter;
import com.sur.ultra.contacta.Models.Message;
import com.sur.ultra.contacta.R;
import com.sur.ultra.contacta.Util.DecoracionLineaDivisoria;

/**
 * Created by alexis on 6/2/16.
 */
public class NewsFragment extends android.support.v4.app.Fragment {

    private LinearLayoutManager linearLayout;

    public NewsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragments_recycler_view, container, false);

        RecyclerView reciclador = (RecyclerView)view.findViewById(R.id.reciclador);
        linearLayout = new LinearLayoutManager(getActivity());
        reciclador.setLayoutManager(linearLayout);

        MessageAdapter adaptador = new MessageAdapter(Message.getNEWS(), getActivity(), null);
        reciclador.setAdapter(adaptador);
        reciclador.addItemDecoration(new DecoracionLineaDivisoria(getActivity()));

        return view;
    }


}