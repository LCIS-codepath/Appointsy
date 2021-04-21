package com.codepath.appointsy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.util.List;

public class BusinessPostAdapter extends RecyclerView.Adapter<BusinessPostAdapter.ViewHolder> {

    private final Context context;
    private final List<BusinessPost> businessPost;

    public BusinessPostAdapter(Context context, List<BusinessPost> businessPost ){
        this.context = context;
        this.businessPost = businessPost;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private final ImageView ivBusinessImage;
        private final TextView tvBusinessName;
        private final TextView tvBusinessType;
        private final TextView tvDetails;
        private final TextView tvAppointmentStatus;
        private final ImageView ivStatusIcon;
        private final TextView tvDistance;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivBusinessImage = itemView.findViewById(R.id.ivBusinessImage);
            tvBusinessName =  itemView.findViewById(R.id.tvBusinessName);
            tvBusinessType =  itemView.findViewById(R.id.tvBusinessType);
            tvDetails =  itemView.findViewById(R.id.tvDetails);
            tvAppointmentStatus =  itemView.findViewById(R.id.tvBusinessStatus);
            ivStatusIcon =  itemView.findViewById(R.id.ivStatusIcon);
            tvDistance =  itemView.findViewById(R.id.tvDistance);
        }

        public void bind(BusinessPost businessPosts) {
            tvBusinessName.setText(businessPosts.getBusinessName());
            tvBusinessType.setText(businessPosts.getBusinessType());
            tvDetails.setText(businessPosts.getBusinessBio());
            ParseFile image = businessPosts.getBusinessImage();
            if(image != null){
                Glide.with(context).load(businessPosts.getBusinessImage().getUrl()).override(300, 200).into(ivBusinessImage);
            }

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_business_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BusinessPost businessPosts = businessPost.get(position);
        holder.bind(businessPosts);
    }

    @Override
    public int getItemCount() {
        return businessPost.size();
    }



}
