package com.rapidsmsapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class delete extends Activity 

{
    /** Called when the activity is first created. */
	Button btnBack,btnRslt;
	database viewDB;
	TextView txtVwTitle;
	String strDat,temp;
	LinearLayout linLayHide; 
	EditText edtTxtTbl,edtTxtFld;
	editforms edtFrmsObj;
	AlertDialog confirm;
	String name,strTemp;
	Intent i;
	boolean validate;
	//public static boolean Table,Field,Data;
	
    public void onCreate(Bundle savedInstanceState)
    {
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete);
        
        viewDB = new database();
                
        btnBack = (Button)findViewById(R.id.btnBckDlt);
        btnRslt = (Button)findViewById(R.id.btnDltRslt);
        txtVwTitle = (TextView)findViewById(R.id.txtVwDlt);
        edtTxtTbl = (EditText)findViewById(R.id.edtTxtDltTblName);
        edtTxtFld = (EditText)findViewById(R.id.edtTxtDltFldName);
        linLayHide = (LinearLayout)findViewById(R.id.linLayDltFldHide);
        confirm = new AlertDialog.Builder(this).create();
        edtFrmsObj = new editforms();
       
        if(edtFrmsObj.edtDlt)
        {
        	txtVwTitle.setText("Delete Table");
        	linLayHide.setVisibility(View.GONE);
        }
        else
        {
        	txtVwTitle.setText("Delete Field");
        }
        btnBack.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View v) 
    		{	 
    			 Intent i = new Intent(delete.this,editforms.class); //calling a class forms
    			 delete.this.finish();// close the form class 
    			 startActivity(i);			
    		}
    	});
        
        btnRslt.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View v) 
    		{	 
    			
    			if(!validate())
    			{
    				if(edtFrmsObj.edtDlt)
					{						
						name = edtTxtTbl.getText().toString();
    					confirm.setMessage("Do you wish to delete the table("+name.toUpperCase()+") from the database?");	
					}
					if(!edtFrmsObj.edtDlt)
					{
						name = edtTxtFld.getText().toString();
						confirm.setMessage("Do you wish to delete the field("+edtTxtFld.getText().toString().toUpperCase()+") from the database?");
					}
						confirm.setTitle("Delete "+name);
						confirm.setButton("Yes", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
    	    	  
							try
							{
								if(edtFrmsObj.edtDlt)
	    						{	
									String temp = viewDB.delete(name, name, "6", name);			
	    						}
								if(!edtFrmsObj.edtDlt)
		    					{
									String temp = viewDB.delete(name, name, "5", name);		
		    					}
								
								try
								{
									strTemp = temp.substring(0, temp.indexOf("@"));
									Toast.makeText(delete.this, strTemp, Toast.LENGTH_LONG);
									edtTxtFld.setText("");
									edtTxtTbl.setText("");
								}
								catch(Exception e)
								{
									strTemp = temp.substring(0, temp.indexOf("%"));
									Toast.makeText(delete.this, strTemp, Toast.LENGTH_LONG);
								}
								i = new Intent(delete.this,delete.class);
		    					startActivity(i);
							}
							catch(Exception e)
							{
								
							}
							
						} }); 
						confirm.setButton2("Cancel", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
    	    	  
							confirm.dismiss();
						} }); 
						confirm.show();
    			}
    		}
    	}); 
    }
	boolean validate()
	{
		validate = false;
		
		if(edtTxtTbl.getText().toString().length() < 4||edtTxtTbl.getText().toString().compareTo("") == 0||edtTxtTbl.getText().toString() == null)
		{	
			confirm.setMessage("Please enter table name. \nLength should be greater than 3 characters");
			validate = true;
		}	
		if(!edtFrmsObj.edtDlt && !validate)
	    {
			if(edtTxtFld.getText().toString().length() < 2||edtTxtFld.getText().toString().compareTo("") == 0||edtTxtFld.getText().toString() == null)
			{	
				confirm.setMessage("Please enter field name.\nIt should be 2 or more characters long");
				validate = true;
			}
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