package com.sur.ultra.contacta.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sur.ultra.contacta.Adapters.ProvidersAdapter;
import com.sur.ultra.contacta.Models.Provider;
import com.sur.ultra.contacta.R;
import com.sur.ultra.contacta.Util.DecoracionLineaDivisoria;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProvidersFragment extends Fragment {

    OnProviderSelectedListener mCallback;

    // Container Activity must implement this interface
    public interface OnProviderSelectedListener {
        void onProviderSelected(int position);
    }

    public ProvidersFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container context has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnProviderSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnProviderSelectedListener");
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        View view = inflater.inflate(R.layout.fragments_recycler_view, container, false);

        RecyclerView reciclador = (RecyclerView) view.findViewById(R.id.reciclador);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        reciclador.setLayoutManager(layoutManager);

        ProvidersAdapter adaptador = new ProvidersAdapter(Provider.PROVEEDORES, getActivity(), mCallback);
        reciclador.setAdapter(adaptador);
        reciclador.addItemDecoration(new DecoracionLineaDivisoria(getActivity()));

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.no_filter_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

}
