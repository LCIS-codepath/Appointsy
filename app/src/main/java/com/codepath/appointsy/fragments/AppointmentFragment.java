package com.codepath.appointsy.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codepath.appointsy.AppointmentPost;
import com.codepath.appointsy.AppointmentPostAdapter;
import com.codepath.appointsy.BusinessPost;
import com.codepath.appointsy.R;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AppointmentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AppointmentFragment extends Fragment {

    public String TAG = "AppointmentFragment";
    private RecyclerView rvAppointmentPost;
    private AppointmentPostAdapter adapter;
    private List<AppointmentPost> allAppointmentPost;



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
        return inflater.inflate(R.layout.fragment_appointment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvAppointmentPost = view.findViewById(R.id.rvAppointmentPost);
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

    private void queryPosts(){
        ParseQuery<AppointmentPost> query = ParseQuery.getQuery(AppointmentPost.class);
        query.include(AppointmentPost.KEY_USER_OBJECT_ID);
        query.findInBackground((posts, e) -> {
            if(e != null){
                Log.e(TAG, "Issues with getting post", e);
                return;
            }
            for(AppointmentPost post: posts){
                Log.i(TAG, "Post " + post.getUserId() );
            }
            allAppointmentPost.addAll(posts);
            adapter.notifyDataSetChanged();
        });
    }
}