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
import android.widget.Button;
import android.widget.TextView;

import com.codepath.appointsy.AppointmentPost;
import com.codepath.appointsy.BusinessPost;
import com.codepath.appointsy.BusinessPostAdapter;
import com.codepath.appointsy.databinding.FragmentBusinessBinding;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class BusinessFragment extends Fragment {


    private String TAG = "BusinessFragment";
    private RecyclerView rvBusinessPost;
    protected BusinessPostAdapter adapter;
    protected SwipeRefreshLayout swipeContainer;
    protected List<BusinessPost> allBusinessPost;
    private FragmentBusinessBinding binding;
    protected String UserInput;


    public BusinessFragment() {
        // Required empty public constructor
    }

    public static BusinessFragment newInstance(String param1, String param2) {
        BusinessFragment fragment = new BusinessFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBusinessBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "Post started");
        Bundle bundle = this.getArguments();
        if(bundle != null) {
            UserInput = bundle.getString("getBusinessType");
        }

        swipeContainer =  binding.swipeContainer;
        swipeContainer.setOnRefreshListener(()->{
            Log.i(TAG, "fetching Data");
            queryPosts();
        });

        rvBusinessPost = binding.rvBusinessPost;
        allBusinessPost = new ArrayList<>();
        adapter = new BusinessPostAdapter(getContext(), allBusinessPost);
        // 1. create layout for one row in the list
        // 2. create the adapter
        // 3. create the data source
        // 4. set the adapter on the recycler view
        rvBusinessPost.setAdapter(adapter);
        // 5. set the layout manager on the recycler view
        rvBusinessPost.setLayoutManager(new LinearLayoutManager(getContext()));
        queryPosts();
    }

    protected void queryPosts(){
        String type = UserInput;
        ParseQuery<BusinessPost> query = ParseQuery.getQuery(BusinessPost.class);
        query.whereExists("businessProfileID"); // find adults
        query.include("businessProfileID");
        query.findInBackground((List<BusinessPost> posts, ParseException e) -> {
            if (e == null) {
                for(BusinessPost post: posts){
                    // getData from User
                    String businessUser = post.getString("username");
                    String businessBio = post.getString("userBio");
                    ParseFile businessImage = post.getParseFile("profileImage");

                    // getData from the business Table
                    ParseObject businessTable = post.getParseObject("businessProfileID");
                    String businessId = businessTable.getObjectId();
                    String businessName = businessTable.getString("businessName");
                    Number businessPrice = businessTable.getNumber("servicePrice");
                    String businessLocation = businessTable.getString("location");
                    String businessType = businessTable.getString("businessType");
                    String businessOwner = businessTable.getString("ownerName");
                    JSONArray businessHours = businessTable.getJSONArray("businessHours");

                    //set the post
                    post.setBusinessId(businessId);
                    post.setBusinessName(businessName);
                    post.setServicePrice(businessPrice);
                    post.setBusinessLocation(businessLocation);
                    post.setBusinessType(businessType);
                    post.setBusinessOwner(businessOwner);
                    Log.i(TAG, "Post   #e   " + businessId);

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