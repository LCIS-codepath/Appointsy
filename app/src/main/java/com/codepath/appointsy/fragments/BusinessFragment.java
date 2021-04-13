package com.codepath.appointsy.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.codepath.appointsy.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BusinessFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BusinessFragment extends Fragment {


    private RecyclerView rvBusinessPost;
    private TextView tvBusiness;

    public BusinessFragment() {
        // Required empty public constructor
    }



    // TODO: Rename and change types and number of parameters
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
        rvBusinessPost = view.findViewById(R.id.rvBusinessPost);


        // 1. create layout for one row in the list
        // 2. create the adapter
        // 3. create teh data source
        // 4. set the adapter on the recycler view
        // 5. set the layout manaager on the recylcer view
    }
}