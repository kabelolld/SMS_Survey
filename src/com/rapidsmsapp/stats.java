package com.rapidsmsapp;

import java.util.StringTokenizer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class stats extends Activity
 {
  
	/** Called when the activity is first created. */
   
	Button backtoForm1;
	TextView formNames;
	String data,StrData[];
	StringTokenizer stArr;
	ListView listView;
	database dt;
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
		    super.onCreate(savedInstanceState);
	        setContentView(R.layout.stats);
	        
	        backtoForm1 = (Button)findViewById(R.id.btnBackStats); 
	       
	        listView = (ListView) findViewById(R.id.ListViewStats);
	          
	        dt = new database();
	        
	        try
	        {
	        	data = dt.getStats();
	        	if(data.indexOf("%") != 0)
		        {
		        	try
		        	{
		        		data = data.substring(0,data.indexOf("@"));
		        
		        		 StrData = data.split("#");
		        		  
		        		listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, StrData));	
		        	}
		        	catch(Exception e)
		        	{
		        		Toast.makeText(stats.this, "Error: Failed to connect.\n\n"+data, Toast.LENGTH_LONG).show();
		        	}
		        }
		        else
		        {
		        	Toast.makeText(stats.this, "Error: Failed to retrieve.\n\n"+data, Toast.LENGTH_LONG).show();
		        }
	        }
	        catch(Exception e)
	        {
	        	
	        }
	         
	        
	        backtoForm1.setOnClickListener(new View.OnClickListener() 
	        {
	    		
	    		public void onClick(View v) 
	    		{
	    			// TODO Auto-generated method stub
	    			 
	    			Intent i = new Intent(stats.this,RapidSMSAppActivity.class); //calling a class forms
	    			stats.this.finish();// close the form class 
	    			startActivity(i);
	    			
	    		}
	    	});
    }
 }