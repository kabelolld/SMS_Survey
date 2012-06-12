package com.rapidsmsapp;
  
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;



public class database extends Activity 
{
	String strUrl;
	String Recvdata;
	boolean result = true;
	
	database()
	{
		strUrl ="http://tutchat.comze.com/PhoneGap/recievedInformation.php";
		Recvdata =" ";
		
	};
	//---------------------Retrieve from the database-----------------------
	String getForms(String Tbname,String Fname, String Action,String Option)
	  {
		
		ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>(); 
		
		postParameters.add(new BasicNameValuePair("tblname",Tbname));   
		postParameters.add(new BasicNameValuePair("fieldname", Fname));   
		postParameters.add(new BasicNameValuePair("action", Action));   
		postParameters.add(new BasicNameValuePair("option", Option));  
				
		
		try
		{
			
			Recvdata = CustomHttpClient.executeHttpPost(strUrl, postParameters);  
			
				if(Recvdata.length()== 0)
				{
					result = false;
				}
				
			 
		}
		catch (Exception e)
		{
			
			/*excpError = e.getMessage().toString();
			Recvdata = excpError;*/
			result = false;
			
		}
		
		if(result== false)
		  {
			Recvdata=   "No Recied Data from Net\n";
		  }
		
		return Recvdata;
	};
	//---------------Send to the database-----------------------------------
	String setForms(String Tbname,String Fname, String Action,String Option, String NewName)
	  {
		
		String excpError;
		ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>(); 
		
		postParameters.add(new BasicNameValuePair("tblname",Tbname));   
		postParameters.add(new BasicNameValuePair("fieldname", Fname));   
		postParameters.add(new BasicNameValuePair("action", Action));   
		postParameters.add(new BasicNameValuePair("option", Option));  
		postParameters.add(new BasicNameValuePair("newname", NewName));   
		
		
		
		try
		{
				Recvdata = CustomHttpClient.executeHttpPost(strUrl, postParameters);  
				
				if(Recvdata.length()== 0)
				{
					result = false;
				}
				
			 
		}
		catch (Exception e)
		{
			
			
			excpError = e.getMessage().toString();
			Recvdata = excpError;
			result = false;
			
		}
		
		if(result== false)
		  {
			Recvdata=   "No Recieved Data from Net\n";
		  }
		
		return Recvdata;
	};
	//--------------------Delete-----------------------------------------------
	String delete(String Tbname,String Fname, String Action,String Option)
	  {
		String excpError;
		ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>(); 
		
		postParameters.add(new BasicNameValuePair("tblname",Tbname));   
		postParameters.add(new BasicNameValuePair("fieldname", Fname));   
		postParameters.add(new BasicNameValuePair("action", Action));   
		postParameters.add(new BasicNameValuePair("option", Option));  
		
		try
		{
				Recvdata = CustomHttpClient.executeHttpPost(strUrl, postParameters);  
				
				if(Recvdata.length()== 0)
				{
					result = false;
				}
				
			 
		}
		catch (Exception e)
		{
			
			excpError = e.getMessage().toString();
			Recvdata = excpError;
			result = false;
			
		}
		
		if(result== false)
		  {
			Recvdata=   "No Recied Data from Net\n";
		  }
		
		
		return Recvdata;
	};
	
	public String getStats()
	{
		strUrl = "http://tutchat.comze.com/PhoneGap/stats.php";
		Recvdata="Nothing";
		
		try
		{
			
		Recvdata = CustomHttpClient.executeHttpGet(strUrl);  
		
		}
		catch (Exception e)
		{
		}
		 
	 return Recvdata;
	};
	
	String getRename(String Tbname,String Fname, String Action,String Option, String NewName)
	  {
		String excpError;
		ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>(); 
		
		postParameters.add(new BasicNameValuePair("tblname",Tbname));   
		postParameters.add(new BasicNameValuePair("fieldname", Fname));   
		postParameters.add(new BasicNameValuePair("action", Action));   
		postParameters.add(new BasicNameValuePair("option", Option));  
		postParameters.add(new BasicNameValuePair("newname", NewName));   
				
		
		try
		{
				Recvdata = CustomHttpClient.executeHttpPost(strUrl, postParameters);  
				
				if(Recvdata.length()== 0)
				{
					result = false;
				}
				else
				{
					result = true;
				}
				
			 
		}
		catch (Exception e)
		{		
			result = false;			
		}
		
		if(result== false)
		  {
			Recvdata=   "No Recieved Data from internet\n";
		  }
		
		return Recvdata;
	};
}