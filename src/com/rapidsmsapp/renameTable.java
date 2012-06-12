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
import android.widget.TextView.BufferType;
import android.widget.Toast;


public class renameTable extends Activity 
{
    /** Called when the activity is first created. */
	Button btnBack,btnAdd,btnTblRslt;
	viewForms viewFormObj;
	AlertDialog confirm;
	EditText edtTop,edtBottom;
	editforms edtFrmsObj;
	Intent i;
	String TblNew,TblOld,temp;
	database dbObj;
	
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.renametable);
        
        btnBack = 		(Button)findViewById(R.id.btnBckTbl); // chose the btnform you want to use by its ID
        btnTblRslt = 		(Button)findViewById(R.id.btnRsltTbl);//
        edtTop = (EditText)findViewById(R.id.edtTxtOldName);
        edtBottom = (EditText)findViewById(R.id.edtTxtNewName);
        viewFormObj = new viewForms();
        edtFrmsObj = new editforms();
        dbObj = new database();
       
        btnTblRslt.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) 
    		{
        		TblNew = edtBottom.getText().toString();
        		TblOld = edtTop.getText().toString();
        		
        		try
        		{
        			temp = dbObj.getRename(TblOld, "Fname", "9", "Option", TblNew);
        			if(temp.length() != 0)
        			{
        				
        				Toast.makeText(renameTable.this,temp.substring(0, temp.indexOf("@")) , Toast.LENGTH_LONG).show(); 
        				if(edtFrmsObj.edtFrms)
            			{
            				i = new Intent(renameTable.this,editforms.class); //calling a class forms
            			}
            			else
            			{
            				i = new Intent(renameTable.this,viewForms.class); //calling a class forms	
            			}
            			renameTable.this.finish();// close the form class 
            			startActivity(i);
        			}
        		}
        		catch(Exception e)
        		{
        			Toast.makeText(renameTable.this,"Error: \n"+temp, Toast.LENGTH_LONG).show(); 
        		}
    			 
    			
    			
    		}
    	});
        
       
        btnBack.setOnClickListener(new View.OnClickListener() {	
    		public void onClick(View v) 
    		 {
    			if(edtFrmsObj.edtFrms)
    			{
    				i = new Intent(renameTable.this,editforms.class); //calling a class forms
    			}
    			else
    			{
    				i = new Intent(renameTable.this,viewForms.class); //calling a class forms	
    			}
    			renameTable.this.finish();// close the form class 
    			startActivity(i);
    		 }
    		});  
        
        String defaultName="";
        Bundle extras = getIntent().getExtras();
       
        if(extras != null)
        {
        	defaultName = extras.getString("TblName");
        	edtTop.setText(defaultName);
        }
    }	
}
