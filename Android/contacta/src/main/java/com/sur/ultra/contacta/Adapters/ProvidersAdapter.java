package com.sur.ultra.contacta.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sur.ultra.contacta.Fragments.ProvidersFragment;
import com.sur.ultra.contacta.Models.Provider;
import com.sur.ultra.contacta.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexis on 6/2/16.
 */
public class ProvidersAdapter
        extends RecyclerView.Adapter<ProvidersAdapter.ViewHolder> {

    private final List<Provider> providers;
    private Context ctx;
    private ProvidersFragment.OnProviderSelectedListener mCallback;

    public ProvidersAdapter(List<Provider> providers, Context ctx, ProvidersFragment.OnProviderSelectedListener mCallback) {
        this.providers = providers;
        this.ctx = ctx;
        this.mCallback = mCallback;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // Campos respectivos de un item
        public TextView name;
        public TextView info;
        private List<Provider> providers = new ArrayList<Provider>();
        private Context ctx;
        private ProvidersFragment.OnProviderSelectedListener mCallback;
        Button disconnectButton;

        public ViewHolder(View v, Context ctx, List<Provider> providers, ProvidersFragment.OnProviderSelectedListener mCallback) {
            super(v);
            this.providers = providers;
            this.ctx = ctx;
            this.mCallback = mCallback;

            disconnectButton = (Button) v.findViewById(R.id.disconnectButton);

            disconnectButton.setOnClickListener(this);
            v.setOnClickListener(this);

            name = (TextView) v.findViewById(R.id.providers_name);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

            if (v.getId() == disconnectButton.getId()){
                Toast.makeText(v.getContext(), "Desconectar = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
            } else {
                mCallback.onProviderSelected(providers.get(position).id);
            }
        }
    }

    @Override
    public int getItemCount() {
        return Provider.PROVIDERS.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_provider_list, viewGroup, false);
        return new ViewHolder(v,this.ctx, this.providers, mCallback );
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Provider item = providers.get(i);

        viewHolder.name.setText(item.name);
    }
}
