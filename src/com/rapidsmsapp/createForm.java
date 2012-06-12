package com.rapidsmsapp;

import java.net.HttpURLConnection;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;


public class createForm extends Activity 
{
     Button backtoForm;
     Button create;
     EditText FormName;
     EditText FormNumField; 
     AlertDialog disp;
     HttpURLConnection conn; 
     ScrollView sv;
     TextView[] TxtViewSepArr;
     TextView[] TxtViewNameArr;
     EditText[] EdtTxtNameArr;
     TextView[] TxtViewDTypeArr;
     EditText[] EdtTxtDTypeArr;
     TextView[] TxtViewSizeArr;
     EditText[] EdtTxtSizeArr;
     LinearLayout ll,llBtn;
     Button b;
     boolean TblCreated = false;
     database dbCreateTbl;
     int counter = 0,key = 0;
     String getName,getField,fields,form;
     int myNum = 0;  
	 int oldNum = 0;
	 boolean ViewFields = false;
     
	 public void onCreate(Bundle savedInstanceState)
	    {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.createform);
//--------------------------Event Listener for the Buttons and text----------------------------------	        
	        
	        //-------------Buttons------------------------
	        create =  (Button)findViewById(R.id.btnCreate); 
	        backtoForm =  (Button)findViewById(R.id.btnMain2); 
	        
	        //-------------Edit boxes--------------------
	        FormName =(EditText)findViewById(R.id.edtFname);
	        FormNumField=(EditText)findViewById(R.id.edtFields); 
	        
//------------------------------------------------------------------------------------------
	        disp = new AlertDialog.Builder(this).create();
//------------------------------------------------------------------------------------------
	        backtoForm.setOnClickListener(new View.OnClickListener()
	        {
				
				
				public void onClick(View v) 
				{
					Intent i = new Intent(createForm.this,forms.class); //calling a class forms
					createForm.this.finish();// close the form class 
	    			startActivity(i);					
				}
			});
	        
	        create.setOnClickListener(new View.OnClickListener()
	        {
				public void onClick(View v) 
				{
					if(FormName.getText().toString() == null||FormName.getText().toString().compareTo("") == 0||FormName.getText().toString().length() < 4)
					{
						disp.setTitle("Incomplete Entry");
						disp.setMessage("Please enter form name. \nIt should be greater than 3 charecters");
						disp.setButton("Cancel", new DialogInterface.OnClickListener() {
			        	      public void onClick(DialogInterface dialog, int which) {
			        	    	  
			        	    	  	disp.dismiss();
			        	    } }); 
						disp.show();
					}
					else
					{
						if(FormNumField.getText().toString() == null||FormNumField.getText().toString().compareTo("") == 0)
						{
							disp.setTitle("Incomplete Entry");
							disp.setMessage("Please enter number of fields. \nIt should be a number");
							disp.setButton("Cancel", new DialogInterface.OnClickListener() {
				        	      public void onClick(DialogInterface dialog, int which) {
				        	    	  
				        	    	  	disp.dismiss();
				        	    } }); 
							disp.show();
						}
						else
						{
							validateFields();
						}
						
					}
					
				}
			});
	        
	        sv = (ScrollView) findViewById(R.id.ScrllViewFields);		   	
	        ll = (LinearLayout) findViewById(R.id.LinLayAdd);	
	        llBtn = (LinearLayout) findViewById(R.id.LinLayBtns);
	        
	       
	        
	        
	        
	         FormName.addTextChangedListener(new TextWatcher(){         
	        	 public void onTextChanged(CharSequence s, int start, int before, int count){
	        	 }
				public void afterTextChanged(Editable s) {
					
				}

				public void beforeTextChanged(CharSequence s, int start,
						int count, int after) {}     
	        	 });  
	         
	        
	         
	         FormNumField.addTextChangedListener(new TextWatcher(){         
	        	 public void onTextChanged(CharSequence s, int start, int before, int count){
	        		 
	        		if((FormName.getText().toString() == null)||(FormName.getText().toString().compareTo("") == 0))
	        		{
	        			disp.setTitle("Warning: Incomplete Entry");
        				disp.setMessage("Please enter form name with a length of 3 characters or more");
        				disp.setButton("Cancel", new DialogInterface.OnClickListener() {
  		        	      public void onClick(DialogInterface dialog, int which) {
  		        	    	  
  		        	    	  	disp.dismiss();
  		        	    } }); 
        				disp.show();
        				FormNumField.setText("");    			
	        		}
	        		else
	        		{
	        			boolean test = false;
	        			if((FormNumField.getText().toString().compareTo("") != 0))
	        			{
	        				try
	        				{
	        					counter = Integer.parseInt(FormNumField.getText().toString());
	        					test = true;
	        				}
	        				catch(Exception e)
	        				{
	        					disp.setTitle("Warning: Wrong Entry");
	            				disp.setMessage("Please enter numbers only");
	            				disp.setButton("Cancel", new DialogInterface.OnClickListener() {
	      		        	      public void onClick(DialogInterface dialog, int which) {
	      		        	    	  
	      		        	    	  	disp.dismiss();
	      		        	    } }); 
	            				disp.show();
	        				}
	        			}
	        			if((FormNumField.getText().toString().compareTo("") == 0))
	        			{
	        				ll.removeAllViews();
	        				sv.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT,0));
	        			}
	        			if(test)
	        			{
	        				CheckInput(FormNumField.getText().toString());
	        			}
	        		}
					
	        	 }
				public void afterTextChanged(Editable s) {}

				public void beforeTextChanged(CharSequence s, int start,
						int count, int after) {}     
	        	 });   
	         
	    }
//---------------------------------------------------After On Create Methods--------------------------------------------------------------
	 public void ShowFields(int count)
	 {			
		 for(int q = 0; q < count;q++)
		 {
			 //Dynamically creating pointers to the components 
			EdtTxtNameArr[q] = new EditText(this);
			EdtTxtDTypeArr[q] = new EditText(this);
			EdtTxtSizeArr[q] = new EditText(this);
			TxtViewNameArr[q] = new TextView(this);
			TxtViewDTypeArr[q] = new TextView(this);
			TxtViewSizeArr[q] = new TextView(this);
			TxtViewSepArr[q] = new TextView(this);
			
			TxtViewSepArr[q].setHeight(30);				//Empty text view as separator
		    ll.addView(TxtViewSepArr[q]);				//adding to the layout
			
			//Setting attributes of the "name" label and edit box 
			TxtViewNameArr[q].setText("Field "+(q+1));
			TxtViewNameArr[q].setTextSize(30);
			ll.addView(TxtViewNameArr[q]);				//adding to the layout
			EdtTxtNameArr[q].setText("");
			EdtTxtNameArr[q].setMaxLines(1);
			EdtTxtNameArr[q].setLayoutParams(new LinearLayout.LayoutParams(this.ll.getWidth()/2, LayoutParams.WRAP_CONTENT));
	        ll.addView(EdtTxtNameArr[q]);				//adding to the layout
	        
	        //Setting attributes of the "data type" label and edit box 
	        TxtViewDTypeArr[q].setText("Data Type");
			ll.addView(TxtViewDTypeArr[q]);				//adding to the layout
			EdtTxtDTypeArr[q].setText("");
			EdtTxtDTypeArr[q].setMaxLines(1);
			EdtTxtDTypeArr[q].setLayoutParams(new LinearLayout.LayoutParams(this.ll.getWidth()/2, LayoutParams.WRAP_CONTENT));
	        ll.addView(EdtTxtDTypeArr[q]);				//adding to the layout
			
	      //Setting attributes of the "size" label and edit box 
	        TxtViewSizeArr[q].setText("Field Size");
			ll.addView(TxtViewSizeArr[q]);				//adding to the layout
			EdtTxtSizeArr[q].setText("");
			//EdtTxtSizeArr[q].setInputType(InputType.TYPE_CLASS_NUMBER);
			EdtTxtSizeArr[q].setMaxLines(1);
			EdtTxtSizeArr[q].setLayoutParams(new LinearLayout.LayoutParams(this.ll.getWidth()/2, LayoutParams.WRAP_CONTENT));
			ll.addView(EdtTxtSizeArr[q]);				//adding to the layout
	        
	       
		 }
		 sv.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,0,1));
	 }
	 public void CheckInput(String count)		//Check for the required number of fields
	 {
		 ll.removeAllViews();
		 
		 
		 try 
		 {    
			 if(count != null)
			 {
				 myNum = Integer.parseInt(count.toString());
			 	 if((myNum > oldNum)||(myNum == oldNum))
			 	 {
			 		oldNum = myNum;
			 		ViewFields = true;
			 	 }
			 	if(myNum < oldNum)
			 	 {
			 		oldNum = myNum;
			 		ViewFields = false;
			 	 }
			 }
		 } 
		 catch(NumberFormatException nfe) 
		 {    
			 sv.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,0,0));
		 }  
		 
		
		 if(ViewFields == true)
		 {
			TxtViewSepArr = new TextView[myNum];
			TxtViewNameArr = new TextView[myNum];
			TxtViewDTypeArr = new TextView[myNum];
			TxtViewSizeArr = new TextView[myNum];
			EdtTxtNameArr = new EditText[myNum];
			EdtTxtDTypeArr = new EditText[myNum];
			EdtTxtSizeArr = new EditText[myNum];
			ShowFields(myNum);
		 }
		 if(ViewFields == false)
		 {
			for(int q = oldNum;q > myNum;q--)
			{
				Log.e(getPackageName(), "DELETING_11111111111111_DELETING__111111111111111111__DELETING_____1111111111111111111____DELETING");
				ll.removeView(EdtTxtNameArr[q]);
				ll.removeView(EdtTxtDTypeArr[q]);
				ll.removeView(EdtTxtSizeArr[q]);
				ll.removeView(TxtViewNameArr[q]);
				ll.removeView(TxtViewDTypeArr[q]);
				ll.removeView(TxtViewSizeArr[q]);
				ll.removeView(TxtViewSepArr[q]);
			}
		 }
	 }
	 String name,datatype;
	 int FSize = 0;
	 
	 public void checkFields()
	 {
		 String temp = "";
			for(int x = 0; x < counter; x++)
			{
				
				name = EdtTxtNameArr[x].getText().toString();
				
				datatype = EdtTxtDTypeArr[x].getText().toString();	
				
				try
				{
					if(EdtTxtSizeArr[x].getText().toString().indexOf("") == -1)
					{
						FSize = Integer.parseInt(EdtTxtSizeArr[x].getText().toString());
					}
					else
					{
						disp.setTitle("Warning: Wrong Entry");
	    				disp.setMessage("Field size should be numbers only");
	    				disp.setButton("Cancel", new DialogInterface.OnClickListener() {
			        	      public void onClick(DialogInterface dialog, int which) {
			        	    	  
			        	    	  	disp.dismiss();
			        	    } }); 
	    				disp.show();
					}
				}
				catch(Exception e)
				{
					disp.setTitle("Warning: Wrong Entry");
    				disp.setMessage("Please enter field size. \nIt should be numbers only");
    				disp.setButton("Cancel", new DialogInterface.OnClickListener() {
		        	      public void onClick(DialogInterface dialog, int which) {
		        	    	  
		        	    	  	disp.dismiss();
		        	    } }); 
    				disp.show();
				}
				temp += name + " " + datatype.toUpperCase() + "(" + EdtTxtSizeArr[x].getText().toString() + ") NOT NULL"; 
				
				if(x + 1 != counter)
				{
					temp += ",";
				}
			}
			fields = temp.substring(0,(temp.length()));
		 
	 }
	 
	 public void validateFields()
	 {
	 	int x = 0;
	 	
	 	String[] dataType = {"INT","VARCHAR","CHAR","TEXT","MEDIUMTEXT","TINYTEXT","FLOAT","DOUBLE","TINYINT","SMALLINT"};
	 	
	 	disp.setTitle("Invalid Entry");			//Error title
	
	 	ValidateEnd:
	 	
	 			
	 	for(; x < counter; x++)
	 	{
	 		key = 0;
	 		Log.e(getPackageName(),"In validateFields() 123451234512345");
	 		if(EdtTxtNameArr[x].getText().toString() == null||EdtTxtNameArr[x].getText().toString().compareTo("") == 0)
	 		{
	 			
	 			disp.setMessage("Field "+(x+1)+": \nPlease enter field name");
	 			disp.show();
	 			key = 1;
	 			x = 100;
	 			break ValidateEnd;
	 		}
	 		else 
	 		{
	 			if(EdtTxtNameArr[x].getText().toString().indexOf(" ")!= -1)
	 			{
	 				
	 				
	 				disp.setMessage("Field "+(x+1)+": \nSpaces are not in field name");
		 			disp.show();
	 				key = 1;
	 				x = 100;
	 				break ValidateEnd;
	 			}
	 			
	 			// Check if field names are the same
	 			for(int i = (x+1); i < counter; i++)
	 			{	
	 				if(EdtTxtNameArr[x].getText().toString().compareTo(EdtTxtNameArr[i].getText().toString()) == 0)
	 				{	
	 					
	 					
	 					disp.setMessage("Field "+(x+1)+": \nPlease enter unique field name");
	 		 			disp.show();
	 					key = 1;
	 					i = 100;
	 					x = 100;
	 					break ValidateEnd;
	 				} 
	 			}	
	 		}
	 		
	 		if(EdtTxtDTypeArr[x].getText().toString() == null||EdtTxtDTypeArr[x].getText().toString().compareTo("") == 0)
	 		{
	 			
	 			
	 			disp.setMessage("Field "+(x+1)+": \nPlease enter data type");
	 			disp.show();
	 			key = 1;
	 			x = 100;
	 			break ValidateEnd;
	 		}
	 		
	 		
	 		if(EdtTxtDTypeArr[x].getText().toString() != null||EdtTxtDTypeArr[x].getText().toString().compareTo("") != 0)
	 		{
	 			int start = 0,end = 1;
	 			
	 			if(EdtTxtDTypeArr[x].getText().toString().indexOf(" ")!= -1)
	 			{
	 				
	 				
	 				disp.setMessage("Field "+(x+1)+": Spaces are not in data type");
		 			disp.show();
	 				key = 1;
	 				x = 100;
	 				break ValidateEnd;
	 			}
	 			else
	 			{
	 				for(int w = 0;w < EdtTxtDTypeArr[x].getText().toString().length();w++)
	 				{
	 				
	 					String temp = EdtTxtDTypeArr[x].getText().toString().substring(start, end);
	 					try
	 					{
	 						if(Integer.parseInt(temp.toString()) >= 0 && Integer.parseInt(temp.toString()) <= 9)
	 						{
	 							
	 							
	 							disp.setMessage("Field "+(x+1)+": \nData type should not include numbers");
	 							disp.show();
	 							key = 1;
	 							x = 100;
	 							w = 100;
	 							break ValidateEnd;
	 					
	 						}
	 						else
	 						{
	 							
	 							
	 							
	 						}
	 					}
	 					catch(NumberFormatException nfe)
	 					{
	 						if(EdtTxtDTypeArr[x].getText().toString().length() - w == 1)
	 						{
	 							continue;
	 						}
	 					}
	 					start++;
	 					end++;
	 				}
	 				
	 				for(int q = 0;q < dataType.length;q++)
		 			{
		 			
		 				if(EdtTxtDTypeArr[x].getText().toString().toLowerCase().compareTo(dataType[q].toLowerCase()) == 0)
		 				{
		 					q = dataType.length + 10;
		 					continue;
		 				}
		 				else
		 				{
		 					if((dataType.length - q) == 1)
		 					{
		 						String temp = "";
		 						for(int a = 0;a < dataType.length;a++)
		 						{
		 							temp += dataType[a].toString();
		 							if(a + 1 != dataType.length)
		 							{
		 								temp += ", ";
		 							}
		 						}
		 						disp.setMessage("Field "+(x+1)+": \nData type not applicable for this app.\nAccepted Data types are: \n" + temp);
		 						disp.show();
		 						key = 1;
		 						x = 100;
		 						q = (dataType.length + 10);
		 						break ValidateEnd;
		 					}
		 					
		 				}
		 			}
	 			}
	 		}
	 		
	 		int q = 0;
	 		try
	 		{
	 			if(EdtTxtSizeArr[x].getText().toString().indexOf("") == -1||EdtTxtSizeArr[x].getText().toString().compareTo("") != 0)
	 			{
	 				q = Integer.parseInt(EdtTxtSizeArr[x].getText().toString());
	 			
	 				if(q == 0)
	 				{
	 					disp.setMessage("Field "+(x+1)+": \nSize should greater than 0");
	 					disp.show();
	 					key = 1;
	 					x = 100;
	 					break ValidateEnd;
	 				}
	 			
	 			}
	 			else
	 			{
	 				disp.setMessage("Field "+(x+1)+": \nPlease enter field size. \nIt should be numbers only. \nSpaces are not allowed");
		 			disp.setButton("Cancel", new DialogInterface.OnClickListener() {
		        	      public void onClick(DialogInterface dialog, int which) {
		        	    	  
		        	    	  	disp.dismiss();
		        	    } }); 
					disp.show();
					key = 1;
					x = 100;
	 			}
	 		}
	 		catch(NumberFormatException nfe)
	 		{
	 			disp.setMessage("Field "+(x+1)+": \nSize should be an integer");
	 			disp.setButton("Cancel", new DialogInterface.OnClickListener() {
	        	      public void onClick(DialogInterface dialog, int which) {
	        	    	  
	        	    	  	disp.dismiss();
	        	    } }); 
				disp.show();
	 			key = 1;
	 			x = 100;
	 			
	 			break ValidateEnd;	
	 		}
	 	}
	 	
	 	if(key!= 1)
	 	{
	 		
	 		checkFields();
	 		
	 		dbCreateTbl = new database();
	 		try
	 		{
	 			form = dbCreateTbl.setForms(FormName.getText().toString(), fields, "2", "0", "temp");
	 			form = form.substring(0, form.indexOf("@"));
		 		disp.setTitle("Table creation completed");
		 		disp.setMessage(form);
		 		disp.setButton("OK", new DialogInterface.OnClickListener() {
	      	      public void onClick(DialogInterface dialog, int which) {
	      	    	  
	      	    	  	if(TblCreated)
	      	    	  	{  
	      	    	  		Toast.makeText(createForm.this, "Table Created", Toast.LENGTH_LONG).show();
	      	    	  		Intent i = new Intent(createForm.this,forms.class); //calling a class forms
	      					createForm.this.finish();// close the form class 
	      	    			startActivity(i);
	      	    	  		TblCreated = false;
	      	    	  	}
	      	    	  	else
	      	    	  	{
	      	    	  		disp.dismiss();
	      	    	  	}
	      	    } }); 
	 		}
	 		catch(Exception e)
	 		{
	 			Toast.makeText(createForm.this, "Error: Failed to retrieve from the database\n\nException\n"+ e.getMessage(), Toast.LENGTH_LONG).show();  	
	 			disp.setTitle("Table creation Error");
		 		disp.setMessage("Error: " + e.getMessage() + "\n" + form);
		 		disp.setButton("OK", new DialogInterface.OnClickListener() {
	      	      public void onClick(DialogInterface dialog, int which) {
	      	    	  
	      	    	
	      	    	  		disp.dismiss();
	      	    	  	
	      	    } }); 
	 		}
	 		
	 		
	 		
	 		TblCreated = true;
	 		disp.show();
	 	}	
	 	
	 };
}