package com.example.app1;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager.NameNotFoundException;
import android.widget.TextView;

public class MainActivity extends Activity 
{

	TextView tv1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		savePreferences("a", "dfdf");
		
		tv1 = (TextView)findViewById(R.id.tv1);
		
		tv1.setText("value:"+loadSavedPreferences("a"));
		
		//sendDataAnotherApp("Ridwan");
		//startAnotherApplication("com.example.app2");
		
		
	}
	
	public void startAnotherApplication(String appPackageName)
	{
		Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(appPackageName);
		startActivity(LaunchIntent);
	}
	
	public void sendDataAnotherApp(String msg)
	{
		SharedPreferences prefs = getApplicationContext().
				getSharedPreferences("sharedprefone", Context.MODE_WORLD_READABLE);
				Editor edit = prefs.edit();
//				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//				Date date = new Date();	//
				edit.putString("time", msg);
				edit.commit();	
	}

	public void receiveDataFromAnotherApp()
	{
		Context myContext;
		try 
		{
			myContext = getApplicationContext().createPackageContext
					("com.example.app1", Context.MODE_WORLD_WRITEABLE);
			
			SharedPreferences testPrefs = myContext.getSharedPreferences
					("sharedprefone", Context.MODE_WORLD_READABLE);
					String prefString = testPrefs.getString("time", "");
		}  
		catch (NameNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void savePreferences(String key, String value) 
	{
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(this);
		Editor editor = sharedPreferences.edit();
		editor.putString(key, value);
		editor.commit();
	}
	
	public String loadSavedPreferences(String key) 
	{
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(this);
		String name = sharedPreferences.getString(key, "");
		
		return name;
	}
}
