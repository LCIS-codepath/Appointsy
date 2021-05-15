package com.codepath.appointsy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.CalendarView;
import com.codepath.appointsy.databinding.ActivityCreateAppointmentBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

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

        tvBusinessName = binding.tvBusinessName;
        calendarView = (CalendarView) findViewById(R.id.calendarView);
        tvSelectedDate = (MaterialTextView) findViewById(R.id.tvApptConfirmation);
        btnScheduleAppt = binding.btnScheduleAppt;

    }
}