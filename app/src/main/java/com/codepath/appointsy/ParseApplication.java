package com.codepath.appointsy;

import android.app.Application;
import com.parse.Parse;

public class ParseApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        // Initializes Parse SDK when app is created, api keys hidden
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(BuildConfig.PARSE_APP_ID)
                .clientKey(BuildConfig.PARSE_CLIENT_KEY)
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
