package com.codepath.appointsy;

import androidx.appcompat.widget.AppCompatSeekBar;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Appointment")
public class AppointmentPost extends ParseObject {
    public static final String KEY_APPOINTMENT_DETAILS = "details";
    public static final String KEY_APPOINTMENT_TIME = "time";
    public static final String KEY_APPOINTMENT_STATUS = "status";
    public static final String KEY_APPOINTMENT_IS_RESCHEDULE = "isReschedule";
    public static final String KEY_BUSINESS_ID = "businessObjectID";
    public static final String KEY_USER_OBJECT_ID = "userObjectID";

    public String getAppointmentDetails(){
        return  getString(KEY_APPOINTMENT_DETAILS);
    }

    public void setAppointmentDetails(String details){
        put(KEY_APPOINTMENT_DETAILS, details);
    }

    public String getAppointmentTime(){
        return  getString(KEY_APPOINTMENT_TIME);
    }

    public void setAppointmentTime(String time){
        put(KEY_APPOINTMENT_TIME, time);
    }

    public String getAppointmentStatus(){
        return  getString(KEY_APPOINTMENT_STATUS);
    }

    public void setAppointmentStatus(String status){
        put(KEY_APPOINTMENT_STATUS, status);
    }

    public String getAppointmentIsReschedule(){
        return  getString(KEY_APPOINTMENT_IS_RESCHEDULE);
    }

    public void setAppointmentIsReschedule(String status){
        put(KEY_APPOINTMENT_IS_RESCHEDULE, status);
    }

    public String getBusinessId(){
        return  getString(KEY_BUSINESS_ID);
    }

    public void setBusinessId(String status){
        put(KEY_BUSINESS_ID, status);
    }

    public String getUserId(){
        return  getString(KEY_USER_OBJECT_ID);
    }

    public void setUserId(String status){
        put(KEY_USER_OBJECT_ID, status);
    }




}
