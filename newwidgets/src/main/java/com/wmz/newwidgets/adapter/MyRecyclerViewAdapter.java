package com.wmz.newwidgets.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wmz.newwidgets.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wmz on 26/5/16.
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyViewHolder>{

    private Context context;
    private List<String> datas;
    private LayoutInflater inflater;
    public MyRecyclerViewAdapter(Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);
        datas = new ArrayList<>();
        for(int i='A';i<'Z';i++){
            datas.add(String.valueOf((char)i));
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_main,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        if(listener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(v,position);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onItemLongClick(v,position);
                    return true;
                }
            });
        }
        holder.textView.setText(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
        void onItemLongClick(View view,int position);
    }
}
