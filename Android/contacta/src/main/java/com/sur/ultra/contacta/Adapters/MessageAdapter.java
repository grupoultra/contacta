package com.sur.ultra.contacta.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sur.ultra.contacta.Fragments.MessagesFragment;
import com.sur.ultra.contacta.Models.Message;
import com.sur.ultra.contacta.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexis on 6/2/16.
 */
public class MessageAdapter
        extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private final List<Message> messages;
    private Context ctx;
    private MessagesFragment.OnHeadlineSelectedListener mCallback;

    public MessageAdapter(List<Message> messages, Context ctx, MessagesFragment.OnHeadlineSelectedListener mCallback) {
        this.messages = messages;
        this.ctx = ctx;
        this.mCallback = mCallback;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // Campos respectivos de un item
        public TextView messageSummary;
        public TextView author;
        private List<Message> messages = new ArrayList<Message>();
        private Context ctx;
        private MessagesFragment.OnHeadlineSelectedListener mCallback;


        public ViewHolder(View v, Context ctx, List<Message> messages, MessagesFragment.OnHeadlineSelectedListener mCallback) {
            super(v);
            this.messages = messages;
            this.ctx = ctx;
            this.mCallback = mCallback;

            v.setOnClickListener(this);

            messageSummary = (TextView) v.findViewById(R.id.text_messageSummary);
            author = (TextView) v.findViewById(R.id.text_author);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            mCallback.onArticleSelected(3);
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_message_list, viewGroup, false);
        return new ViewHolder(v,this.ctx, this.messages, mCallback );
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Message item = messages.get(i);
        viewHolder.messageSummary.setText(item.messageSummary);
        viewHolder.author.setText(item.author);
    }

}


