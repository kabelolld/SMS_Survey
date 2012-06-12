package com.rapidsmsapp;

import java.util.StringTokenizer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class viewData extends Activity
 {
    /** Called when the activity is first created. */
   
	Button backtoForm;
	TextView formNames;
	String data,StrData[],Tbname,Fname,Action,Option,toastMsg;
	StringTokenizer stArr;
	ListView listView;
	database dt;
	views view;
	Bundle extras;
	
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
		    super.onCreate(savedInstanceState);
	        setContentView(R.layout.viewdata);
	        
	        backtoForm = (Button)findViewById(R.id.btnBckVwData); 
	       
	        listView = (ListView) findViewById(R.id.LstVwIdVwData);
	        
	        listView.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT,10));
	        dt = new database();
	        
	        try
	        {
	        	data = "";
	            extras = getIntent().getExtras();
	          
	            if(extras != null)
	            {
	            	data = extras.getString("strTemp");        	
	            	data = data.substring(0, data.indexOf("@"));	   		 
	            	StrData = data.split("#");
	            	listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, StrData));	 
	            	registerForContextMenu(listView);
	            }
	        }
	        catch(Exception e)
	        {
	        	Toast.makeText(viewData.this, "Couldn't connect to the database. Check if data is enabled.", 5000).show();
	        }
	        
	      	       
	        backtoForm.setOnClickListener(new View.OnClickListener() 
	        {
	    		
	    		public void onClick(View v) 
	    		{
	    			
	    			 
	    			Intent i = new Intent(viewData.this,views.class); //calling a class forms
	    			viewData.this.finish();// close the form class 
	    			startActivity(i);
	    			
	    		}
	    	});
    }
	
 }