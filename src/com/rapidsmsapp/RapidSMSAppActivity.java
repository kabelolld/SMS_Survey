package com.rapidsmsapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RapidSMSAppActivity extends Activity {
    /** Called when the activity is first created. */
   
	Button btnform,btnContact,btnmsg,btnstats;
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        btnform = 		(Button)findViewById(R.id.btnForms); // chose the btnform you want to use by its ID
        btnContact = 	(Button)findViewById(R.id.btnContacts);//
        btnmsg = 		(Button)findViewById(R.id.btnMessages);
        btnstats =		(Button)findViewById(R.id.btnStats);
        
        btnform.setOnClickListener(new View.OnClickListener() {
    		
    		public void onClick(View v) 
    		{
    			 Intent i = new Intent(RapidSMSAppActivity.this,forms.class); //calling a class forms
    			 //RapidSMSAppActivity.this.finish();// close the form class 
    			 startActivity(i);
    			
    		}
    	});
        btnContact.setOnClickListener(new View.OnClickListener() {
    		
    		
    		public void onClick(View v) 
    		{
    			
    			 
    			 Intent cnt = new Intent(RapidSMSAppActivity.this,contactmenu.class); //calling a class forms
    			 //RapidSMSAppActivity.this.finish();// close the form class 
    			 startActivity(cnt);
    			
    		}
    	});
        
        btnmsg.setOnClickListener(new View.OnClickListener() {
    		
    		public void onClick(View v) 
    		{
    			
    			 
    			 Intent i = new Intent(RapidSMSAppActivity.this,messagemenu.class); //calling a class forms
    			 //RapidSMSAppActivity.this.finish();// close the form class 
    			 startActivity(i);
    			
    		}
    	});
        
        btnstats.setOnClickListener(new View.OnClickListener() {
    		
    		public void onClick(View v) 
    		{
    			
    			
    			 Intent i = new Intent(RapidSMSAppActivity.this,stats.class); //calling a class forms
    			 //RapidSMSAppActivity.this.finish();// close the form class 
    			 startActivity(i);
    			
    		}
    	});
       
    }
  

}
