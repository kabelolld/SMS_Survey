package com.rapidsmsapp;

import android.app.Activity;

public class contactToPhone extends Activity
 {
    /** Called when the activity is first created. */
	String name,lastname,email;
	contactToPhone()
	{
		 name = "";
		 lastname = "";
		 email = "";
		
		
	}
	void saveContactstophone(String Name,String LastName,String Email)
	{
		 name = Name;
		 lastname = LastName;
		 email =Email;	
	}
		
 }
   