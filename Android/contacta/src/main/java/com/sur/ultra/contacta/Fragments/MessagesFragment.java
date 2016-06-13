package com.sur.ultra.contacta.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
public class MessagesFragment extends android.support.v4.app.Fragment {

    private static final String MESSAGE_TYPE = "";
    OnMessageSelectedListener mCallback;

    // Container Activity must implement this interface
    public interface OnMessageSelectedListener {
        void onMessageSelected(int position, String type);
    }

    public MessagesFragment() {

    }

    public static MessagesFragment newInstance(int type) {
        MessagesFragment fragment = new MessagesFragment();
        Bundle args = new Bundle();
        args.putInt(MESSAGE_TYPE, type);

//        args.putString(MESSAGE_TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }


    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnMessageSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnMessageSelectedListener");
        }
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
        LinearLayoutManager linearLayout = new LinearLayoutManager(getActivity());
        reciclador.setLayoutManager(linearLayout);

        MessageAdapter adaptador;
        int messageType = getArguments().getInt(MESSAGE_TYPE);

        if(messageType == 0){
            adaptador = new MessageAdapter(Message.getNEWS(), getActivity(), mCallback);
        } else{
            adaptador = new MessageAdapter(Message.getMESSAGES(), getActivity(), mCallback);
        }
        reciclador.setAdapter(adaptador);
        reciclador.addItemDecoration(new DecoracionLineaDivisoria(getActivity()));

        return view;
    }


}