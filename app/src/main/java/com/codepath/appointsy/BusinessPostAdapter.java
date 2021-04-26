package com.codepath.appointsy;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.appointsy.databinding.ItemBusinessPostBinding;
import com.parse.ParseFile;

import java.util.List;

public class BusinessPostAdapter extends RecyclerView.Adapter<BusinessPostAdapter.ViewHolder> {

    private final Context context;
    private final List<BusinessPost> businessPost;
    private ItemBusinessPostBinding binding;

    public BusinessPostAdapter(Context context, List<BusinessPost> businessPost ){
        this.context = context;
        this.businessPost = businessPost;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        binding = ItemBusinessPostBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding.getRoot());
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

    // Clean all elements of the recycler
    public void clear() {
        businessPost.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<BusinessPost> list) {
        businessPost.addAll(list);
        notifyDataSetChanged();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        private RecyclerView rlBusinessPost;
        private final ImageView ivBusinessImage;
        private final TextView tvBusinessName;
        private final TextView tvBusinessType;
        private final TextView tvDetails;
        private final TextView tvAppointmentStatus;
        private final ImageView ivStatusIcon;
        private final TextView tvDistance;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivBusinessImage = binding.ivBusinessImage;
            tvBusinessName =  binding.tvBusinessName;
            tvBusinessType =  binding.tvBusinessType;
            tvDetails =  binding.tvDetails;
            tvAppointmentStatus =  binding.tvBusinessStatus;
            ivStatusIcon =  binding.ivStatusIcon;
            tvDistance =  binding.tvDistance;
//            rlBusinessPost = binding.rlItemBusinessPost;
        }

        public void bind(BusinessPost businessPosts) {
            tvBusinessName.setText(businessPosts.getBusinessName());
            tvBusinessType.setText(businessPosts.getBusinessType());
            tvDetails.setText(businessPosts.getBusinessBio());
            ParseFile image = businessPosts.getBusinessImage();
            if(image != null){
                Glide.with(context).load(image.getUrl()).override(300, 200).into(ivBusinessImage);
            }
//            rlBusinessPost.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                  Intent i = new Intent(context, )
//                    // need to add Parcels to the project
//                  i.putExtra("tweet", Parcels.wrap());
//                  context.startActivities(new Intent[]{i});
//                }
//            });

        }
    }

}
