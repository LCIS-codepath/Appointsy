package com.codepath.appointsy;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.appointsy.databinding.ItemAppointmentPostBinding;
import com.parse.ParseFile;

import java.util.List;

public class AppointmentPostAdapter extends RecyclerView.Adapter<AppointmentPostAdapter.ViewHolder> {

    private final Context context;
    private final List<AppointmentPost> appointmentPosts;
    private ItemAppointmentPostBinding binding;

    public AppointmentPostAdapter(Context context, List<AppointmentPost> appointmentPosts){
        this.context = context;
        this.appointmentPosts = appointmentPosts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        binding = ItemAppointmentPostBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding.getRoot());
        
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AppointmentPost appointmentPost = appointmentPosts.get(position);
        holder.bind(appointmentPost);
    }

    @Override
    public int getItemCount() {
        return appointmentPosts.size();
    }

    // Clean all elements of the recycler
    public void clear() {
        appointmentPosts.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<AppointmentPost> list) {
        appointmentPosts.addAll(list);
        notifyDataSetChanged();
    }



    class ViewHolder extends RecyclerView.ViewHolder{

        private  RelativeLayout rlAppointment;
        private final ImageView ivAppointmentImage;
        private final TextView tvAppointmentBusinessName;
        private final TextView tvAppointmentTime;
        private final TextView  tvAppointmentDetails;
        private final TextView tvAppointmentStatus;
        private final ImageView ivStatusIcon;
        private final TextView tvDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rlAppointment = binding.rlAppoinment;
            ivAppointmentImage = binding.ivAppointmentImage;
            tvAppointmentBusinessName =  binding.tvAppointmentBusinessName;
            tvAppointmentTime =  binding.tvAppointmentTime;
            tvAppointmentDetails =  binding.tvAppointmentDetails;
            tvAppointmentStatus =  binding.tvAppointmentStatus;
            ivStatusIcon = binding.ivStatusIcon;
            tvDate = binding.tvDate;

        }

        public void bind(AppointmentPost appointmentPost) {

            tvAppointmentBusinessName.setText(appointmentPost.getAppointmentBusinessName());
            tvAppointmentTime.setText(appointmentPost.getAppointmentTime());
            tvAppointmentDetails.setText(appointmentPost.getAppointmentDetails());
            tvDate.setText(appointmentPost.getAppointmentDate());
            ParseFile image = appointmentPost.getAppointmentBusinessImage();
            ///Log.i()
            if(image != null){
                Glide.with(context).load(image.getUrl()).override(300, 200).into(ivAppointmentImage);
            }else{
                Glide.with(context).load(R.drawable.ic_iconcmpt).override(300, 200).into(ivAppointmentImage);

            }
            rlAppointment.setOnClickListener((v) -> {
               // Intent i = new Intent(context, )
               //  i.putExtra("tweet", Parcels.wrap(tweet));
               //  context.startActivities(new Intent[]{i});
            });

        }
    }
}
