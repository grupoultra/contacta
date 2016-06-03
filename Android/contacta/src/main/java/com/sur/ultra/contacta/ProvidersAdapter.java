package com.sur.ultra.contacta;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sur.ultra.contacta.Models.Provider;

/**
 * Created by alexis on 6/2/16.
 */
public class ProvidersAdapter
        extends RecyclerView.Adapter<ProvidersAdapter.ViewHolder> {


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView name;
        public TextView info;

        public ViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.nombre_comida);
        }
    }

    public ProvidersAdapter() {
    }

    @Override
    public int getItemCount() {
        return Provider.PROVEEDORES.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_provider_list, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Provider item = Provider.PROVEEDORES.get(i);

        viewHolder.name.setText(item.getName());
    }


}
