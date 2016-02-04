package com.example.androidpractice;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Settings extends Application
{
	private static String CLASS_NAME;
	protected boolean vibrateOn;
	private static String VIBRATE="vibrate";
	
	
	 public Settings()
	{
		// TODO Auto-generated constructor stub
		 CLASS_NAME=getClass().getName();
	}
	 
	 public boolean isVibrateOn(Activity activity)
	 {
		 
		 SharedPreferences preferences=activity.getPreferences( Activity.MODE_PRIVATE);
		 if(preferences.contains(VIBRATE))
		 {
			 vibrateOn=preferences.getBoolean(VIBRATE, false);
		 }
		 return vibrateOn;
	 }
	 public void setVibrate(Activity activity,boolean vibrate)
	 {
		 SharedPreferences preferences=activity.getPreferences( Activity.MODE_PRIVATE);
		 Editor editor=preferences.edit();
		 editor.putBoolean(VIBRATE, vibrate);
		 editor.apply();
		 vibrateOn=vibrate;
	 }
}
