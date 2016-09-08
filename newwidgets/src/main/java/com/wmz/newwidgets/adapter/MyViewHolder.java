package com.wmz.newwidgets.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.wmz.newwidgets.R;

/**
 * Created by wmz on 26/5/16.
 */
public class MyViewHolder extends RecyclerView.ViewHolder{
    public TextView textView;
    public MyViewHolder(View itemView) {
        super(itemView);

        textView = (TextView) itemView.findViewById(R.id.id_textview);
    }
}
