package com.example.servicepractice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }

    MyBinder binder=new MyBinder();

    class MyBinder extends Binder
    {
        public void execute()
        {
            Log.i("AAA","execute()");
        }

    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
       return  binder;
    }

    @Override
    public void onCreate()
    {
        Log.i("AAA","onCreate");
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent,int flags,int startId)
    {
        Log.i("AAA", "onStartCommand");
        return super.onStartCommand(intent,flags,startId);
    }

    @Override
    public void onDestroy()
    {
        Log.i("AAA","onDestroy");
        super.onDestroy();

    }

}
