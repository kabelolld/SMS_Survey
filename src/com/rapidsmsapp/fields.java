package com.rapidsmsapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class fields extends Activity 

{
    /** Called when the activity is first created. */
	Button btnBackView,btnResult;
	EditText edtTop,edtBottom;
	TextView txtVw;
	String FldName, TblName;
	views views;
	database dt;
	LinearLayout linHide;
	Intent i;
	AlertDialog confirm;
	boolean validate;
	
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fields);
        btnBackView = (Button)findViewById(R.id.btnFldBck);
        btnResult = (Button)findViewById(R.id.btnFldRslts);
        edtTop = (EditText)findViewById(R.id.edtTxtFldTblName);
        edtBottom = (EditText)findViewById(R.id.edtTxtDltField);
        linHide = (LinearLayout)findViewById(R.id.linLayHideFld);
        txtVw = (TextView)findViewById(R.id.txtVwDlt);
        confirm = new AlertDialog.Builder(this).create();
        views = new views();
        dt = new database();
        edtBottom.setText("");
        btnBackView.setOnClickListener(new View.OnClickListener() {
    		
    		
    		public void onClick(View v) 
    		{
    			if(views.Table)
    			{
    				i = new Intent(fields.this,viewForms.class); //calling a class forms
    				fields.this.finish();// close the form class 
    			}
    			if(views.Field)
    			{
    				i = new Intent(fields.this,views.class); //calling a class forms
    				fields.this.finish();// close the form class 
    				
    			}
    			startActivity(i);
    		}
    	});
        
        btnResult.setOnClickListener(new View.OnClickListener() {
    		
    		
    		public void onClick(View v) 
    		{
    			FldName = edtBottom.getText().toString();
    			TblName = edtTop.getText().toString();
    			if(views.Table)
    			{
    				if(!validateField())
    				{
    					confirm.setMessage("Do you wish to delete the field("+FldName.toUpperCase()+") from the database?");				
    					confirm.setTitle("Delete "+FldName);
    					confirm.setButton("Yes", new DialogInterface.OnClickListener() {
    						public void onClick(DialogInterface dialog, int which) {
	    	  
    							try
    							{								
    								
    									String temp = dt.delete(TblName, FldName, "5", FldName);
    									i = new Intent(fields.this,viewFields.class);
    									Bundle extras = new Bundle();
    									extras.putString("strTemp",temp);
    									i.putExtras(extras);
    									startActivityForResult(i, 1);
    							}
    							catch(Exception e)
    							{
    								Toast.makeText(fields.this, "Failed to delete ("+ FldName.toUpperCase()+")", Toast.LENGTH_LONG).show();
    							}
						
    						} }); 
    					confirm.setButton2("Cancel", new DialogInterface.OnClickListener() {
    						public void onClick(DialogInterface dialog, int which) {
	    	  
    							confirm.dismiss();
    						} }); 
    					confirm.show();
    				}
    			}
    			if(views.Field)
				{
    				if(!validateField())
    				{
    					try
    					{
    						String temp = dt.getForms(TblName, FldName, "8", FldName);
							i = new Intent(fields.this,viewFields.class);
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
    		}
    	});
        
        String defaultName="";
        Bundle extras = getIntent().getExtras();
       
        if(extras != null)
        {
        	defaultName = extras.getString("TblName");
        	edtTop.setText(defaultName);
        }
        
        if(views.Table)
        {
        	txtVw.setText("Delete Field");
        }
        if(views.Field)
        {
        	txtVw.setText("View Fields");
        	linHide.setVisibility(View.GONE);
        }
    }
	boolean validateField()
	{
		validate = false;
		if(TblName.length() < 4||TblName.compareTo("") == 0||TblName == null)
		{	
			confirm.setMessage("Please enter table name. \nLength should be greater than 3 characters");
			validate = true;
		}
		if((FldName.length() < 2 ||FldName.compareTo("") == 0||FldName == null)&& ((!validate)&&(!views.Field)))
		{
			confirm.setMessage("Please enter field name.\nLength should be greater than 3 characters");
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