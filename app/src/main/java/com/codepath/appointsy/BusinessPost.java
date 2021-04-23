package com.codepath.appointsy;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("BusinessProfile")
public class BusinessPost extends ParseObject {
    public static final String KEY_BUSINESS_NAME = "businessName";
    public static final String KEY_SERVICE_PRICE = "servicePrice";
    public static final String KEY_Business_LOCATION = "location";
    public static final String KEY_BUSINESS_TYPE = "businessType";
    public static final String KEY_BUSINESS_OWNER_NAME = "ownerName";
    public static final String KEY_BUSINESS_HOURS = "businessHours";

    public static final String KEY_Business_OBJECT_ID = "objectID";

    public String getBusinessName(){
        return  getString(KEY_BUSINESS_NAME);
    }

    public void setBusinessName(String businessName){
        put(KEY_BUSINESS_NAME, businessName);
    }

    public Number getServicePrice(){
        return  getNumber(KEY_BUSINESS_NAME);
    }

    public void setServicePrice(Number servicePrice){
        put(KEY_SERVICE_PRICE, servicePrice);
    }

    public String getBusinessLocation(){
        return  getString(KEY_Business_LOCATION);
    }

    public void setBusinessLocation(String location){
        put(KEY_Business_LOCATION, location);
    }

    // have to look into ready array for business hours
    public String getBusinessPhoneNumber(){
        return  getString(KEY_BUSINESS_HOURS);
    }

    public void setBusinessHours(String phoneNumber){
        put(KEY_BUSINESS_HOURS, phoneNumber);
    }

    public String getBusinessType(){
        return  getString(KEY_BUSINESS_TYPE);
    }

    public void setBusinessType(String businessType){
        put(KEY_BUSINESS_TYPE, businessType);
    }

    public String getBusinessOwner(){
        return  getString(KEY_BUSINESS_OWNER_NAME);
    }

    public void setBusinessOwner(String owner){
        put(KEY_BUSINESS_OWNER_NAME, owner);
    }


    public ParseUser getUserId(){
        return  getParseUser(KEY_Business_OBJECT_ID);
    }

    public void setUserId(ParseUser status){
        put(KEY_Business_OBJECT_ID, status);
    }
}
