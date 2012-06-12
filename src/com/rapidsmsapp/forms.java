package com.rapidsmsapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class forms extends Activity {
	Button back;// = new Button(forms.this);
	Button create;
	Button view,edit;
	public void onCreate(Bundle savedInstanceState)
	{
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forms);
        
 //----------------------------------------------------------------------------------------------     
        
        back = (Button)findViewById(R.id.btnMain);
        create = (Button)findViewById(R.id.btnCreate);
        view = (Button)findViewById(R.id.btnView);
        edit = (Button)findViewById(R.id.btnEdit);
 //------------------------------------------------------------------------------------------------
        
       
        back.setOnClickListener(new View.OnClickListener()
        {
			
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				Intent i = new Intent(forms.this,RapidSMSAppActivity.class);
				forms.this.finish();
				startActivity(i);
				
			}
		});
//----------------------------------------------------------------------------------------------------       
        create.setOnClickListener(new View.OnClickListener()
         {
			
			
			public void onClick(View v) 
			{
				Intent btncreate = new Intent(forms.this,createForm.class);
				//forms.this.finish();
				startActivity(btncreate);
			
				
			}
		});
//----------------------------------------------------------------------------------------------------

        view.setOnClickListener(new View.OnClickListener()
        {
			
			
			public void onClick(View v) 
			{
				Intent btncreate = new Intent(forms.this,views.class);
				//forms.this.finish();
				startActivity(btncreate);
				
			}
		});

        
//----------------------------------------------------------------------------------------------------        
        edit.setOnClickListener(new View.OnClickListener()
        {
			
			
			public void onClick(View v) 
			{
				Intent btncreate = new Intent(forms.this,editforms.class);
				//forms.this.finish();
				startActivity(btncreate);
				
			}
		});
    }
	
}