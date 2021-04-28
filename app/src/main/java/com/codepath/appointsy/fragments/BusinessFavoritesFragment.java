package com.codepath.appointsy.fragments;

import android.util.Log;

import com.codepath.appointsy.BusinessPost;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.json.JSONArray;

import java.util.List;

public class BusinessFavoritesFragment extends BusinessFragment {
    @Override
    protected void queryPosts() {
        super.queryPosts();
        ParseQuery<BusinessPost> query = ParseQuery.getQuery(BusinessPost.class);
        query.whereExists("businessProfileID"); // find adults
        query.include("businessProfileID");
        query.setLimit(1);
        query.findInBackground((List<BusinessPost> posts, ParseException e) -> {
            if (e == null) {
                for(BusinessPost post: posts){
                    // getData from User
                    String businessBio = post.getString("userBio");
                    ParseFile businessImage = post.getParseFile("profileImage");

                    // getData from the business Table
                    ParseObject businessTable = post.getParseObject("businessProfileID");
                    String businessName = businessTable.getString("businessName");
                    Number businessPrice = businessTable.getNumber("servicePrice");
                    String businessLocation = businessTable.getString("location");
                    String businessType = businessTable.getString("businessType");
                    String businessOwner = businessTable.getString("ownerName");
                    JSONArray businessHours = businessTable.getJSONArray("businessHours");

                    //set the post
                    post.setBusinessName(businessName);
                    post.setServicePrice(businessPrice);
                    post.setBusinessLocation(businessLocation);
                    post.setBusinessType(businessType);
                    post.setBusinessOwner(businessOwner);
                   // Log.i(TAG, "Post " + businessName  + " #e " +  businessBio + "  ");

                }
            } else {
                // Something went wrong.
            }
            adapter.clear();
            adapter.addAll(posts);
            swipeContainer.setRefreshing(false);
        });
    }
}
