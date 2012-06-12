package com.rapidsmsapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class contactmenu extends Activity 

{
    /** Called when the activity is first created. */
	Button btnBack,btnAdd,btnView;
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts);
        
        btnBack = 		(Button)findViewById(R.id.btnBack); // chose the btnform you want to use by its ID
        btnAdd = 		(Button)findViewById(R.id.btnAddContacts);//
        btnView = 		(Button)findViewById(R.id.btnViewContacts);//
        
        btnAdd.setOnClickListener(new View.OnClickListener() {
    		
    		
    		public void onClick(View v) 
    		{
    			// TODO Auto-generated method stub
    			 
    			 Intent i = new Intent(contactmenu.this,addcontacts.class); //calling a class forms
    			 contactmenu.this.finish();// close the form class 
    			 startActivity(i);
    			
    		}
    	});
        
        
        btnBack.setOnClickListener(new View.OnClickListener() {
    		
    		
    		public void onClick(View v) 
    		 {
    			// TODO Auto-generated method stub
    			 
    			 Intent i = new Intent(contactmenu.this,RapidSMSAppActivity.class); //calling a class forms
    			 contactmenu.this.finish();// close the form class 
    			 startActivity(i);
    			
    		 }
    		});
        

    }
	
}
