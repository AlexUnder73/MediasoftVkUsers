package com.example.formi.mediasoftnetworking.presentation.nameSearch.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.formi.mediasoftnetworking.R;
import com.example.formi.mediasoftnetworking.domain.model.name.Item;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NameAdapter extends RecyclerView.Adapter<NameAdapter.NameViewHolder>{

    private Context ctx;
    private List<Item> itemList;

    public NameAdapter(Context ctx, List<Item> itemList) {
        this.ctx = ctx;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public NameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.user_item, parent, false);
        return new NameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NameViewHolder holder, int position) {
        Item item = itemList.get(position);

        holder.txtFirstNameItem.setText(item.getFirstName());
        holder.txtLastNameItem.setText(item.getLastName());
        Picasso.with(ctx).load(item.getImgURL()).placeholder(R.drawable.place_holder).into(holder.imgPhotoItem);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class NameViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgPhotoItem;
        private TextView txtFirstNameItem, txtLastNameItem;

        public NameViewHolder(View itemView) {
            super(itemView);

            imgPhotoItem = itemView.findViewById(R.id.imgPhotoItem);
            txtFirstNameItem = itemView.findViewById(R.id.txtFirstNameItem);
            txtLastNameItem = itemView.findViewById(R.id.txtLastNameItem);
        }
    }
}
