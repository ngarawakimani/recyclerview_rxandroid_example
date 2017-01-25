package com.wakimani.recyclerviewrxandroidexample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

//import com.squareup.picasso.Picasso;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by wa kimani on 11/12/2016.
 */

public class ItemsAdapter extends RecyclerView.Adapter<ItemsViewHolder> {

    Context mContext;
    List<ItemsList> list;

    public ItemsAdapter(Context mContext, List<ItemsList> list) {
        this.mContext = mContext;
        this.list = list;
    }


    @Override
    public ItemsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_layout,parent,false);
        ItemsViewHolder itemsViewHolder = new ItemsViewHolder(view);
        return itemsViewHolder;
    }

    @Override
    public void onBindViewHolder(ItemsViewHolder holder, int position) {
        ItemsList itemsList = list.get(position);

        Picasso.with(mContext)
                .load("http://192.168.43.59/android/norem/photos/" + itemsList.image)
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.image);
        //holder.image.setImageResource(itemsList.image);
        holder.title.setText(itemsList.title);
        holder.description.setText(itemsList.description);
        holder.station.setText(itemsList.station);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class ItemsViewHolder extends RecyclerView.ViewHolder{

    ImageView image;
    TextView title;
    TextView description;
    TextView station;

    public ItemsViewHolder(View itemView) {
        super(itemView);
        image = (ImageView)itemView.findViewById(R.id.container_image);
        title = (TextView)itemView.findViewById(R.id.container_title);
        description = (TextView)itemView.findViewById(R.id.container_description);
        station = (TextView)itemView.findViewById(R.id.container_station);
    }
}
