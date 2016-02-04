package com.example.androidpractice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.os.Vibrator;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TimerActivity extends Activity
{
	private static String CLASS_NAME;
	private static final long UPDATE_EVERY=200;
	protected TextView counter;// count
	protected Button start;
	protected Button stop;
	protected boolean timerRunning;
	protected long startedAt;
	protected long lastStopped;
	
	protected Handler handler;
	protected UpdateTimer updateTimer;
	
	protected Vibrator vibrate;
	protected long lastSeconds;//set only vibrate once per second;
	public  TimerActivity()
	{
		// TODO Auto-generated constructor stub
		CLASS_NAME=getClass().getName();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Log.i(CLASS_NAME, "Oncreat");
		setContentView(R.layout.activity_timer);
		//apply strict mode detect all thread and memory leakage ,使用Strict模式,检查所有线程安全与内存泄漏问题
		if(BuildConfig.DEBUG)
		{
			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
			StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
		}
		counter=(TextView)findViewById(R.id.timer);
		start=(Button)findViewById(R.id.start_button);
		stop=(Button)findViewById(R.id.stop_button);
		
	}

	public void clickedStart(View view)
	{
		Log.i(CLASS_NAME, "Start");
		timerRunning=true;
		enableButtons();
		startedAt=System.currentTimeMillis();//get current time   unit ms
		handler=new Handler();
		updateTimer=new UpdateTimer();
		handler.postDelayed(updateTimer, UPDATE_EVERY);
	}
	public void clickedStop(View view)
	{
		Log.i(CLASS_NAME, "Stop");
		timerRunning=false;
		enableButtons();
		lastStopped=System.currentTimeMillis();
		if(updateTimer!=null&&handler!=null) // for the first time
		{
		handler.removeCallbacks(updateTimer); 
		handler=null;
		}
	}
	
	public void clickedSettings(View view)
	{
		Intent settingsIntent=new Intent(getApplicationContext(),SettingsActivity.class);
		startActivity(settingsIntent);
	}
	protected void enableButtons()
	{
		Log.i(CLASS_NAME, "set buttons enabled/disenabled.");
		start.setEnabled(!timerRunning);
		stop.setEnabled(timerRunning);
	}
	
	class UpdateTimer implements Runnable
	{
		public void run()
		{
//			Log.i(CLASS_NAME, "run");
			setTimeDisplay();
			
			vibrateCheck();
			
			if(handler!=null)
			{
				handler.postDelayed(this, UPDATE_EVERY);
			}
		}
		
	}
	
	
	protected void setTimeDisplay()
	{
		String display;
		long timeNow;
		long diff;
		long seconds;
		long minutes;
		long hours;
		
//		Log.i(CLASS_NAME, "Setting time display");
		if(timerRunning)
		{
			timeNow=System.currentTimeMillis();
		}else{
			timeNow=lastStopped;
		}
		diff=timeNow-startedAt;
		//no negative time
		if(diff<0)
		{
			diff=0;
		}
		
		seconds=diff/1000;
		minutes=seconds/60;
		hours=minutes/60;
		seconds=seconds%60;
		minutes=minutes%60;
		
		display=String.format("%d", hours)+":"+String.format("%02d", minutes)+":"+String.format("%02d", seconds);
		counter.setText(display);
	}
	
	protected void vibrateCheck()
	{
		
		long timeNow=System.currentTimeMillis();
		long diff=timeNow-startedAt;
		long seconds=diff/1000;
		long minutes=seconds/60;
		
		
		
//		Log.i(CLASS_NAME, "vibrateCheck");
		
		if(vibrate!=null&&seconds!=lastSeconds)
		{
			long[] once={0,100};
			
			if(seconds%15==0)
			{
				Log.i(CLASS_NAME, "vibrate once");
				vibrate.vibrate(once,-1);
			}
		}
		lastSeconds=seconds;
	}
	
	
	@Override
	public void onStart()
	{
		super.onStart();
		Log.i(CLASS_NAME, "onStart");
		
		vibrate=(Vibrator)getSystemService(VIBRATOR_SERVICE);
		if(vibrate==null)
			Log.w(CLASS_NAME, "No vibration service exists");
		
		if(timerRunning)
		{
			handler=new Handler();
			updateTimer=new UpdateTimer();
			handler.postDelayed(updateTimer, UPDATE_EVERY);
		}
		
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
		Log.i(CLASS_NAME, "onResume");
		enableButtons();
		setTimeDisplay();
	}
	
	@Override
	public void onPause()
	{
		super.onPause();
		Log.i(CLASS_NAME, "onPause");
	}
	
	@Override
	public void onStop()
	{
		super.onStop();
		Log.i(CLASS_NAME, "onStop");
		if(updateTimer!=null&&handler!=null)//for the first time launch
		{
		handler.removeCallbacks(updateTimer);
		updateTimer=null;
		handler=null;
		}
	}

	@Override
	public void onRestart()
	{
		super.onRestart();
		Log.i(CLASS_NAME, "onRestart");
	}
	

	
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		Log.i(CLASS_NAME, "onDestroy");
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
