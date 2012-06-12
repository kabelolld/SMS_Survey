package com.rapidsmsapp;

import java.util.StringTokenizer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class editforms extends Activity
 {
    /** Called when the activity is first created. */
   
	Button backtoForm,BtnReTbl,BtnDltTbl,BtnAddFld,BtnReFld,BtnDltFld;
	TextView formNames,txtVwBottom;
	String data,StrData[],Tbname,Fname,Action,Option,toastMsg;
	public static String strTblName;
	StringTokenizer stArr;
	ListView listView;
	database dt;
	AlertDialog confirm;
	Intent i;
	EditText edtTop,edtBottom;
	LinearLayout linLayedFrm;
	viewForms prevClass;
	renameTable rnmTbl;
	views views;
	public static boolean edtFrms,edtDlt;
	int tempInt;
	
	
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
		    super.onCreate(savedInstanceState);
	        setContentView(R.layout.editforms);
	        
	        backtoForm = (Button)findViewById(R.id.btnBckEdtFrm); 
	        BtnReTbl = (Button)findViewById(R.id.btnEdtFrmReTbl); 
	        BtnDltTbl = (Button)findViewById(R.id.btnEdtFrmDltTbl); 
	        BtnAddFld = (Button)findViewById(R.id.btnEdtFrmAddFld); 
	        BtnReFld = (Button)findViewById(R.id.btnEdtFrmReFld); 
	        BtnDltFld = (Button)findViewById(R.id.btnEdtFrmDltFld); 
	        views = new views();
	        confirm = new AlertDialog.Builder(this).create();
	        edtFrms = false;
	        
	        backtoForm.setOnClickListener(new View.OnClickListener() 
	        {
	    		
	    		public void onClick(View v) 
	    		{
	    			
	    			
	    			Intent i = new Intent(editforms.this,forms.class); //calling a class forms
	    			editforms.this.finish();// close the form class 
	    			startActivity(i);
	    			
	    		}
	    	});
	        
	        BtnReTbl.setOnClickListener(new View.OnClickListener() 
	        {
	    		
	    		public void onClick(View v) 
	    		{
	    			
	    			edtFrms = true;
	    			Intent i = new Intent(editforms.this,renameTable.class); //calling a class forms
	    			//editforms.this.finish();// close the form class 
	    			startActivity(i);
	    			
	    		}
	    	});
	        
	        BtnDltTbl.setOnClickListener(new View.OnClickListener() 
	        {
	    		
	    		public void onClick(View v) 
	    		{
	    			
	    			edtFrms = true;
	    			edtDlt = true;
	    			//-------------------------
	    			/*confirm.setMessage("Do you wish to select from table list?");					
					confirm.setButton("Yes", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
	    	  
						try
						{
							
						}
						catch(Exception e)
						{
							
						}
						
					} }); 
					confirm.setButton2("Cancel", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
	    	  
						
					} }); 
					confirm.show();*/
	    			//=========================
	    			Intent i = new Intent(editforms.this,delete.class); //calling a class forms
	    			//editforms.this.finish();// close the form class 
	    			startActivity(i);
	    			
	    		}
	    	});
	        
	        BtnAddFld.setOnClickListener(new View.OnClickListener() 
	        {
	    		
	    		public void onClick(View v) 
	    		{
	    			
	    			edtFrms = true; 
	    			Intent i = new Intent(editforms.this,addField.class); //calling a class forms
	    			//editforms.this.finish();// close the form class 
	    			startActivity(i);
	    			
	    		}
	    	});
	        
	        
	        
	        BtnDltFld.setOnClickListener(new View.OnClickListener() 
	        {
	    		
	    		public void onClick(View v) 
	    		{
	    			
	    			edtFrms = true;
	    			edtDlt = false;
	    			Intent i = new Intent(editforms.this,delete.class); //calling a class forms
	    			editforms.this.finish();// close the form class 
	    			startActivity(i);
	    			
	    		}
	    	});
	        
	       
    }
}