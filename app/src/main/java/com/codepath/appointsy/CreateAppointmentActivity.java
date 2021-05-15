package com.codepath.appointsy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

import com.codepath.appointsy.databinding.ActivityCreateAppointmentBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class CreateAppointmentActivity extends AppCompatActivity {
    private ActivityCreateAppointmentBinding binding;

    private MaterialTextView tvBusinessName;
    private CalendarView calendarView;
    private MaterialTextView tvSelectedDate;
    private MaterialTextView tvApptConfirmation;
    private MaterialButton btnScheduleAppt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_appointment);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_appointment);

        Bundle bundle = getIntent().getExtras();
        BusinessPost businessPost = bundle.getParcelable("businessParseObject");

        tvBusinessName = binding.tvBusinessName;
        tvBusinessName.setText(businessPost.getBusinessName());

        calendarView = (CalendarView) findViewById(R.id.calendarView);
        tvSelectedDate = (MaterialTextView) findViewById(R.id.tvApptConfirmation);

        tvApptConfirmation.setText();

        btnScheduleAppt = binding.btnScheduleAppt;
        btnScheduleAppt.setOnClickListener(e -> {
            // create new appointment
            ParseObject entity = new ParseObject("Appointment");

            entity.put("status", true);
            entity.put("time", );
            entity.put("details", "A string");
            entity.put("isReschedule", true);
            entity.put("userObjectID", ParseUser.getCurrentUser());
            entity.put("date", );
            entity.put("businessObjectID", new ParseObject("BusinessProfile"));

            // Saves the new object.
            entity.saveInBackground(a -> {
                if (a == null) {
                    //Save was done
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
//
//    Button dateButton, timeButton;
//    TextView dateTextView, timeTextView;
//
//        dateButton = findViewById(R.id.dateButton);
//        timeButton = findViewById(R.id.timeButton);
//        dateTextView = findViewById(R.id.dateTextView);
//        timeTextView = findViewById(R.id.timeTextView);
//
//        dateButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                handleDateButton();
//            }
//        });
//        timeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                handleTimeButton();
//            }
//        });
//
//    }
//
//    private void handleDateButton() {
//        Calendar calendar = Calendar.getInstance();
//        int YEAR = calendar.get(Calendar.YEAR);
//        int MONTH = calendar.get(Calendar.MONTH);
//        int DATE = calendar.get(Calendar.DATE);
//
//        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker datePicker, int year, int month, int date) {
//
//                Calendar calendar1 = Calendar.getInstance();
//                calendar1.set(Calendar.YEAR, year);
//                calendar1.set(Calendar.MONTH, month);
//                calendar1.set(Calendar.DATE, date);
//                String dateText = DateFormat.format("EEEE, MMM d, yyyy", calendar1).toString();
//
//                dateTextView.setText(dateText);
//            }
//        }, YEAR, MONTH, DATE);
//
//        datePickerDialog.show();
//
//
//
//
//    }
//
//    private void handleTimeButton() {
//        Calendar calendar = Calendar.getInstance();
//        int HOUR = calendar.get(Calendar.HOUR);
//        int MINUTE = calendar.get(Calendar.MINUTE);
//        boolean is24HourFormat = DateFormat.is24HourFormat(this);
//
//        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
//            @Override
//            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
//                Log.i(TAG, "onTimeSet: " + hour + minute);
//                Calendar calendar1 = Calendar.getInstance();
//                calendar1.set(Calendar.HOUR, hour);
//                calendar1.set(Calendar.MINUTE, minute);
//                String dateText = DateFormat.format("h:mm a", calendar1).toString();
//                timeTextView.setText(dateText);
//            }
//        }, HOUR, MINUTE, is24HourFormat);
//
//        timePickerDialog.show();
//
//    }