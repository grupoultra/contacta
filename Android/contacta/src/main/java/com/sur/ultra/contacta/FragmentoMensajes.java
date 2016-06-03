package com.sur.ultra.contacta;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sur.ultra.contacta.Models.Message;

/**
 * Created by alexis on 6/2/16.
 */
public class FragmentoMensajes extends android.support.v4.app.Fragment {

    /**/

//    private static final String INDICE_SECCION
//            = "com.sur.ultra.contacta.InboxFragment.extra.INDICE_SECCION";
//
//    private RecyclerView reciclador;
//    private GridLayoutManager layoutManager;
//    private MessageAdapter adaptador;
//
//    public static InboxFragment nuevaInstancia(int indiceSeccion) {
//        InboxFragment fragment = new InboxFragment();
//        Bundle args = new Bundle();
//        args.putInt(INDICE_SECCION, indiceSeccion);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragments_recycler_view, container, false);
//
//        reciclador = (RecyclerView) view.findViewById(R.id.reciclador);
//        layoutManager = new GridLayoutManager(getActivity(), 2);
//        reciclador.setLayoutManager(layoutManager);
//
//        int indiceSeccion = getArguments().getInt(INDICE_SECCION);
//
//        switch (indiceSeccion) {
//            case 0:
//                adaptador = new MessageAdapter(Message.NEWS);
//                break;
//            case 1:
//                adaptador = new MessageAdapter(Message.NEWS);
//                break;
//        }
//
//        reciclador.setAdapter(adaptador);
//
//        return view;
//    }
    /**/


    private LinearLayoutManager linearLayout;

    public FragmentoMensajes() {

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

        MessageAdapter adaptador = new MessageAdapter(Message.MESSAGES);
        reciclador.setAdapter(adaptador);
        reciclador.addItemDecoration(new DecoracionLineaDivisoria(getActivity()));

        return view;
    }


}