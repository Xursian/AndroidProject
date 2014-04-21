package com.example.finalandroidapp;

import com.parse.Parse;
import com.parse.ParseACL;
 
import com.parse.ParseUser;
 
import android.app.Application;
 
public class ParseApplication extends Application {
 
    @Override
    public void onCreate() {
        super.onCreate();
 
        // Add your initialization code here
        Parse.initialize(this, "zl19Tr1mCFzHQPaHFGT4Ewlj8lAMGWY1egfpsUzL", "uS5c6yGsyWBOmfAhe5eqycmrLEHNhDgkNd4cy27K");
 
        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
 
        // If you would like all objects to be private by default, remove this
        // line.
        defaultACL.setPublicReadAccess(true);
 
        ParseACL.setDefaultACL(defaultACL, true);
    }
 
}