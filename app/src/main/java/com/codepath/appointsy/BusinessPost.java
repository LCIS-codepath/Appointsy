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
    public static final String KEY_BUSINESS_PHONE_NUMBER = "phoneNumber";
    public static final String KEY_BUSINESS_TYPE = "businessType";
    public static final String KEY_BUSINESS_IMAGE = "businessImage";
    public static final String KEY_BUSINESS_EMAIL = "email";
    public static final String KEY_BUSINESS_OWNER_NAME = "ownerName";
    public static final String KEY_BUSINESS_BIO = "bio";
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

    public String getBusinessPhoneNumber(){
        return  getString(KEY_BUSINESS_PHONE_NUMBER);
    }

    public void setBusinessPhoneNumber(String phoneNumber){
        put(KEY_BUSINESS_PHONE_NUMBER, phoneNumber);
    }

    public String getBusinessType(){
        return  getString(KEY_BUSINESS_TYPE);
    }

    public void setBusinessType(String businessType){
        put(KEY_BUSINESS_TYPE, businessType);
    }

    public ParseFile getBusinessImage(){
        return getParseFile(KEY_BUSINESS_IMAGE);
    }

    public void setBusinessImage(ParseFile parseFile){
         put(KEY_BUSINESS_IMAGE, parseFile);
    }

    public String getBusinessEmail(){
        return  getString(KEY_BUSINESS_EMAIL);
    }

    public void setBusinessEmail(String email){
        put(KEY_BUSINESS_EMAIL, email);
    }

    public String getBusinessOwner(){
        return  getString(KEY_BUSINESS_OWNER_NAME);
    }

    public void setBusinessOwner(String owner){
        put(KEY_BUSINESS_OWNER_NAME, owner);
    }

    public String getBusinessBio(){
        return  getString(KEY_BUSINESS_BIO);
    }

    public void setBusinessBio(String bio){
        put(KEY_BUSINESS_BIO, bio);
    }

    public ParseUser getUserId(){
        return  getParseUser(KEY_Business_OBJECT_ID);
    }

    public void setUserId(ParseUser status){
        put(KEY_Business_OBJECT_ID, status);
    }
}
