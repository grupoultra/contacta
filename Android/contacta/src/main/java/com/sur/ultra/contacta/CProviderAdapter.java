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
public class CProviderAdapter extends ArrayAdapter<CProvider> {

    Context mContext;
    int mLayoutResourceId;
    CProvider mData[] = null;

    public CProviderAdapter(Context context, int resource, CProvider[] data) {
        super(context, resource, data);
        this.mContext = context;
        this.mLayoutResourceId = resource;
        this.mData = data;
    }

    @Override
    public CProvider getItem(int position) {
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

        //get the data from the data array
        CProvider cprovider = mData[position];

        //setting the view to reflect the data we need to display
        nameView.setText(cprovider.messageSummary);
        ImageView avatarView = (ImageView) row.findViewById(R.id.imageView);
        int avatarId = mContext.getResources().getIdentifier(cprovider.image,"drawable",mContext.getPackageName());
        avatarView.setImageResource(avatarId);

        //returning the row view (because this is called getView after all)
        return row;
    }
}
