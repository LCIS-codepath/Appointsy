package com.codepath.appointsy.sideBarFragments;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import com.codepath.appointsy.BusinessPost;
import com.codepath.appointsy.fragments.BusinessFragment;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.json.JSONArray;

import java.util.List;

public class BusinessTypeFragment extends BusinessFragment {

    public static final String TAG = "BusinessTypeFragment";

    private SearchViewModel searchViewModel;
    private String searchType = "";
    protected void queryPosts(){
        String search;
        SearchViewModel model = new ViewModelProvider(requireActivity()).get(SearchViewModel.class);
       search = String.valueOf(model.getSelected().getValue());
        Log.i(TAG, "get value " + search);

        ParseQuery<ParseObject> innerQuery = ParseQuery.getQuery("BusinessProfile");
        innerQuery.whereMatches("businessType", search);

        ParseQuery<BusinessPost> query = ParseQuery.getQuery(BusinessPost.class);
        query.whereExists("businessProfileID"); // find adults
        query.include("businessProfileID");
        query.whereMatchesQuery("businessProfileID", innerQuery);


        query.findInBackground((List<BusinessPost> posts, ParseException e) -> {
            if (e == null) {
                for(BusinessPost post: posts){
                    // getData from User
                    String businessUser = post.getString("username");
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
                    Log.i(TAG, "Post " + businessName  + " #e " +  businessBio + "  ");

                    //add image

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
