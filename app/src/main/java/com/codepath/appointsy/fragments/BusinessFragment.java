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

import com.codepath.appointsy.BusinessPost;
import com.codepath.appointsy.BusinessPostAdapter;
import com.codepath.appointsy.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BusinessFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BusinessFragment extends Fragment {

    private String TAG = "BusinessFragment";
    private RecyclerView rvBusinessPost;
    private BusinessPostAdapter adapter;
    private SwipeRefreshLayout swipeContainer;
    private List<BusinessPost> allBusinessPost;


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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_business, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "Post started");

        swipeContainer =  view.findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(()->{
            Log.i(TAG, "fetching Data");
            queryPosts();
        });

        rvBusinessPost = view.findViewById(R.id.rvBusinessPost);
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

    private void queryPosts(){
        ParseQuery<BusinessPost> query = ParseQuery.getQuery(BusinessPost.class);
        query.include(BusinessPost.KEY_BUSINESS_NAME);
        query.setLimit(20);
        query.findInBackground((posts, e) -> {
            if(e != null){
                Log.e(TAG, "Issues with getting post", e);
                return;
            }
            for(BusinessPost post: posts){
                Log.i(TAG, "Post " + post.getBusinessName());
            }
            adapter.clear();
            adapter.addAll(posts);
            swipeContainer.setRefreshing(false);
            //allBusinessPost.addAll(posts);
          //  adapter.notifyDataSetChanged();
        });
    }
}