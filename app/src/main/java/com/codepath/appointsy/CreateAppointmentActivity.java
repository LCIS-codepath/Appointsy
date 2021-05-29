package com.codepath.appointsy;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.codepath.appointsy.databinding.ActivityBusinessDetailsBinding;
import com.codepath.appointsy.databinding.ActivityCreateAppointmentBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.Calendar;

public class CreateAppointmentActivity extends AppCompatActivity {

    private  final String TAG = "CreateAppointment";
    private ActivityCreateAppointmentBinding binding;
    private Context context;

    private MaterialTextView tvBusinessName;
    private MaterialButton btnPickDate;
    private MaterialTextView tvChosenDate;
    private MaterialButton btnPickTime;
    private MaterialTextView tvChosenTime;
    private MaterialTextView tvApptConfirmation;
    private MaterialButton btnScheduleAppt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_appointment);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_appointment);



        // set business name
        Bundle bundle = getIntent().getExtras();
        BusinessPost businessPost = bundle.getParcelable("businessParseObject");
        String businessID = businessPost.getBusinessId();
        Log.i(TAG, String.valueOf(businessID));
        tvBusinessName = binding.tvBusinessName;
        tvBusinessName.setText(businessPost.getBusinessName());

        // select date
        btnPickDate = binding.btnPickDate;
        tvChosenDate = binding.tvChosenDate;
        btnPickDate.setOnClickListener(view -> {
            Calendar calendar = Calendar.getInstance();
            int YEAR = calendar.get(Calendar.YEAR);
            int MONTH = calendar.get(Calendar.MONTH);
            int DATE = calendar.get(Calendar.DATE);

            DatePickerDialog datePickerDialog = new DatePickerDialog(CreateAppointmentActivity.this, (datePicker, year, month, date) -> {
                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.YEAR, year);
                calendar1.set(Calendar.MONTH, month);
                calendar1.set(Calendar.DATE, date);
                String dateText = DateFormat.format("EEEE, MMM d, yyyy", calendar1).toString();

                tvChosenDate.setText(dateText);
            }, YEAR, MONTH, DATE);

            datePickerDialog.show();            });

        // select time
        btnPickTime = binding.btnPickTime;
        tvChosenTime = binding.tvChosenTime;
        btnPickTime.setOnClickListener(view -> {
            Calendar calendar = Calendar.getInstance();
            int HOUR = calendar.get(Calendar.HOUR);
            int MINUTE = calendar.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(CreateAppointmentActivity.this, (timePicker, hour, minute) -> {
                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.HOUR, hour);
                calendar1.set(Calendar.MINUTE, minute);
                String dateText = DateFormat.format("h:mm a", calendar1).toString();
                tvChosenTime.setText(dateText);
            }, HOUR, MINUTE, false);

            timePickerDialog.show();
        });

        // show selected date and time
        String chosenDateTime = tvChosenDate.getText().toString() + " @ " + tvChosenTime.getText().toString();
//       tvApptConfirmation.setText(chosenDateTime);

        // schedule it and go back to MainActivity
        btnScheduleAppt = binding.btnScheduleAppt;
        btnScheduleAppt.setOnClickListener(e -> createAppointment(businessID));
    }


    private void createAppointment(String businessID) {
        AppointmentPost post = new AppointmentPost();
        post.setAppointmentStatus(true);
        post.setAppointmentTime(tvChosenTime.getText().toString());
        post.setAppointmentDetails("No extra information provided");
        post.setAppointmentDate(tvChosenDate.getText().toString());
        post.setBusinessId(ParseObject.createWithoutData(  "BusinessProfile", businessID));
        post.setUserId(ParseUser.getCurrentUser());
        post.setAppointmentIsReschedule(false);

        post.saveInBackground(e -> {
       if(e == null){
           Toast.makeText(CreateAppointmentActivity.this, " Created ", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(CreateAppointmentActivity.this, MainActivity.class);
            startActivity(i);
       }else{
            Log.e(TAG, "ERROR " + e);
       }
        });
    }

}
