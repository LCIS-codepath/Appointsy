package com.codepath.appointsy.sideBarAppoinments;

import android.util.Log;

import com.codepath.appointsy.AppointmentPost;
import com.codepath.appointsy.fragments.AppointmentFragment;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class AppoinmentOrderNameFragment extends AppointmentFragment {

    protected void queryPosts(){

        //  Log.i(TAG, "business Hash Table Size " + businessImageHashMap.size() );
        ParseQuery<AppointmentPost> query1 = ParseQuery.getQuery(AppointmentPost.class);
        //find the appointments for the signed in user
        query1.whereEqualTo(AppointmentPost.KEY_USER_OBJECT_ID, ParseUser.getCurrentUser() );
        // include will join the business Table with Appointment Table
        query1.include(AppointmentPost.KEY_USER_OBJECT_ID);
        query1.include(AppointmentPost.KEY_BUSINESS_ID);
        query1.orderByAscending("time");
        query1.findInBackground((posts, e) -> {

            if(e != null){
                Log.e(TAG, "Issues with getting post", e);
                return;
            }
            for(AppointmentPost post: posts){
                // to access the Business Table
                // Use ParseObject on business Object Id to access it's data
                ParseObject businessTable = post.getParseObject("businessObjectID");
                String businessName = businessTable.getString("businessName");
                ParseObject appointmentTable = post.getParseObject(post.KEY_USER_OBJECT_ID);
                //  ParseFile image = businessImageHashMap.get(businessTable);
                // if(businessImageHashMap.contains(businessTable)) {
//                    Log.i(TAG, "id  " + businessTable);

                //              }

                post.setAppointmentBusinessName(businessName);
                // Log.i(TAG, "image found  " + image);
                // add a null check for business Image
                // if(image != null) {
                Log.i(TAG, "image found ");
                //    post.setAppointmentBusinessImage(image);
                //}else {
                //    Log.i(TAG, "image failed ");
                // }
            }
            adapter.clear();
            adapter.addAll(posts);
            swipeContainerAppointment.setRefreshing(false);
        });
    }

}
