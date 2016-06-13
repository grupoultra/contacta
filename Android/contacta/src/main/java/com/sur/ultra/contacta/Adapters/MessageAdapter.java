package com.sur.ultra.contacta.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

    private static final String TAG = "MessageAdapter";
    private final List<Message> messages;
    private Context ctx;
    private MessagesFragment.OnMessageSelectedListener mCallback;

    public MessageAdapter(List<Message> messages, Context ctx, MessagesFragment.OnMessageSelectedListener mCallback) {
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
        private MessagesFragment.OnMessageSelectedListener mCallback;

        Button dismissButton;

        public ViewHolder(View v, Context ctx, List<Message> messages, MessagesFragment.OnMessageSelectedListener mCallback) {
            super(v);
            this.messages = messages;
            this.ctx = ctx;
            this.mCallback = mCallback;

            dismissButton = (Button) v.findViewById(R.id.dismissButton);

            dismissButton.setOnClickListener(this);
            v.setOnClickListener(this);

            messageSummary = (TextView) v.findViewById(R.id.text_messageSummary);
            author = (TextView) v.findViewById(R.id.text_author);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

            if (v.getId() == dismissButton.getId()){
                Toast.makeText(v.getContext(), "Marcar como leida = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
            } else {
                mCallback.onMessageSelected(messages.get(position).id, messages.get(position).type);
            }
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
        viewHolder.messageSummary.setText(item.title);
        viewHolder.author.setText(item.author);
    }

}


