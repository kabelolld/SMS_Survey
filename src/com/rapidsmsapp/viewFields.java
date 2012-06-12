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
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class viewFields extends Activity
 {
    /** Called when the activity is first created. */
   
	Button backtoForm;
	TextView formNames;
	String data,StrData[],Tbname,Fname,Action,Option,toastMsg,strTemp,strTblName,listItemName;
	StringTokenizer stArr;
	ListView listView;
	database dt;
	Bundle extras;
	views views;
	Intent i;
	AlertDialog confirm;
	int tempInt;
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
		    super.onCreate(savedInstanceState);
	        setContentView(R.layout.viewfields);
	        
	        backtoForm = (Button)findViewById(R.id.btnBckFld); 
	       
	        listView = (ListView) findViewById(R.id.LstVwIdFld);
	        confirm = new AlertDialog.Builder(this).create();
	        listView.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT,10));
	        dt = new database();
	        views = new views();
	        try
	        {
	        	data = "";
	            extras = getIntent().getExtras();
	          
	            if(extras != null)
	            {
	            	data = extras.getString("strTemp");        	
	            	data = data.substring(0, data.indexOf("@"));	   		 
	            	StrData = data.split("#");
	            	listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, StrData));	 
	            	registerForContextMenu(listView);
	            }
	        }
	        catch(Exception e)
	        {
	        	Toast.makeText(viewFields.this, "Couldn't retrieve from the database.", Toast.LENGTH_LONG).show();
	        }
	        
	      	       
	        backtoForm.setOnClickListener(new View.OnClickListener() 
	        {
	    		
	    		public void onClick(View v) 
	    		{
	    			
	    			 
	    			Intent i = new Intent(viewFields.this,fields.class); //calling a class forms
	    			viewFields.this.finish();// close the form class 
	    			startActivity(i);
	    			
	    		}
	    	});
	        
    }
	@Override
    public void onCreateContextMenu(ContextMenu menu, View v,
    		ContextMenuInfo menuInfo) {
    	if (v.getId()==R.id.LstVwIdFld) {
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
	    listItemName = StrData[info.position];
	    
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
			
			if(test)
			{
				switch(tempInt)
				{
	    			case 0:	
	    				strTblName = listItemName;
	    				if(views.Table)
	    				{
	    					i = new Intent(viewFields.this,renameTable.class);
	    					Bundle extras = new Bundle();
	    					extras.putString("TblName",strTblName);
	    					i.putExtras(extras);
	    					startActivityForResult(i, 1);
	    				}
	    				if(views.Field)
	    				{
	    					i = new Intent(viewFields.this,renameTable.class);
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
	    								i = new Intent(viewFields.this,viewForms.class);
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
	    					i = new Intent(viewFields.this,addField.class);
	    					
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
    					i = new Intent(viewFields.this,fields.class);
					
    					if(views.Table)
    					{
    						Bundle extras = new Bundle();
    						extras.putString("TblName",strTblName);
    						i.putExtras(extras);
    						startActivityForResult(i, 1);
    					}
	    				break;
	    			
	    	
				};
				
			}
		}
		
    	return true;
    }
}
