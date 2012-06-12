package com.rapidsmsapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class messagemenu extends Activity 

{
    /** Called when the activity is first created. */
	Button btnBackH,btnInbox,btnMessage;
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messagemenu);
        
        btnBackH = 		(Button)findViewById(R.id.BtnBackhome);
        btnInbox = 		(Button)findViewById(R.id.btnViewInbox);
        btnMessage=		(Button)findViewById(R.id.btnCreateSms);
        
        btnBackH.setOnClickListener(new View.OnClickListener() {
    		
    		public void onClick(View v) 
    		{
    			// TODO Auto-generated method stub
    			 
    			 Intent i = new Intent(messagemenu.this,RapidSMSAppActivity.class); //calling a class forms
    			 messagemenu.this.finish();// close the form class 
    			 startActivity(i);
    		}
    	});
        
        btnMessage.setOnClickListener(new View.OnClickListener() {
    		
    		public void onClick(View v) 
    		{
    			// TODO Auto-generated method stub
    			 
    			 Intent i = new Intent(messagemenu.this,sendsms.class); //calling a class forms
    			 messagemenu.this.finish();// close the form class 
    			 startActivity(i);
    		}
    	});
        
        btnInbox.setOnClickListener(new View.OnClickListener() {
    		
    		public void onClick(View v) 
    		{
    			// TODO Auto-generated method stub
    			 
    			 Intent i = new Intent(messagemenu.this,InboxActivity.class); //calling a class forms
    			 messagemenu.this.finish();// close the form class 
    			 startActivity(i);
    		}
    	});
    }
}