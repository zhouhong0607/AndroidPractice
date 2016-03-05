package com.example.servicepractice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button startButton;
    private Button stopButton;
    private Button bindButton;
    private Button unbindButton;

    private MyService.MyBinder activityBinder;

    private ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("AAA","onServiceConnected");
            activityBinder=(MyService.MyBinder)service;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i("AAA","onServiceDisconnected");
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        startButton=(Button)findViewById(R.id.start_service);
        stopButton=(Button)findViewById((R.id.stop_service));
        bindButton=(Button)findViewById(R.id.bind_service);
        unbindButton=(Button)findViewById(R.id.unbind_service);
        startButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);
        bindButton.setOnClickListener(this);
        unbindButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.start_service:
                Intent intent1=new Intent(this,MyService.class);
                startService(intent1);
                break;
            case R.id.stop_service:
                Intent intent2=new Intent(this,MyService.class);
                stopService(intent2);
                break;
            case  R.id.bind_service:
                Intent bindIntent=new Intent(this,MyService.class);
                bindService(bindIntent, connection, BIND_AUTO_CREATE);
                Log.i("AAA", "BindButton");
                break;
            case R.id.unbind_service:
                activityBinder.execute();
                unbindService(connection);
                break;
                default:
                    break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
