package com.rapidsmsapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class views extends Activity 

{
    /** Called when the activity is first created. */
	Button btnBackView,btnTblView,btnFieldsView,btnDataView;
	database viewDB;
	ListView listView;
	viewForms viewFormsObj;
	viewFields viewFieldsObj;
	String strDat,temp;
	public static boolean Table,Field,Data;
	
    public void onCreate(Bundle savedInstanceState)
    {
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.views);
        
        //viewDB = new database();
        //listView = (ListView)findViewById(R.id.ListViewId);
        
        btnBackView = (Button)findViewById(R.id.btnBckViews);
        btnTblView = (Button)findViewById(R.id.btnTblView);
        btnFieldsView = (Button)findViewById(R.id.btnFieldsView);
        btnDataView = (Button)findViewById(R.id.btnDataView);
        viewFormsObj = new viewForms();
        
        Table = false;
        Field = false;
        Data = false;
        
        btnBackView.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View v) 
    		{	 
    			 Intent i = new Intent(views.this,forms.class); //calling a class forms
    			 views.this.finish();// close the form class 
    			 startActivity(i);			
    		}
    	});
        
        btnTblView.setOnClickListener(new View.OnClickListener() {			
			public void onClick(View v) {			
				
			Table = true;
			
			gotoViewForms();
			
			}
		});
        
        btnFieldsView.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				
				Field = true;
				
			Intent i = new Intent(views.this,fields.class); //calling a class forms
			//views.this.finish();// close the form class 
			startActivity(i);
			}
		});   
        btnDataView.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				
				Data = true;
			Intent i = new Intent(views.this,data.class); //calling a class forms
			//views.this.finish();// close the form class 
			startActivity(i);
			}
		});    
    }
	
	void gotoViewForms()
	{
		
		Intent i = new Intent(views.this,viewForms.class); //calling a class forms
		//views.this.finish();// close the form class 
		startActivity(i);
		
		
	}
}