package com.rapidsmsapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class addField extends Activity
 {
    /** Called when the activity is first created. */
   
	Button backtoForm,btnRslt;	
	public static String strTblName;
	database dbCreateTbl;
	AlertDialog disp;
	Intent i;
	EditText edtTop,edtBottom;
	LinearLayout ll;
	views views;
	int q = 0,counter;
	ImageView ImgView,ImgViewMinus;
	TextView[] TxtViewSep;
    TextView[] TxtViewName,TxtViewName1;
    EditText[] EdtTxtName;
    TextView[] TxtViewDType;
    EditText[] EdtTxtDType;
    TextView[] TxtViewSize;
    EditText[] EdtTxtSize;
    ScrollView sv;
    String name,datatype;
	int FSize = 0,key , x ,p = 0;
    boolean removeView,valid,addFld,isTblName;
    String fields,TblName,temp,form;
    editforms edtFrmsObj;
	
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
		    super.onCreate(savedInstanceState);
	        setContentView(R.layout.addfield);
	       
	        backtoForm = (Button)findViewById(R.id.btnBckAdd); 
	        btnRslt = (Button)findViewById(R.id.btnRsltAdd); 
	        edtTop = (EditText)findViewById(R.id.edtTxtTblAdd); 
	        ImgView = (ImageView)findViewById(R.id.ImgAddField);
	        ImgViewMinus = (ImageView)findViewById(R.id.ImgViewMinus);
	        sv = (ScrollView)findViewById(R.id.ScrllViewAddFields);
	        ll = (LinearLayout)findViewById(R.id.LinLayAddField);
	        
	        dbCreateTbl = new database();
	        views = new views();
	        
	        disp = new AlertDialog.Builder(this).create();
	        edtFrmsObj = new editforms();
	        ImgViewMinus.setVisibility(View.INVISIBLE);

	        int maxNum = 10;
	        TxtViewSep = new TextView[maxNum];
			TxtViewName = new TextView[maxNum];
			TxtViewName1 = new TextView[maxNum];
			TxtViewDType = new TextView[maxNum];
			TxtViewSize = new TextView[maxNum];
			EdtTxtName = new EditText[maxNum];
			EdtTxtDType = new EditText[maxNum];
			EdtTxtSize = new EditText[maxNum];
	        
	        backtoForm.setOnClickListener(new View.OnClickListener() 
	        {	
	        	
	    		public void onClick(View v) 
	    		{ 
	    			if(views.Table)
	    			{
	    				i = new Intent(addField.this,viewForms.class); //calling a class forms
	    			}
	    			if(views.Field)
	    			{
	    				i = new Intent(addField.this,views.class); //calling a class forms
	    			}	
	    			if(edtFrmsObj.edtFrms)
	    			{
	    				i = new Intent(addField.this,editforms.class); //calling a class forms
	    			}
	    			addField.this.finish();// close the form class 
	    			startActivity(i);	    			
	    		}
	    	});
	        
	        btnRslt.setOnClickListener(new View.OnClickListener() 
	        {	
	        	
	    		public void onClick(View v) 
	    		{ 
	    			if(validating() && addFld)
	    			{
	    				TblName = edtTop.getText().toString();
	            		try
	            		{
	            			temp = dbCreateTbl.setForms(edtTop.getText().toString(), "fields", "3", "0",fields);
	            			if(temp.length() != 0)
	            			{
	        				
	            				Toast.makeText(addField.this,temp.substring(0, temp.indexOf("@")) , Toast.LENGTH_LONG).show(); 
	            				Intent i = new Intent(addField.this,editforms.class); //calling a class forms
	            				addField.this.finish();// close the form class 
	            				startActivity(i);
	            			}
	            		}
	            		catch(Exception e)
	            		{
	            			Toast.makeText(addField.this,"Error: \n"+temp , Toast.LENGTH_LONG).show(); 
	            		}
	    			}
	    			else
	    			{
	    				isTblName = false;
	    				if(edtTop.getText().toString().compareTo("") == 0 || edtTop.getText().toString() == null)
	    				{
	    					disp.setTitle("Incomplete Entry");
	    					disp.setMessage("Please enter table name");
	    					disp.setButton("Cancel", new DialogInterface.OnClickListener() {
			        	      public void onClick(DialogInterface dialog, int which) {
			        	    	  isTblName = true;
			        	    	  	disp.dismiss();
			        	    } }); 
	    					disp.show();
	    				}
	    				else
	    				{
	    					if(edtTop.getText().toString().length() < 3)
		    				{
		    					disp.setTitle("Invalid Entry");
		    					disp.setMessage("Table name should be greater than 3 characters\n"+edtTop.getText().toString());
		    					disp.setButton("Cancel", new DialogInterface.OnClickListener() {
				        	      public void onClick(DialogInterface dialog, int which) {
				        	    	  isTblName = true;
				        	    	  	disp.dismiss();
				        	    } }); 
		    					disp.show();
		    				}
	    					else
	    					{
	    						if(!addFld)
	    						{
	    							disp.setTitle("Incomplete Entry");
	    							disp.setMessage("Please add at least 1 field)");
	    							disp.setButton("Cancel", new DialogInterface.OnClickListener() {
	    								public void onClick(DialogInterface dialog, int which) {
			        	    	  
	    									disp.dismiss();
	    								} }); 
	    							disp.show();
	    						}
	    					}
	    				}
	    			}
	    		}
	    	});
	        
	        ImgView.setOnClickListener(new View.OnClickListener() 
	        {	
	    		public void onClick(View v) 
	    		{ 
	    			if(edtTop.getText().toString() != null||edtTop.getText().toString().compareTo("") != 0||edtTop.getText().toString().length() > 3)
	    			{
	    				addFields();
	    				counter = q++;
	    				if(q == 1)
	    				{
	    					ImgViewMinus.setVisibility(View.VISIBLE);
	    					removeView = true;
	    					addFld = true;
	    				}
	    			}
	    			else
	    			{
	    				disp.setTitle("Incomplete Entry");
	    				disp.setMessage("Please enter table name");
	    				disp.setButton("Cancel", new DialogInterface.OnClickListener() {
			        	      public void onClick(DialogInterface dialog, int which) {
			        	    	  
			        	    	  disp.dismiss();
			        	    } }); 
	    				disp.show();
	    			}
	    		}
	    	});
	        
	        ImgViewMinus.setOnClickListener(new View.OnClickListener() 
	        {	
	    		public void onClick(View v) 
	    		{ 
	    			if(removeView)
	    			{
	    				q--;
	    				removeView = false;
	    			}
	    			removeFields();
	    			
	    			counter = q--;
	    			if(q < 0)
	    			{
	    				ImgViewMinus.setVisibility(View.INVISIBLE);
	    				counter = q++;
	    				addFld = false;
	    				sv.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,50,0));
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
    }
	void addFields()
	{
				//Dynamically creating pointers to the components 
		if(q != 10)
		{
			
			EdtTxtName[q] = new EditText(this);
			EdtTxtDType[q] = new EditText(this);
			EdtTxtSize[q] = new EditText(this);
			TxtViewName[q] = new TextView(this);
			TxtViewName1[q] = new TextView(this);
			TxtViewDType[q] = new TextView(this);
			TxtViewSize[q] = new TextView(this);
			TxtViewSep[q] = new TextView(this);
		
			TxtViewSep[q].setHeight(15);				//Empty text view as separator
			ll.addView(TxtViewSep[q]);				//adding to the layout
			
			//Setting attributes of the "name" label and edit box 
			TxtViewName[q].setText("Field "+(q+1));
			TxtViewName1[q].setText("Field Name");
			TxtViewName[q].setTextSize(30);
			ll.addView(TxtViewName[q]);				//adding to the layout
			EdtTxtName[q].setText("");
			EdtTxtName[q].setMaxLines(1);
			EdtTxtName[q].setLayoutParams(new LinearLayout.LayoutParams(this.ll.getWidth()/2, LayoutParams.WRAP_CONTENT));
			EdtTxtName[q].setOnClickListener(new View.OnClickListener()
	        {
				public void onClick(View v) 
				{
					EdtTxtName[q].setText("");
				}
			});
			ll.addView(EdtTxtName[q]);				//adding to the layout
			
			//Setting attributes of the "data type" label and edit box 
			TxtViewDType[q].setText("Data Type");
			ll.addView(TxtViewDType[q]);				//adding to the layout
			EdtTxtDType[q].setText("");
			EdtTxtDType[q].setMaxLines(1);
			EdtTxtDType[q].setLayoutParams(new LinearLayout.LayoutParams(this.ll.getWidth()/2, LayoutParams.WRAP_CONTENT));
			EdtTxtDType[q].setOnClickListener(new View.OnClickListener()
	        {
				public void onClick(View v) 
				{
					EdtTxtDType[q].setText("");
				}
			});
			ll.addView(EdtTxtDType[q]);				//adding to the layout
			
			//Setting attributes of the "size" label and edit box 
			TxtViewSize[q].setText("Field Size");
			ll.addView(TxtViewSize[q]);				//adding to the layout
			EdtTxtSize[q].setText("");
			EdtTxtSize[q].setInputType(InputType.TYPE_CLASS_NUMBER);
			//EdtTxtSizeArr[q].setInputType(InputType.TYPE_CLASS_NUMBER);
			EdtTxtSize[q].setMaxLines(1);
			EdtTxtSize[q].setLayoutParams(new LinearLayout.LayoutParams(this.ll.getWidth()/2, LayoutParams.WRAP_CONTENT));
			EdtTxtSize[q].setOnClickListener(new View.OnClickListener()
	        {
				public void onClick(View v) 
				{
					EdtTxtSize[q].setText("");
				}
			});
			ll.addView(EdtTxtSize[q]);	

			sv.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,0,1));
			
		}
		else
		{

			Toast.makeText(addField.this, "You can not add more than 10 fields at a time", 2500);
			
		}
		
	}
	
	void removeFields()
	{
		ll.removeView(EdtTxtName[q]);
		ll.removeView(EdtTxtDType[q]);
		ll.removeView(EdtTxtSize[q]);
		ll.removeView(TxtViewName[q]);
		ll.removeView(TxtViewDType[q]);
		ll.removeView(TxtViewSize[q]);
		ll.removeView(TxtViewSep[q]);
	}
	
	
	 
	 public void testFields()
	 {
		 temp = "";
		 
		 for(int x = 0; x <= counter; x++)
			{				
				try
				{
					
					FSize = Integer.parseInt(EdtTxtSize[x].getText().toString());
						
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
				temp += EdtTxtName[x].getText().toString() + 
						" " + EdtTxtDType[x].getText().toString().toUpperCase() + 
						"(" + EdtTxtSize[x].getText().toString() + ") NOT NULL"; 
				
				if(x + 1 != counter)
				{
					temp += ",";
				}
			}
			fields = temp.substring(0,(temp.length()));
			
			if(fields.length() > 0)
			{
				valid = true;
			}
			Toast.makeText(addField.this, fields, Toast.LENGTH_LONG).show();
	 }
	 
	boolean validating()
	 {
	 	valid = false;
	 	x = 0;
	 	key = 0;
	 	String[] dataTypes = {"INT","VARCHAR","CHAR","TEXT","MEDIUMTEXT","TINYTEXT","FLOAT","DOUBLE","TINYINT","SMALLINT"};
	 	
	 	
	
	 	ValidateEnd:
	 		
	 		for(; x <= counter; x++)
		 	{
	 			disp.setTitle("Invalid Entry");			//Error title
		 		key = 0;
		 		
		 		if(EdtTxtName[x].getText().toString() == null||EdtTxtName[x].getText().toString().compareTo("") == 0)
		 		{
		 			
		 			disp.setMessage("Field "+(x+1)+": \nPlease enter field name");
		 			disp.setButton("Cancel", new DialogInterface.OnClickListener() {
		        	      public void onClick(DialogInterface dialog, int which) {
		        	    	  
		        	    	  	disp.dismiss();
		        	    } }); 
		 			disp.show();
		 			key = 1;
		 			x = 100;
		 			break ValidateEnd;
		 		}
		 		else 
		 		{
		 			if(EdtTxtName[x].getText().toString().indexOf(" ")!= -1)
		 			{
		 				
		 				
		 				disp.setMessage("Field "+(x+1)+": \nSpaces are not in field name");
		 				disp.setButton("Cancel", new DialogInterface.OnClickListener() {
			        	      public void onClick(DialogInterface dialog, int which) {
			        	    	  
			        	    	  	disp.dismiss();
			        	    } }); 
			 			disp.show();
		 				key = 1;
		 				x = 100;
		 				break ValidateEnd;
		 			}
		 			
		 			// Check if field names are the same
		 			for(int i = (x+1); i < counter; i++)
		 			{	
		 				if(EdtTxtName[x].getText().toString().compareTo(EdtTxtName[i].getText().toString()) == 0)
		 				{	
		 					
		 					
		 					disp.setMessage("Field "+(x+1)+": \nPlease enter unique field name");
		 					disp.setButton("Cancel", new DialogInterface.OnClickListener() {
				        	      public void onClick(DialogInterface dialog, int which) {
				        	    	  
				        	    	  	disp.dismiss();
				        	    } }); 
		 		 			disp.show();
		 					key = 1;
		 					i = 100;
		 					x = 100;
		 					break ValidateEnd;
		 				} 
		 			}	
		 		}
		 		
		 		if(EdtTxtDType[x].getText().toString() == null||EdtTxtDType[x].getText().toString().compareTo("") == 0)
		 		{
		 			
		 			
		 			disp.setMessage("Field "+(x+1)+": \nPlease enter data type");
		 			disp.setButton("Cancel", new DialogInterface.OnClickListener() {
		        	      public void onClick(DialogInterface dialog, int which) {
		        	    	  
		        	    	  	disp.dismiss();
		        	    } }); 
		 			disp.show();
		 			key = 1;
		 			x = 100;
		 			break ValidateEnd;
		 		}
		 		
		 		
		 		if(EdtTxtDType[x].getText().toString() != null||EdtTxtDType[x].getText().toString().compareTo("") != 0)
		 		{
		 			int start = 0,end = 1;
		 			
		 			if(EdtTxtDType[x].getText().toString().indexOf(" ")!= -1)
		 			{
		 				
		 				
		 				disp.setMessage("Field "+(x+1)+": Spaces are not in data type");
		 				disp.setButton("Cancel", new DialogInterface.OnClickListener() {
			        	      public void onClick(DialogInterface dialog, int which) {
			        	    	  
			        	    	  	disp.dismiss();
			        	    } }); 
			 			disp.show();
		 				key = 1;
		 				x = 100;
		 				break ValidateEnd;
		 			}
		 			else
		 			{
		 				for(int w = 0;w < EdtTxtDType[x].getText().toString().length();w++)
		 				{
		 				
		 					temp = EdtTxtDType[x].getText().toString().substring(start, end);
		 					try
		 					{
		 						if(Integer.parseInt(temp.toString()) >= 0 && Integer.parseInt(temp.toString()) <= 9)
		 						{
		 							
		 							
		 							disp.setMessage("Field "+(x+1)+": \nData type should not include numbers");
		 							disp.setButton("Cancel", new DialogInterface.OnClickListener() {
		 				        	      public void onClick(DialogInterface dialog, int which) {
		 				        	    	  
		 				        	    	  	disp.dismiss();
		 				        	    } }); 
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
		 						if(EdtTxtDType[x].getText().toString().length() - w == 1)
		 						{
		 							continue;
		 						}
		 					}
		 					start++;
		 					end++;
		 				}
		 				
		 				for(int f = 0;f < dataTypes.length;q++)
			 			{
			 			
			 				if(EdtTxtDType[x].getText().toString().toLowerCase().compareTo(dataTypes[f].toLowerCase()) == 0)
			 				{
			 					f = dataTypes.length + 10;
			 					continue;
			 				}
			 				else
			 				{
			 					if((dataTypes.length - x) == 1)
			 					{
			 						temp = "";
			 						for(int a = 0;a < dataTypes.length;a++)
			 						{
			 							temp += dataTypes[a].toString();
			 							if(a + 1 != dataTypes.length)
			 							{
			 								temp += ", ";
			 							}
			 						}
			 						disp.setMessage("Field "+(x+1)+": \nData type not applicable for this app.\nAccepted Data types are: \n" + temp);
			 						disp.setButton("Cancel", new DialogInterface.OnClickListener() {
			 			        	      public void onClick(DialogInterface dialog, int which) {
			 			        	    	  
			 			        	    	  	disp.dismiss();
			 			        	    } }); 
			 						disp.show();
			 						key = 1;
			 						x = 100;
			 						f = (dataTypes.length + 10);
			 						break ValidateEnd;
			 					}
			 					
			 				}
			 			}
		 			}
		 		}
		 		
		 		int y = 0;
		 		try
		 		{
		 			if(EdtTxtSize[x].getText().toString().indexOf("") == -1||EdtTxtSize[x].getText().toString().compareTo("") != 0)
		 			{
		 				y = Integer.parseInt(EdtTxtSize[x].getText().toString());
		 			
		 				if(y == 0)
		 				{
		 					disp.setMessage("Field "+(x+1)+": \nSize should greater than 0");
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
			 		
		 			
		 			testFields();
		 			
			 		
			 	}	
		 	
	 	return valid;
	 };
 }