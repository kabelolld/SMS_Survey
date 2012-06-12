package com.rapidsmsapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class data extends Activity 

{
    /** Called when the activity is first created. */
	Button btnBackView,btnResult;
	EditText edtTxtTblName,edtTxtNumData;
	AlertDialog confirm;
	String TblName,NumData;
	Intent i;
	int counter = 0;
	database dt;
	views views;
	boolean validate,Tbl,Fld;
	
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data);
        btnBackView = (Button)findViewById(R.id.btnBckData);
        btnResult = (Button)findViewById(R.id.btnResultData);
        edtTxtNumData = (EditText)findViewById(R.id.edtTxtDataNum);
        edtTxtTblName = (EditText)findViewById(R.id.edtTxtDataTbl);
        confirm = new AlertDialog.Builder(this).create();
        dt = new database();
        views = new views();
                        
        btnBackView.setOnClickListener(new View.OnClickListener() {
    		
    		
    		public void onClick(View v) 
    		{
    			
    			 
    			 Intent i = new Intent(data.this,views.class); //calling a class forms
    			 data.this.finish();// close the form class 
    			 startActivity(i);
    			
    		}
    	});
        
        btnResult.setOnClickListener(new View.OnClickListener() {
    		
    		
    		public void onClick(View v) 
    		{
    			if(!validateTxtFields())
				{
					TblName = edtTxtTblName.getText().toString();
					
    				try
					{
						String temp = dt.getForms(TblName, "temp", "8", "1");
						i = new Intent(data.this,viewData.class);
						Bundle extras = new Bundle();
						extras.putString("strTemp",temp);
						i.putExtras(extras);
						startActivityForResult(i, 1);
					}
					catch(Exception e)
					{
						
					}
				}
    		}
    	});
        
        edtTxtNumData.addTextChangedListener(new TextWatcher(){         
       	 public void onTextChanged(CharSequence s, int start, int before, int count){
       		 
       		
       			boolean test = false;
       			if((edtTxtNumData.getText().toString().compareTo("") != 0) && !validateTxtFields())
       			{
       				try
       				{
       					counter = Integer.parseInt(edtTxtNumData.getText().toString());
       					test = true;
       				}
       				catch(Exception e)
       				{
       					confirm.setTitle("Warning: Wrong Entry");
       					confirm.setMessage("Please enter numbers only");
       					confirm.setButton("Ok", new DialogInterface.OnClickListener() {
     		        	      public void onClick(DialogInterface dialog, int which) {
     		        	    	  
     		        	    	 confirm.dismiss();
     		        	    } }); 
       					confirm.show();
       				}
       			}
       			if(test)
       			{
       				
       			}
       	 }
       	public void afterTextChanged(Editable s) {}

		public void beforeTextChanged(CharSequence s, int start,
				int count, int after) {}     
    	 });  
    }		
       	 

	boolean validateTxtFields()
	{
		validate = false;
		
		if(edtTxtTblName.getText().toString().length() < 4||edtTxtTblName.getText().toString().compareTo("") == 0||edtTxtTblName.getText().toString() == null)
		{	
			confirm.setMessage("Please enter table name. \nLength should be greater than 3 characters");
			validate = true;
		}		
		if(validate)
		{
			confirm.setTitle("Incomplete Entry");
			confirm.setButton("Ok", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
    	  
					confirm.dismiss();
				} }); 
			confirm.show();
		}
		return validate;
	}
}