package com.example.vercettisera;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
public class DetailsProvider extends Activity {
    final String EMAIL_KEY = "EMAIL_KEY";
    final String USERNAME_KEY = "USERNAME_KEY";
    final String PHONE_KEY = "PHONE_KEY";
    final String PASSWORD_KEY = "PASSWORD_KEY";

    SharedPreferences sharedPreferences;
    public User getDetails(Context context){
        sharedPreferences = context.getSharedPreferences("SHARED_PREFS",MODE_PRIVATE);
        String name = sharedPreferences.getString(USERNAME_KEY,"0");
        String phone = sharedPreferences.getString(PHONE_KEY,"0");
        String email = sharedPreferences.getString(EMAIL_KEY,"0");
        String password = sharedPreferences.getString(PASSWORD_KEY,"0");
        User currentUser = new User(email,password,phone,name);
        return currentUser;
    }
    public void saveDetails(User user,Context context){
        sharedPreferences = context.getSharedPreferences("SHARED_PREFS",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EMAIL_KEY,user.email);
        editor.putString(USERNAME_KEY,user.name);
        editor.putString(PHONE_KEY,user.phone);
        editor.putString(PASSWORD_KEY,user.password);
        editor.apply();
    }

}
