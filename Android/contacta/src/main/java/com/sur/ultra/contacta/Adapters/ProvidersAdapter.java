package com.sur.ultra.contacta.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
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
        public ImageView avatar;
        public TextView info;
        private List<Provider> providers = new ArrayList<Provider>();
        private Context ctx;
        private ProvidersFragment.OnProviderSelectedListener mCallback;

        public ProgressBar progressBar;

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
            avatar = (ImageView) v.findViewById(R.id.avatar);
            progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
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
        return providers.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_provider_list, viewGroup, false);
        return new ViewHolder(v,this.ctx, this.providers, mCallback );
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        Provider item = providers.get(position);

        viewHolder.name.setText(item.name);

        ImageLoadingListener imageLoadingListener = new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                viewHolder.progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                viewHolder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                viewHolder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                viewHolder.progressBar.setVisibility(View.GONE);
            }
        };


        // Then later, when you want to display image
        ImageLoader.getInstance().displayImage(providers.get(position).getAvatar(), viewHolder.avatar, imageLoadingListener);
    }
}
