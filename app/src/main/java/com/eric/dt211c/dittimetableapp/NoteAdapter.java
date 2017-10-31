package com.eric.dt211c.dittimetableapp;

/**
 * Created by eric on 31/10/2017.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;



/**
 * Created by eric on 31/10/2017.
 */

public class NoteAdapter extends ArrayAdapter<Task> {
    Context mContext;
    int mLayoutResourceId;
    Task data[]= null;

    public NoteAdapter(Context context, int resource, Task[] data){
        super(context,resource,data);
        this.mContext = context;
        this.mLayoutResourceId = resource;
        this.data = data;

    }


    public Task getItem(int position){
        return super.getItem(position);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View row = convertView;

        LayoutInflater inflater = LayoutInflater.from(mContext);
        row = inflater.inflate(mLayoutResourceId,parent,false);

        TextView titleView = (TextView) row.findViewById(R.id.nameTextView);
        TextView descView = (TextView) row.findViewById(R.id.descTextView);
        TextView priorityView = (TextView) row.findViewById(R.id.priority);
        TextView createdView = (TextView) row.findViewById(R.id.created);
        ImageView imageView = (ImageView) row.findViewById(R.id.imageView);

        Task task = data[position];

        titleView.setText(task.getTitle());
        descView.setText(task.getDescription());
        priorityView.setText(task.getUrgencyLevel());
        createdView.setText(task.getDate() + " : " + task.getTime());

        int resId = mContext.getResources().getIdentifier(task.image,"drawable", mContext.getPackageName());
        imageView.setImageResource(resId);

        return row;

    }

}
