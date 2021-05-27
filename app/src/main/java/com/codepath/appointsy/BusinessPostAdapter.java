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
import com.codepath.appointsy.databinding.ItemBusinessPostBinding;
import com.codepath.appointsy.fragments.BusinessFragment;
import com.codepath.appointsy.CreateAppointmentActivity;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.List;

public class BusinessPostAdapter extends RecyclerView.Adapter<BusinessPostAdapter.ViewHolder> {

    private final Context context;
    private final List<BusinessPost> businessPost;
    private ItemBusinessPostBinding binding;
    private boolean status = false;
    private final String TAG = "BusinessPostAdapter";

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
        private final RelativeLayout rlBusinessPost;
        private final ImageView ivBusinessImage;
        private final TextView tvBusinessName;
        private final TextView tvBusinessType;
        private final TextView tvDetails;
//        private final TextView tvAppointmentStatus;
//        private final ImageView ivStatusIcon;
        private final TextView tvDistance;
        private final ImageView ivFavorites;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rlBusinessPost = binding.rlItemBusinessPost;
            ivBusinessImage = binding.ivBusinessImage;
            tvBusinessName =  binding.tvBusinessName;
            tvBusinessType =  binding.tvBusinessType;
            tvDetails =  binding.tvDetails;
//            tvAppointmentStatus =  binding.tvBusinessStatus;
//            ivStatusIcon =  binding.ivStatusIcon;
            tvDistance =  binding.tvDistance;
            ivFavorites = binding.ivFavorite;
        }

        public void bind(BusinessPost businessPosts) {
            Log.i("BusinessFragment", "bind adapter called");

            tvBusinessName.setText(businessPosts.getBusinessName());
            tvBusinessType.setText(businessPosts.getBusinessType());
            tvDetails.setText(businessPosts.getBusinessBio());
            ParseFile image = businessPosts.getBusinessImage();
            if(image != null){
                Glide.with(context).load(image.getUrl()).into(ivBusinessImage);
            }
            else{
                Glide.with(context).load(R.drawable.ic_iconcmpt).into(ivBusinessImage);

            }


            ivFavorites.setOnClickListener(v -> {
                if(status){
                    ivFavorites.setImageResource(R.drawable.filled_star);
                    status = false;
                    try {
                        favoriteQuery(ParseUser.getCurrentUser(), ParseUser.become(businessPosts.getBusinessId()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }else{
                    ivFavorites.setImageResource(R.drawable.ic_not_favorite);
                    status = true;
                }

            });


//            Bundle bundle = getIntent().getExtras();
//            BusinessPost businessPost = bundle.getParcelable("ParseOBJECT");
            Log.i("TemporaryActivity", "testing "  + businessPosts.getBusinessId() + " "
            + businessPosts.getBusinessHours());

            rlBusinessPost.setOnClickListener(v -> {
              Intent i = new Intent(context, BusinessDetails.class);
                // need to add Parcels to the project
              i.putExtra("businessParseObject", businessPosts);
              context.startActivities(new Intent[]{i});
              Log.i(TAG, "open activity");



            });

        }

        private void favoriteQuery(ParseUser user, ParseUser business) {
            ParseObject favorites = new ParseObject("UserFavorites");


            String data = "CibJYeLNkz";
            favorites.put("userObjectID", ParseObject.createWithoutData("_User","HiwnIOsxa8" ));
            favorites.put("businessObjectID", ParseObject.createWithoutData(  "BusinessProfile", data));
            favorites.saveInBackground(e -> {
                if(e == null){
                    Log.i(TAG, "Success");
                }else{
                    Log.e(TAG, "falure ", e);

                }
            });
        }
    }

}
