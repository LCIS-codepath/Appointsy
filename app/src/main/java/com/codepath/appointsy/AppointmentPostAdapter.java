package com.codepath.appointsy;

import android.content.Context;
import android.util.Log;
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

public class AppointmentPostAdapter extends RecyclerView.Adapter<AppointmentPostAdapter.ViewHolder> {

    private final Context context;
    private final List<AppointmentPost> appointmentPosts;

    public AppointmentPostAdapter(Context context, List<AppointmentPost> appointmentPosts){
        this.context = context;
        this.appointmentPosts = appointmentPosts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_appointment_post, parent, false);
        return new ViewHolder(view);
        
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


    class ViewHolder extends RecyclerView.ViewHolder{

        private final ImageView ivAppointmentImage;
        private final TextView tvAppointmentBusinessName;
        private final TextView tvAppointmentTime;
        private final TextView  tvAppointmentDetails;
        private final TextView tvAppointmentStatus;
        private final ImageView ivStatusIcon;
        private final TextView tvDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivAppointmentImage = itemView.findViewById(R.id.ivAppointmentImage);
            tvAppointmentBusinessName =  itemView.findViewById(R.id.tvAppointmentBusinessName);
            tvAppointmentTime =  itemView.findViewById(R.id.tvAppointmentTime);
            tvAppointmentDetails =  itemView.findViewById(R.id.tvAppointmentDetails);
            tvAppointmentStatus =  itemView.findViewById(R.id.tvAppointmentStatus);
            ivStatusIcon =  itemView.findViewById(R.id.ivStatusIcon);
            tvDate = itemView.findViewById(R.id.tvDate);

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
        }
    }
}
