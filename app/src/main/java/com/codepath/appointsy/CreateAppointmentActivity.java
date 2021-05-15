package com.codepath.appointsy;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.codepath.appointsy.databinding.ActivityCreateAppointmentBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.Calendar;

public class CreateAppointmentActivity extends AppCompatActivity {
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
        context = this.context;

        // set business name
        Bundle bundle = getIntent().getExtras();
        BusinessPost businessPost = bundle.getParcelable("businessParseObject");
        tvBusinessName = binding.tvBusinessName;
        tvBusinessName.setText(businessPost.getBusinessName());

        // select date
        btnPickDate = findViewById(R.id.btnPickDate);
        tvChosenDate = findViewById(R.id.tvChosenDate);
        btnPickDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int YEAR = calendar.get(Calendar.YEAR);
                int MONTH = calendar.get(Calendar.MONTH);
                int DATE = calendar.get(Calendar.DATE);

                DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                        Calendar calendar1 = Calendar.getInstance();
                        calendar1.set(Calendar.YEAR, year);
                        calendar1.set(Calendar.MONTH, month);
                        calendar1.set(Calendar.DATE, date);
                        String dateText = DateFormat.format("EEEE, MMM d, yyyy", calendar1).toString();

                        tvChosenDate.setText(dateText);
                    }
                }, YEAR, MONTH, DATE);

                datePickerDialog.show();            }
        });

        // select time
        btnPickTime = findViewById(R.id.btnPickTime);
        tvChosenTime = findViewById(R.id.tvChosenTime);
        btnPickTime.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int HOUR = calendar.get(Calendar.HOUR);
                int MINUTE = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        Calendar calendar1 = Calendar.getInstance();
                        calendar1.set(Calendar.HOUR, hour);
                        calendar1.set(Calendar.MINUTE, minute);
                        String dateText = DateFormat.format("h:mm a", calendar1).toString();
                        tvChosenTime.setText(dateText);
                    }
                }, HOUR, MINUTE, false);

                timePickerDialog.show();
            }
        });

        // show selected date and time
        String chosenDateTime = tvChosenDate.getText().toString() + " @ " + tvChosenTime.getText().toString();
        tvApptConfirmation.setText(chosenDateTime);

        // schedule it and go back to MainActivity
        btnScheduleAppt = binding.btnScheduleAppt;
        btnScheduleAppt.setOnClickListener(e -> {
            // create new appointment
            ParseObject entity = new ParseObject("Appointment");

            entity.put("status", true);
            entity.put("time", tvChosenTime.getText().toString());
            entity.put("details", "A string");
            entity.put("isReschedule", true);
            entity.put("userObjectID", ParseUser.getCurrentUser());
            entity.put("date", tvChosenDate.getText().toString());
            entity.put("businessObjectID", new ParseObject("BusinessProfile"));

            // Saves the new object.
            entity.saveInBackground(a -> {
                if (a == null) {
                    //Save was done
                    Toast.makeText(this, "Appointment Scheduled", Toast.LENGTH_SHORT).show();
                } else {
                    //Something went wrong
                    Toast.makeText(this, a.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            // return to MainActivity
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }
}
