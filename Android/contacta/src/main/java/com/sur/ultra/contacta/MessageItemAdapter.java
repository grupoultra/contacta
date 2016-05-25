package com.sur.ultra.contacta;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by alexis on 5/24/16.
 */
public class MessageItemAdapter extends ArrayAdapter<MessageListItem> {

    Context mContext;
    int mLayoutResourceId;
    MessageListItem mData[] = null;

    public MessageItemAdapter(Context context, int resource, MessageListItem[] data) {
        super(context, resource, data);
        this.mContext = context;
        this.mLayoutResourceId = resource;
        this.mData = data;
    }

    @Override
    public MessageListItem getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        //inflate the layout for a single row
        LayoutInflater inflater = LayoutInflater.from(mContext);
        row = inflater.inflate(mLayoutResourceId,parent,false);

        //get a reference to the different view elements we wish to update
        TextView nameView = (TextView) row.findViewById(R.id.nameTextView);
        TextView zipView = (TextView) row.findViewById(R.id.zipcodeTextView);
        ImageView imageView = (ImageView) row.findViewById(R.id.imageView);

        //get the data from the data array
        MessageListItem message = mData[position];

        //setting the view to reflect the data we need to display
        nameView.setText(message.messageSummary);

        zipView.setText(String.valueOf(message.author)); //always pay attention to your datatypes!
        int resId = mContext.getResources().getIdentifier(message.image,"drawable",mContext.getPackageName());
        imageView.setImageResource(resId);

        String icon = message.connected ? "ic_check_circle" : "ic_add_circle";

        ImageView connectedView = (ImageView) row.findViewById(R.id.connectedView);
        int connectedId = mContext.getResources().getIdentifier(icon,"drawable",mContext.getPackageName());
        connectedView.setImageResource(connectedId);

        //returning the row view (because this is called getView after all)
        return row;
    }
}
