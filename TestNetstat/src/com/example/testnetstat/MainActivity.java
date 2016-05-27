package com.example.testnetstat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.R.integer;
import android.app.Activity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity
{
	TextView textview;
	Button button1;
	Button button2;
	Button button3;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.i("AAA", "\0" + "AAA");
		Log.i("AAA", "" + "SAAA");
		textview = (TextView) findViewById(R.id.textview);
		textview.setMovementMethod(ScrollingMovementMethod.getInstance());
		textview.setScrollbarFadingEnabled(false);
		button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				textview.setText("");
				String[] CMD = new String[2];
				CMD[0] = "netstat";
				CMD[1] = "";
				String str = exeCMDnet(CMD);
			}
		});

		button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				textview.setText("");
				String[] CMD = new String[2];
				CMD[0] = "cat";
				CMD[1] = "/proc/net/tcp";
				String str = exeCMDcat(CMD);
			}
		});

		button3 = (Button) findViewById(R.id.button3);
		button3.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				textview.setText("");
				String[] CMD = new String[2];
				CMD[0] = "cat";
				CMD[1] = "/proc/net/tcp6";
				String str = exeCMDcat(CMD);
			}
		});

	}

	private String exeCMDnet(String[] progArray)
	{
		String result = "";
		try
		{
			Process proc = Runtime.getRuntime().exec(progArray); // 执行linux命令
			InputStreamReader instr = new InputStreamReader(proc.getInputStream()); // 返回之后命令后的数据
			BufferedReader br = new BufferedReader(instr);
			String line;
			line = br.readLine();
			while ((line = br.readLine()) != null)
			{
			
				result += line + "\n";
				// Log.i("AAA", line);
				
				textview.append(line + "\n");
				
				String[] tokens = line.split("\\s+");// delims
				
			
				
				if(tokens[0].equals(""))//判断是否为空
				{
				textview.append(tokens[1] + "\n"); 
				 textview.append(tokens[5] + "\n");
				 textview.append(tokens[6] + "\n");
				}else {
					textview.append(tokens[0] + "\n"); 
					 textview.append(tokens[4] + "\n");
					 textview.append(tokens[5] + "\n");
				}
					
				

			}
		} catch (IOException e)
		{
			e.printStackTrace();
		} catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	private String exeCMDcat(String[] progArray)
	{
		String result = "";
		try
		{
			Process proc = Runtime.getRuntime().exec(progArray); // 执行linux命令
			InputStreamReader instr = new InputStreamReader(proc.getInputStream()); // 返回之后命令后的数据
			BufferedReader br = new BufferedReader(instr);
			String line;
			line = br.readLine();
			while ((line = br.readLine()) != null)
			{
				result += line + "\n";

			}
		} catch (IOException e)
		{
			e.printStackTrace();
		} catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		textview.setText(result);
		return result;
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
