package com.rapidsmsapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class addcontacts extends Activity 

{
    /** Called when the activity is first created. */
	Button btnBackView;
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcontacts);
        btnBackView = (Button)findViewById(R.id.btnBackVw);
        
        btnBackView.setOnClickListener(new View.OnClickListener() {
    		
    		
    		public void onClick(View v) 
    		{
    			// TODO Auto-generated method stub
    			 
    			 Intent i = new Intent(addcontacts.this,contactmenu.class); //calling a class forms
    			 addcontacts.this.finish();// close the form class 
    			 startActivity(i);
    			
    		}
    	});
    }
}