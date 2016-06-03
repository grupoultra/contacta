package com.sur.ultra.contacta.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sur.ultra.contacta.Models.Message;
import com.sur.ultra.contacta.R;

import java.util.List;

/**
 * Created by alexis on 6/2/16.
 */
public class MessageAdapter
        extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private final List<Message> items;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView messageSummary;
        public TextView author;

        public ViewHolder(View v) {
            super(v);
            messageSummary = (TextView) v.findViewById(R.id.text_messageSummary);
            author = (TextView) v.findViewById(R.id.text_author);
        }
    }

    public MessageAdapter(List<Message> items) { this.items = items; }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_message_list, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Message item = items.get(i);
        viewHolder.messageSummary.setText(item.messageSummary);
        viewHolder.author.setText(item.author);
    }

}


