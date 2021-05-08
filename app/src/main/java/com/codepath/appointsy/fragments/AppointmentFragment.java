package com.codepath.appointsy.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.codepath.appointsy.AppointmentPost;
import com.codepath.appointsy.AppointmentPostAdapter;
import com.codepath.appointsy.databinding.FragmentAppointmentBinding;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AppointmentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AppointmentFragment extends Fragment {

    public String TAG = "AppointmentFragment";

    public static AtomicInteger atomInt = new AtomicInteger(0);
    public static Hashtable<ParseObject, ParseFile> businessImageHashMap;
    private RecyclerView rvAppointmentPost;
    protected SwipeRefreshLayout swipeContainerAppointment;
    protected AppointmentPostAdapter adapter;
    protected List<AppointmentPost> allAppointmentPost;
    private FragmentAppointmentBinding binding;


    public AppointmentFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AppointmentFragment newInstance(String param1, String param2) {
        AppointmentFragment fragment = new AppointmentFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAppointmentBinding.inflate(inflater,container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        queryBusinessImage();
        rvAppointmentPost = binding.rvAppointmentPost;

        swipeContainerAppointment= binding.swipeContainerAppointment;
        swipeContainerAppointment.setOnRefreshListener(() -> {
            Log.i(TAG, "fetching Data");
            queryPosts();
        });

        allAppointmentPost = new ArrayList<>();
        adapter = new AppointmentPostAdapter(getContext(), allAppointmentPost);

        // 1. create layout for one row in the list
        // 2. create the adapter
        // 3. create the data source
        // 4. set the adapter on the recycler view
        rvAppointmentPost.setAdapter(adapter);
        // 5. set the layout manager on the recycler view
        rvAppointmentPost.setLayoutManager(new LinearLayoutManager(getContext()));
        queryPosts();

    }


    private void newQueryPostTwo(){
        CountDownLatch mCountDownLatch = new CountDownLatch(1);
        businessImageHashMap = new Hashtable<ParseObject, ParseFile>();
        ParseFile[] imageFile = new ParseFile[1];
        // Log.i(TAG, "hello " + businessId);
        Log.i(TAG, "queryBusinessImage ");

        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereExists("businessProfileID");
        //query.whereEqualTo("businessProfileID", businessID);

        ParseQuery<AppointmentPost> query1 = ParseQuery.getQuery(AppointmentPost.class);
        //find the appointments for the signed in user
        query1.whereEqualTo(AppointmentPost.KEY_USER_OBJECT_ID, ParseUser.getCurrentUser() );
        // include will join the business Table with Appointment Table
        query1.include(AppointmentPost.KEY_USER_OBJECT_ID);
        query1.include(AppointmentPost.KEY_BUSINESS_ID);

        query.findInBackground((posts, e) -> {
            if(e != null){
                Log.e(TAG, "Issues with getting post", e);
                return;
            }
            mCountDownLatch.countDown();
            for(ParseUser post: posts) {
                ParseObject id = post.getParseObject("businessProfileID");
                ParseFile image = post.getParseFile("profileImage");
                if(!businessImageHashMap.contains(id)) {
                    Log.i(TAG, " new id: " + id);
                    Log.i(TAG, "adding new key to table");
                    businessImageHashMap.put(id, image);
                }

            }
          //  mCountDownLatch.notifyAll();
        });
        Log.i(TAG, " START QUERY POST");

//  Log.i(TAG, "business Hash Table Size " + businessImageHashMap.size() );


        try {
            mCountDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            queryPosts();
            mCountDownLatch.notify();
        }
    }


    protected void queryPosts(){

      //  Log.i(TAG, "business Hash Table Size " + businessImageHashMap.size() );
        ParseQuery<AppointmentPost> query1 = ParseQuery.getQuery(AppointmentPost.class);
        //find the appointments for the signed in user
        query1.whereEqualTo(AppointmentPost.KEY_USER_OBJECT_ID, ParseUser.getCurrentUser() );
        // include will join the business Table with Appointment Table
        query1.include(AppointmentPost.KEY_USER_OBJECT_ID);
        query1.include(AppointmentPost.KEY_BUSINESS_ID);
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

    private void queryBusinessImage(){
        businessImageHashMap = new Hashtable<ParseObject, ParseFile>();
        ParseFile[] imageFile = new ParseFile[1];
       // Log.i(TAG, "hello " + businessId);
        Log.i(TAG, "queryBusinessImage ");

        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereExists("businessProfileID");
       //query.whereEqualTo("businessProfileID", businessID);
        query.findInBackground((posts, e) -> {
            if(e != null){
                Log.e(TAG, "Issues with getting post", e);
                return;
            }
            for(ParseUser post: posts) {
                ParseObject id = post.getParseObject("businessProfileID");
                ParseFile image = post.getParseFile("profileImage");
                if(!businessImageHashMap.contains(id)) {
                    Log.i(TAG, " new id: " + id);
                    Log.i(TAG, "adding new key to table");
                    businessImageHashMap.put(id, image);
                }

            }
        });
        Log.i(TAG, " START QUERY POST");
       // queryPosts();

    }

//    private void (ParseUser post, ParseException e) {
//
//        for (ParseUser user : post) {
//            ParseObject businessProfileID = user.getParseObject("businessProfileID");
//            ParseFile image = user.getParseFile("profileImage");
//
//            Log.i(TAG, " new id: " + businessProfileID);
//            if (!businessImageHashMap.containsKey(businessProfileID)) {
//                Log.i(TAG, "adding new key to table");
//                businessImageHashMap.put(businessProfileID, image);
//
//            }
//            // Log.i(TAG, "name: " + name + " " + imageFile[0]) ;
//        }
//    }
}

