package com.example.formi.mediasoftnetworking.presentation.idSearch.requests.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.formi.mediasoftnetworking.R;
import com.example.formi.mediasoftnetworking.domain.model.id.User;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RequestsAdapter extends RecyclerView.Adapter<RequestsAdapter.RequestsViewHolder> {

    public interface OnDeleteClickListener{
        void deleteFromDb(int pos);
    }

    private OnDeleteClickListener onDeleteClickListenerInstance;

    public void setOnDeleteClickListenerInstance(OnDeleteClickListener onDeleteClickListenerInstance){
        this.onDeleteClickListenerInstance = onDeleteClickListenerInstance;
    }

    private Context ctx;
    private List<User> userList;

    public RequestsAdapter(Context ctx, List<User> userList) {
        this.ctx = ctx;
        this.userList = userList;
    }

    @NonNull
    @Override
    public RequestsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.user_item, parent, false);
        return new RequestsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestsViewHolder holder, int position) {
        User user = userList.get(position);

        String firstName = user.getResponse().get(0).getFirstName();
        String lastName = user.getResponse().get(0).getLastName();
        String imgUrl = user.getResponse().get(0).getImgURL();

        holder.txtFirstNameItem.setText(firstName);
        holder.txtLastNameItem.setText(lastName);
        Picasso.with(ctx).load(imgUrl).placeholder(R.drawable.place_holder).into(holder.imgPhotoItem);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class RequestsViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgPhotoItem;
        private TextView txtFirstNameItem, txtLastNameItem;
        private ImageButton btnDelete;

        public RequestsViewHolder(View itemView) {
            super(itemView);

            imgPhotoItem = itemView.findViewById(R.id.imgPhotoItem);
            txtFirstNameItem = itemView.findViewById(R.id.txtFirstNameItem);
            txtLastNameItem = itemView.findViewById(R.id.txtLastNameItem);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnDelete.setVisibility(View.VISIBLE);

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onDeleteClickListenerInstance != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            onDeleteClickListenerInstance.deleteFromDb(position);
                        }
                    }
                }
            });
        }
    }
}
