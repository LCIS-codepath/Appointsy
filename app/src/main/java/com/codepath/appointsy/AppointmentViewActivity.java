package com.codepath.appointsy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.codepath.appointsy.databinding.ActivityAppointmentViewBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;
import com.parse.ParseACL;
import com.parse.ParseFileUtils;
import com.parse.ParseUser;

import org.parceler.Parcels;

public class AppointmentViewActivity extends AppCompatActivity {
    private ActivityAppointmentViewBinding binding;
    private ImageView ivIcon;
    private MaterialTextView tvName;
    private MaterialTextView tvDetails;
    private MaterialTextView tvContactInfo;
    private MaterialButton btnDirections;
    private MaterialButton btnReschedule;
    private MaterialButton btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_view);

        Intent i = getIntent();
        ParseACL appointment = Parcels.unwrap((ParseUser) i.getParcelableExtra("appointment"));
        ivIcon = binding.ivIcon;
        tvName = binding.tvName;
        tvDetails = binding.tvDetails;
        tvContactInfo = binding.tvContactInfo;
        btnDirections = binding.btnDirections;
        btnReschedule = binding.btnReschedule;
        btnCancel = binding.btnCancel;

//        ivIcon.setImageIcon();
//        tvName.setText();
//        tvDetails.setText();
//        tvContactInfo.setText();

        // get directions to business location
        btnDirections.setOnClickListener((e -> {

        }));

        // reschedule appointment
        btnReschedule.setOnClickListener((e -> {

        }));

        // cancel appointment
        btnCancel.setOnClickListener((e -> {

        }));
    }
}