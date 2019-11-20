package com.sunilrana.googleinboxanimation;

import android.content.Context;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class RecylerAdapter extends RecyclerView.Adapter<RecylerAdapter.ViewHolder>{
    private ArrayList<String> listdata = new ArrayList<>();
    OnItemClickListener mListener;

    // RecyclerView recyclerView;
    public RecylerAdapter(OnItemClickListener listener) {
      //  this.listdata = listdata;
        mListener = listener;
    }

    public void setData (ArrayList<String> data){
        listdata = data;
        notifyDataSetChanged();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(mListener, position, listdata.get(position));
    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.textView = (TextView) itemView.findViewById(R.id.email_title);
            this.imageView = (ImageView) itemView.findViewById(R.id.image);

        }

        public void bind(final OnItemClickListener listener, final int position, String data){

            final AnimatedVectorDrawableCompat drawableCompat =  AnimatedVectorDrawableCompat.create(imageView.getContext(), R.drawable.animate_drawable);
            imageView.setImageDrawable(drawableCompat);
            textView.setText(TextUtils.concat("S", data));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(itemView , position);
                    drawableCompat.start();
                }
            });
        }
    }
}
