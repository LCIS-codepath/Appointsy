package com.codepath.appointsy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.codepath.appointsy.databinding.ActivityAppointmentViewBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;
import com.parse.ParseACL;
import com.parse.ParseFile;
import com.parse.ParseFileUtils;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.parceler.Parcels;

public class AppointmentViewActivity extends AppCompatActivity {
    private ActivityAppointmentViewBinding binding;
    private ImageView ivIcon;
    private MaterialTextView tvName;
    private MaterialTextView tvDate;
    private MaterialTextView tvDetails;
    private MaterialButton btnDirections;
    private MaterialButton btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_view);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_appointment_view);


        ivIcon = binding.ivIcon;
        tvName = binding.tvName;
        tvDate = binding.tvDate;
        tvDetails = binding.tvDetails;
        btnDirections = binding.btnDirections;
        btnCancel = binding.btnCancel;

        Bundle bundle = getIntent().getExtras();
        AppointmentPost appointmentPost = bundle.getParcelable("appointmentParseObject");

        ParseFile image = appointmentPost.getAppointmentBusinessImage();
        if(image != null)
            Glide.with(this).load(image.getUrl()).into(ivIcon);
        else
            Glide.with(this).load(R.drawable.ic_iconcmpt).into(ivIcon);

        tvName.setText(appointmentPost.getAppointmentBusinessName());
        tvDate.setText(appointmentPost.getAppointmentDate());
        tvDetails.setText(appointmentPost.getAppointmentDetails());

        // get directions to business location
        btnDirections.setOnClickListener((e -> {
            // extended feature
        }));

        // cancel appointment
        btnCancel.setOnClickListener((e -> {
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Appointment");

            // Retrieve the object by id
            query.getInBackground("<PARSE_OBJECT_ID>", (object, a) -> {
                if (a == null) {
                    //Object was fetched
                    //Deletes the fetched ParseObject from the database
                    object.deleteInBackground(a2 -> {
                        if(a2==null){
                            Toast.makeText(this, "Delete Successful", Toast.LENGTH_SHORT).show();
                        }else{
                            //Something went wrong while deleting the Object
                            Toast.makeText(this, "Error: "+a2.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    //Something went wrong
                    Toast.makeText(this, a.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }));
    }
}