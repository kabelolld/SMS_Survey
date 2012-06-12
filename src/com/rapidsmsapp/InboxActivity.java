package com.rapidsmsapp;

import java.util.StringTokenizer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class InboxActivity extends Activity
 {
    /** Called when the activity is first created. */
   
	Button backtoForm;
	TextView formNames,txtVwBottom;
	String data,StrData[],Tbname,Fname,Action,Option,toastMsg;
	public static String strTblName;
	StringTokenizer stArr;
	ListView listView;
	database dt;
	AlertDialog confirm;
	Intent i;
	EditText edtTop,edtBottom;
	LinearLayout llInflate,llHide;
	viewForms prevClass;
	renameTable rnmTbl;
	views views;
	int tempInt;
	
	
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
		    super.onCreate(savedInstanceState);
	        setContentView(R.layout.inbox_list);
	        
	        backtoForm = (Button)findViewById(R.id.BtnInbxBck); 
	       // edtBottom = (EditText)findViewById(R.id.edtTxtNewName); 
	       // edtTop = (EditText)findViewById(R.id.edtTxtOldName); 
	       // llHide = (LinearLayout)findViewById(R.id.LinHide);
	       
	        listView = (ListView) findViewById(R.id.list);
	      //  llInflate = (LinearLayout) findViewById(R.id.LinInflate);
	        listView.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT,10));
	        dt = new database();
	        views = new views();
	        confirm = new AlertDialog.Builder(this).create();
	        
	        
	        
	        
	        try
	        {
	        	data = dt.getForms("SMSBACKUP", "temp", "8", "1");
	        	data = data.substring(0, data.indexOf("@"));
	   		 
	        	StrData = data.split("#");
	        		
	        
	        	listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, StrData));	 
	        	registerForContextMenu(listView);	
	        }
	        catch(Exception e)
	        {
	        	Toast.makeText(InboxActivity.this, "Couldn't connect to the database. Check if data is enabled.", 5000).show();
	        }
	        
	      	      
	        backtoForm.setOnClickListener(new View.OnClickListener() 
	        {
	    		
	    		public void onClick(View v) 
	    		{
	    			
	    			 
	    			Intent i = new Intent(InboxActivity.this,messagemenu.class); //calling a class forms
	    			InboxActivity.this.finish();// close the form class 
	    			startActivity(i);
	    			
	    		}
	    	});
    }
	@Override
    public void onCreateContextMenu(ContextMenu menu, View v,
    		ContextMenuInfo menuInfo) {
    	if (v.getId()==R.id.ListViewIdForms) {
    	    AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
    		menu.setHeaderTitle(StrData[info.position]);
    		String[] menuItems = getResources().getStringArray(R.array.menu); 
    		for (int i = 0; i<menuItems.length; i++) {
    			menu.add(Menu.NONE, i, i, menuItems[i]);
			}
    	}
    }
    
	@Override
    public boolean onContextItemSelected(MenuItem item) {
	    AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
	    int menuItemIndex = item.getItemId();
		String[] menuItems = getResources().getStringArray(R.array.menu);
		String menuItemName = menuItems[menuItemIndex];
	    final String listItemName = StrData[info.position];
	    
	   
        /*llInflate.removeAllViews();
	    llInflate.addView(getLayoutInflater().inflate(R.layout.renametable,llInflate,false));*/
	    
		
		
	   
		String switchValue = "";
		
		
		if(views.Table)
		{
			switchValue = "01234";
		}
		if(views.Field)
		{
			switchValue = "234";
		}
		if(views.Data)
		{
			switchValue = "";
		}
		
		for(int q = 0;q < switchValue.length();q++)
		{
			boolean test = false;
			
			try
			{ 
				tempInt = Integer.parseInt(switchValue.substring(q,q+1).toString());
				
				if(tempInt == menuItemIndex)
				{
					test = true;
					q = 100;
					//continue;
				}
				else
				{
					
					
				}
			}
			catch(NumberFormatException nfe)
			{}
			/*
			if(test)
			{
				switch(tempInt)
				{
	    			case 0:	
	    				strTblName = listItemName;
	    				if(views.Table)
	    				{
	    					i = new Intent(viewForms.this,renameTable.class);
	    					Bundle extras = new Bundle();
	    					extras.putString("TblName",strTblName);
	    					i.putExtras(extras);
	    					startActivityForResult(i, 1);
	    				}
	    				if(views.Field)
	    				{
	    					i = new Intent(viewForms.this,renameTable.class);
	    					Bundle extras = new Bundle();
	    					extras.putString("TblName",strTblName);
	    					i.putExtras(extras);
	    					startActivityForResult(i, 1);
	    				}
	    				
	    				break;
	    			case 1:	
	    					if(views.Table)
    						{						
	    						confirm.setMessage("Do you wish to delete the table("+listItemName.toUpperCase()+") from the database?");	
    						}
	    					if(views.Field)
	    					{
	    						confirm.setMessage("Do you wish to delete the field("+listItemName.toUpperCase()+") from the database?");
	    					}
	    						confirm.setTitle("Delete "+listItemName);
	    						confirm.setButton("Yes", new DialogInterface.OnClickListener() {
	    						public void onClick(DialogInterface dialog, int which) {
	        	    	  
	    							try
	    							{
	    								if(views.Table)
	    	    						{	
	    									String temp = dt.delete(listItemName, listItemName, "6", listItemName);			
	    	    						}
	    								if(views.Field)
	    		    					{
	    									String temp = dt.delete(listItemName, listItemName, "5", listItemName);		
	    		    					}
	    								i = new Intent(viewForms.this,viewForms.class);
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
	    					break;
	    			case 2:	
	    					strTblName = listItemName;
	    					i = new Intent(viewForms.this,addField.class);
	    					
	    					if(views.Table)
	    					{
	    						Bundle extras = new Bundle();
	    						extras.putString("TblName",strTblName);
	    						i.putExtras(extras);
	    						startActivityForResult(i, 1);
	    					}
	    				break;
	    			case 3:	
	    				
	    					strTblName = listItemName;
	    					i = new Intent(viewForms.this,renameField.class);
	    					
	    					if(views.Table)
	    					{
	    						Bundle extras = new Bundle();
	    						extras.putString("TblName",strTblName);
	    						i.putExtras(extras);
	    						startActivityForResult(i, 1);
	    					}
	    				break;
	    			case 4:	
	    					strTblName = listItemName;
	    					i = new Intent(viewForms.this,fields.class);
    					
	    					if(views.Table)
	    					{
	    						Bundle extras = new Bundle();
	    						extras.putString("TblName",strTblName);
	    						i.putExtras(extras);
	    						startActivityForResult(i, 1);
	    					}
	    			break;
	    	
				};
				
			}*/
		}
		
    	return true;
    }
 }