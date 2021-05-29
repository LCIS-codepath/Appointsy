package com.codepath.appointsy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.appointsy.databinding.ActivityBusinessDetailsBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.parse.ParseACL;
import com.parse.ParseFile;

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
        binding = DataBindingUtil.setContentView(this, R.layout.activity_business_details);


        ivIcon = binding.ivIcon;
        tvName = binding.tvName;
        tvContactInfo = binding.tvContactInfo;
        tvPhoneNumber = binding.tvPhoneNumber;
        tvHours = binding.tvHours;
        tvDetails = binding.tvDetails;
        tvServiceType = binding.tvServiceType;
        btnDirections = binding.btnDirections;
        btnScheduleAppt = binding.btnScheduleAppt;

        Bundle bundle = getIntent().getExtras();
        BusinessPost businessPost = bundle.getParcelable("businessParseObject");

        ParseFile image = businessPost.getBusinessImage();
        if(image != null)
            Glide.with(this).load(image.getUrl()).into(ivIcon);
        else
            Glide.with(this).load(R.drawable.ic_iconcmpt).into(ivIcon);

        tvName.setText(businessPost.getBusinessName());
        tvContactInfo.setText(getString(R.string.ownerLabel) + businessPost.getBusinessOwner());
        tvPhoneNumber.setText(" Phone Number: " +businessPost.getBusinessPhoneNumber());
        tvHours.setText("Hours: " + businessPost.getBusinessHours());
        tvDetails.setText("Details: " +businessPost.getBusinessBio());
        tvServiceType.setText("Type of Service "+businessPost.getBusinessType());

        btnDirections.setOnClickListener((e -> {
            // query
            Uri intentUri = Uri.parse("geo:0,0?q=" + "1600 Amphitheatre Parkway, Mountain+View, California");
            // create intent view w/ uri
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, intentUri);
            // add maps package
            mapIntent.setPackage("com.google.android.apps.maps");
            // open Google maps
            startActivity(mapIntent);
        }));

        btnScheduleAppt.setOnClickListener((e -> {
            Intent intent = new Intent(this, CreateAppointmentActivity.class);
            intent.putExtra("businessParseObject", businessPost );
            startActivity(intent);
        }));
        
    }
}