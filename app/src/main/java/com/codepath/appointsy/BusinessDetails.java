package com.codepath.appointsy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.appointsy.databinding.ActivityBusinessDetailsBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.parse.ParseACL;

public class BusinessDetails extends AppCompatActivity {
    private ActivityBusinessDetailsBinding binding;
    private ImageView ivIcon;
    private MaterialTextView tvName;
    private MaterialTextView tvContactInfo;
    private MaterialTextView tvPhoneNumber;
    private MaterialTextView tvHours;
    private MaterialTextView tvDetails;
    private MaterialTextView tvServiceType;
    private MaterialButton btnDirections;
    private MaterialButton btnScheduleAppt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_details);

        ivIcon = binding.ivIcon;
        tvName = binding.tvName;
        tvContactInfo = binding.tvContactInfo;
        tvPhoneNumber = binding.tvPhoneNumber;
        tvHours = binding.tvHours;
        tvDetails = binding.tvDetails;
        tvServiceType = binding.tvServiceType;

        Bundle bundle = getIntent().getExtras();
        BusinessPost businessPost = bundle.getParcelable("businessParseObject");

//        ivIcon.setImageURI(businessPost.getBusinessImage());
        tvName.setText(businessPost.getBusinessName());
        tvContactInfo.setText(businessPost.getBusinessOwner());
        tvPhoneNumber.setText(businessPost.getBusinessPhoneNumber());
//        tvHours.setText();
        tvDetails.setText(businessPost.getBusinessBio());
        tvServiceType.setText(businessPost.getBusinessType());

        btnDirections.setOnClickListener((e -> {
            // extended feature
        }));

        btnScheduleAppt.setOnClickListener((e -> {
            // in progress
        }));
        
    }
}