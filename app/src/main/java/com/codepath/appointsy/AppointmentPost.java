package com.codepath.appointsy;

import androidx.appcompat.widget.AppCompatSeekBar;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Appointment")
public class AppointmentPost extends ParseObject {
    public static final String KEY_APPOINTMENT_DETAILS = "details";
    public static final String KEY_APPOINTMENT_TIME = "time";
    public static final String KEY_APPOINTMENT_DATE = "date";
    public static final String KEY_APPOINTMENT_STATUS = "status";
    public static final String KEY_APPOINTMENT_IS_RESCHEDULE = "isReschedule";
    public static final String KEY_BUSINESS_ID = "businessObjectID";
    public static final String KEY_USER_OBJECT_ID = "userObjectID";

    // to read data access the User id then call the profileImage
    public static final String KEY_BUSINESS_IMAGE = "profileImage";
    // to read data access the Business id then call businessName
    public static final String KEY_BUSINESS_NAME = "businessName";
    public  ParseFile businessImage;



    // Set and Getters for Items need for the Appointment
    //Details
    public String getAppointmentDetails(){
        return  getString(KEY_APPOINTMENT_DETAILS);
    }

    public void setAppointmentDetails(String details){
        put(KEY_APPOINTMENT_DETAILS, details);
    }

    // Time
    public String getAppointmentTime(){
        return  getString(KEY_APPOINTMENT_TIME);
    }

    public void setAppointmentTime(String time){
        put(KEY_APPOINTMENT_TIME, time);
    }

    //date
    public String getAppointmentDate(){
        return getString(KEY_APPOINTMENT_DATE);
    }

    public void setAppointmentDate(String date){
        put(KEY_APPOINTMENT_DATE, date);
    }

    //Status
    public Boolean getAppointmentStatus(){
        return  getBoolean(KEY_APPOINTMENT_STATUS);
    }

    public void setAppointmentStatus(Boolean status){
        put(KEY_APPOINTMENT_STATUS, status);
    }

    //isReschedule
    public Boolean getAppointmentIsReschedule(){
        return  getBoolean(KEY_APPOINTMENT_IS_RESCHEDULE);
    }

    public void setAppointmentIsReschedule(String status){
        put(KEY_APPOINTMENT_IS_RESCHEDULE, status);
    }

    //UserId
    public ParseUser getUserId(){
        return  getParseUser(KEY_USER_OBJECT_ID);
    }

    public void setUserId(ParseUser status){
        put(KEY_USER_OBJECT_ID, status);
    }

    //BusinessID
    public ParseObject getBusinessId(){
        return  getParseObject(KEY_BUSINESS_ID);
    }

    public void setBusinessId(ParseObject status){
        put(KEY_BUSINESS_ID, status);
    }



    // Items from Business needed for the Appointment
    // retrieve the object ID from the business table then get the specified query
    public void setAppointmentBusinessName(String status){
        put(KEY_BUSINESS_NAME, status);
    }



    public String getAppointmentBusinessName(){
        return  getString(KEY_BUSINESS_NAME);
    }

    public void setAppointmentBusinessImage(ParseFile status){
        put(KEY_BUSINESS_IMAGE, status);
    }

    public ParseFile getAppointmentBusinessImage(){

        return getParseFile(KEY_BUSINESS_IMAGE);
    }


}
